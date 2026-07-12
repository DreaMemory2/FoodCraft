package com.crystal.foodcraft.block.entity;

import com.crystal.foodcraft.api.TickableBlockEntity;
import com.crystal.foodcraft.block.basic.CookableProvider;
import com.crystal.foodcraft.block.basic.FluidHandlerProvider;
import com.crystal.foodcraft.block.basic.HeatableProvider;
import com.crystal.foodcraft.network.BlockPosPayload;
import com.crystal.foodcraft.recipe.ModRecipeTypes;
import com.crystal.foodcraft.recipe.PressureCookingRecipe;
import com.crystal.foodcraft.recipe.input.PressureCookerInput;
import com.crystal.foodcraft.screenhandler.PressureCookerMenu;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ContainerStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class PressureCookerBlockEntity extends BaseMachineBlockEntity implements CookableProvider, HeatableProvider, FluidHandlerProvider, TickableBlockEntity, ExtendedMenuProvider<BlockPosPayload> {
    private NonNullList<ItemStack> items = NonNullList.withSize(6, ItemStack.EMPTY);
    public final SingleFluidStorage fluidStorage = SingleFluidStorage.withFixedCapacity(FluidConstants.BUCKET * 8, this::update);
    private final ContainerStorage inventoryStorage = ContainerStorage.of(this, null);
    protected final ContainerData data = new ContainerData() {
        @Override
        public int get(int dataId) {
            return switch (dataId) {
                case 0 -> PressureCookerBlockEntity.this.burnTime;
                case 1 -> PressureCookerBlockEntity.this.maxBurnTime;
                case 2 -> PressureCookerBlockEntity.this.cookingTime;
                case 3 -> PressureCookerBlockEntity.this.maxCookingTime;
                default -> -1;
            };
        }

        @Override
        public void set(int dataId, int value) {
            switch (dataId) {
                case 0 -> PressureCookerBlockEntity.this.burnTime = value;
                case 1 -> PressureCookerBlockEntity.this.maxBurnTime = value;
                case 2 -> PressureCookerBlockEntity.this.cookingTime = value;
                case 3 -> PressureCookerBlockEntity.this.maxCookingTime = value;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    };

    public PressureCookerBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.PRESSURE_COOKER, worldPosition, blockState);
    }

    @Override
    public void tick() {
        if (level == null || level.isClientSide()) return;

        Storage<FluidVariant> itemFluidStorge = ContainerItemContext.ofSingleSlot(inventoryStorage.getSlot(3)).find(FluidStorage.ITEM);
        if (itemFluidStorge != null)
            inputFluid(this.fluidStorage, itemFluidStorge);

        // 判断容器是否已经装上两桶水
        if (getFluidStorage().getAmount() < (FluidConstants.BUCKET * 2)) return;

        // 执行配方程序
        /* 提供内置热源 */
        // 如果方块状态正在燃烧
        if (burnTime > 0) {
            burnTime--;
            isLit = true;
        } else if (burnTime == 0) {
            maxBurnTime = 0;
            isLit = false;
            init();
            detectLit(level, getBlockPos(), getBlockState(), false);
        }

        // 获取燃料
        ItemStack fuel = items.get(4);
        // 如果方块状态熄火。则重新点燃
        if (burnTime == 0 && !fuel.isEmpty() && level.fuelValues().isFuel(fuel)) {
            fuel.shrink(1);
            burnTime = getBurnTime(level.fuelValues(), fuel);
            maxBurnTime = getBurnTime(level.fuelValues(), fuel);
            isLit = true;
            detectLit(level, getBlockPos(), getBlockState(), true);
        }

        /* 烹饪阶段 */
        // 获取食谱
        PressureCookerInput input = PressureCookerInput.input(items, getFluidStorage()).slots();
        Optional<RecipeHolder<@NotNull PressureCookingRecipe>> recipe = ((ServerLevel) level).recipeAccess().getRecipeFor(ModRecipeTypes.PRESSURE_COOKING_RECIPE_TYPE, input, level);
        // 如果食谱不存在，则停止烹饪
        if (recipe.isEmpty()) return;
        // 获取液体
        Fluid fluid = getFluidStorage().variant.getFluid();
        boolean hasFluid = !fluid.defaultFluidState().isEmpty();

        if (isLit && cookingTime <= 0) {
            cookingTime++;
            maxCookingTime = 200;
        }

        if (isLit && hasFluid) {
            cookingTime++;
            if (cookingTime == maxCookingTime) {
                // cookingTime == 200
                ItemStack result = recipe.get().value().assemble(input);
                if (result.isEmpty()) return;
                // 消耗材料
                consumeIngredient(PressureCookerInput.input(items, getFluidStorage()));
                // 消耗液体
                consumeFluid(getFluidStorage(), FluidConstants.BUCKET * 2);
                // 生产食物
                produceFood(result, items, 5);
                init();
            }
        }
        setChanged(level, getBlockPos(), getBlockState());
    }

    public void init() {
        cookingTime = 0;
        maxCookingTime = 0;
    }

    public void update() {
        setChanged();
        if(level != null)
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
    }

    @Override
    protected void loadAdditional(@NotNull ValueInput input) {
        super.loadAdditional(input);
        // 加载物品
        ContainerHelper.loadAllItems(input, this.items);
        // 加载液体
        this.fluidStorage.readValue(input);
    }

    @Override
    protected void saveAdditional(@NotNull ValueOutput output) {
        super.saveAdditional(output);
        // 保存物品
        ContainerHelper.saveAllItems(output, this.items);
        // 保存液体
        this.fluidStorage.writeValue(output);
    }

    @Override
    public @NotNull NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(@NotNull NonNullList<ItemStack> items) {
        this.items = items;
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return Component.translatable("container.foodcraft.pressure_cooker");
    }

    @Override
    protected @NotNull AbstractContainerMenu createMenu(int containerId, @NotNull Inventory inventory) {
        return new PressureCookerMenu(containerId, inventory, this, data, this);
    }

    @Override
    public @NotNull BlockPosPayload getScreenOpeningData(@NotNull ServerPlayer player) {
        return new BlockPosPayload(getBlockPos());
    }

    @Override
    public int getContainerSize() {
        return items.size();
    }

    public ContainerStorage getInventoryProvider(Direction direction) {
        return this.inventoryStorage;
    }

    public SingleFluidStorage getFluidStorage(Direction direction) {
        return this.fluidStorage;
    }

    public SingleFluidStorage getFluidStorage() {
        return this.fluidStorage;
    }
}

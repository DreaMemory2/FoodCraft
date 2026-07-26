package com.crystal.foodcraft.block.entity;

import com.crystal.foodcraft.api.TickableBlockEntity;
import com.crystal.foodcraft.block.basic.CookableProvider;
import com.crystal.foodcraft.block.basic.FluidHandlerProvider;
import com.crystal.foodcraft.block.basic.HeatableProvider;
import com.crystal.foodcraft.network.BlockPosPayload;
import com.crystal.foodcraft.recipe.BeverageMakingRecipe;
import com.crystal.foodcraft.recipe.ModRecipeTypes;
import com.crystal.foodcraft.recipe.input.BMMachineInput;
import com.crystal.foodcraft.screenhandler.BeverageMakingMachineMenu;
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

public class BMMachineBlockEntity extends BaseMachineBlockEntity implements CookableProvider, HeatableProvider, FluidHandlerProvider, TickableBlockEntity, ExtendedMenuProvider<BlockPosPayload> {
    private NonNullList<ItemStack> items = NonNullList.withSize(5, ItemStack.EMPTY);
    public final SingleFluidStorage fluidStorage = SingleFluidStorage.withFixedCapacity(FluidConstants.BUCKET * 4, this::update);
    private final ContainerStorage inventoryStorage = ContainerStorage.of(this, null);
    public final static FuelColdValue FUEL_VALUES = FuelColdValue.coldTime();
    private BeverageMakingMode mode;
    public int modeId;
    protected final ContainerData data = new ContainerData() {
        @Override
        public int get(int dataId) {
            return switch (dataId) {
                case 0 -> BMMachineBlockEntity.this.burnTime;
                case 1 -> BMMachineBlockEntity.this.maxBurnTime;
                case 2 -> BMMachineBlockEntity.this.cookingTime;
                case 3 -> BMMachineBlockEntity.this.maxCookingTime;
                case 4 -> BMMachineBlockEntity.this.modeId;
                default -> -1;
            };
        }

        @Override
        public void set(int dataId, int value) {
            switch (dataId) {
                case 0 -> BMMachineBlockEntity.this.burnTime = value;
                case 1 -> BMMachineBlockEntity.this.maxBurnTime = value;
                case 2 -> BMMachineBlockEntity.this.cookingTime = value;
                case 3 -> BMMachineBlockEntity.this.maxCookingTime = value;
                case 4 -> BMMachineBlockEntity.this.modeId = value;
            }
        }

        @Override
        public int getCount() {
            return 5;
        }
    };

    public BMMachineBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.BEVERAGE_MAKING_MACHINE, worldPosition, blockState);
    }

    @Override
    public void tick() {
        if (level == null || level.isClientSide()) return;

        Storage<FluidVariant> itemFluidStorge = ContainerItemContext.ofSingleSlot(inventoryStorage.getSlot(1)).find(FluidStorage.ITEM);
        // 输入液体
        if (itemFluidStorge != null)
            inputFluid(this.fluidStorage, itemFluidStorge);

        // 如果方块状态正在燃烧
        if (burnTime > 0) {
            burnTime--;
            isLit = true;
        } else if (burnTime == 0) {
            maxBurnTime = 0;
            isLit = false;
            mode = null;
            init();
            detectLit(level, getBlockPos(), getBlockState(), false);
        }

        cool();
        heat();

        /* 烹饪阶段 */
        // 获取食谱
        BMMachineInput input = new BMMachineInput(mode, items.getFirst(), fluidStorage);
        Optional<RecipeHolder<@NotNull BeverageMakingRecipe>> recipe = ((ServerLevel) level).recipeAccess().getRecipeFor(ModRecipeTypes.BEVERAGE_MAKING_RECIPE_TYPE, input, level);
        // 如果食谱不存在，则停止烹饪
        if (recipe.isEmpty()) return;
        if (mode == null) return;
        // 获取液体
        Fluid fluid = getFluidStorage().variant.getFluid();
        boolean hasFluid = !fluid.defaultFluidState().isEmpty();

        if (isLit && cookingTime <= 0) {
            cookingTime++;
            maxCookingTime = 200;
        }

        if (isLit && hasFluid) {
            cookingTime++;
            if (cookingTime == maxCookingTime && recipe.get().value().matches(mode, input)) {
                // cookingTime == 200
                ItemStack result = recipe.get().value().assemble(input);
                if (result.isEmpty()) return;
                // 消耗材料
                if (!this.items.getFirst().isEmpty()) {
                    ItemStack copy = this.items.getFirst().copy();
                    copy.shrink(1);
                    this.items.set(0, copy);
                }
                // 消耗液体
                consumeFluid(getFluidStorage(), FluidConstants.BUCKET);
                // 生产食物
                produceFood(result, items, 4);
                init();
            }
        }
        setChanged(level, getBlockPos(), getBlockState());
    }

    public void cool() {
        // 获取燃料
        ItemStack fuel = items.get(3);
        // 如果方块状态熄火。则重新点燃
        // 并且处于加热状态，不是制冷状态
        if (burnTime == 0 && !fuel.isEmpty() && FUEL_VALUES.isFuel(fuel) && mode != BeverageMakingMode.HEAT) {
            mode = BeverageMakingMode.COOL;
            modeId = BeverageMakingMode.COOL.level();
            fuel.shrink(1);
            burnTime = FUEL_VALUES.getColdTime(fuel);
            maxBurnTime = FUEL_VALUES.getColdTime(fuel);
            isLit = true;
            detectLit(level, getBlockPos(), getBlockState(), true);
        }
    }

    public void heat() {
        // 获取燃料
        ItemStack fuel = items.get(2);
        // 如果方块状态熄火。则重新点燃
        // 并且处于加热状态，不是制冷状态
        if (burnTime == 0 && !fuel.isEmpty() && level.fuelValues().isFuel(fuel) && mode != BeverageMakingMode.COOL) {
            mode = BeverageMakingMode.HEAT;
            modeId = BeverageMakingMode.HEAT.level();
            fuel.shrink(1);
            burnTime = getBurnTime(level.fuelValues(), fuel);
            maxBurnTime = getBurnTime(level.fuelValues(), fuel);
            isLit = true;
            detectLit(level, getBlockPos(), getBlockState(), true);
        }
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
    protected @NotNull Component getDefaultName() {
        return Component.translatable("container.foodcraft.beverage_making_machine");
    }

    @Override
    protected @NotNull NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(@NotNull NonNullList<ItemStack> items) {
        this.items = items;
    }

    @Override
    protected @NotNull AbstractContainerMenu createMenu(int containerId, @NotNull Inventory inventory) {
        return new BeverageMakingMachineMenu(containerId, inventory, this, data, this);
    }

    public BeverageMakingMode getMode() {
        if (this.mode != null) return mode;
        return null;
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    public ContainerStorage getInventoryProvider(Direction direction) {
        return this.inventoryStorage;
    }

    public SingleFluidStorage getFluidStorage(Direction direction) {
        return this.fluidStorage;
    }

    public SingleFluidStorage getFluidStorage() {
        return fluidStorage;
    }

    @Override
    public @NotNull BlockPosPayload getScreenOpeningData(@NotNull ServerPlayer player) {
        return new BlockPosPayload(getBlockPos());
    }
}

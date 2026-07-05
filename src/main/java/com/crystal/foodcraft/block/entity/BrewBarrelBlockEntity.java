package com.crystal.foodcraft.block.entity;

import com.crystal.foodcraft.api.TickableBlockEntity;
import com.crystal.foodcraft.block.basic.CookableProvider;
import com.crystal.foodcraft.block.basic.FluidHandlerProvider;
import com.crystal.foodcraft.network.BlockPosPayload;
import com.crystal.foodcraft.network.NetWorkBlockEntity;
import com.crystal.foodcraft.recipe.BrewingRecipe;
import com.crystal.foodcraft.recipe.ModRecipeTypes;
import com.crystal.foodcraft.recipe.input.BrewBarrelInput;
import com.crystal.foodcraft.screenhandler.BrewBarrelMenu;
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
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class BrewBarrelBlockEntity extends NetWorkBlockEntity implements FluidHandlerProvider, CookableProvider, TickableBlockEntity, ExtendedMenuProvider<BlockPosPayload> {
    private NonNullList<ItemStack> items = NonNullList.withSize(6, ItemStack.EMPTY);
    public final SingleFluidStorage fluidStorage = SingleFluidStorage.withFixedCapacity(FluidConstants.BUCKET * 4, this::update);
    private final ContainerStorage inventoryStorage = ContainerStorage.of(this, null);
    protected final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int dataId) {
            return switch (dataId) {
                case 0 -> BrewBarrelBlockEntity.this.fermentingTime;
                case 1 -> BrewBarrelBlockEntity.this.minFermentingTime;
                default -> -1;
            };
        }

        @Override
        public void set(int dataId, int value) {
            switch (dataId) {
                case 0 -> BrewBarrelBlockEntity.this.fermentingTime = value;
                case 1 -> BrewBarrelBlockEntity.this.minFermentingTime = value;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    };
    private int fermentingTime;
    private int minFermentingTime;

    public BrewBarrelBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.BREW_BARREL, worldPosition, blockState);
    }

    public void init() {
        this.fermentingTime = 0;
        this.minFermentingTime = 0;
    }

    @Override
    public void tick() {
        if (level == null || level.isClientSide()) return;

        Storage<FluidVariant> itemFluidStorge = ContainerItemContext.ofSingleSlot(inventoryStorage.getSlot(3)).find(FluidStorage.ITEM);
        if (itemFluidStorge != null)
            inputFluid(this.fluidStorage, itemFluidStorge);

        // 判断容器是否已经装满液体
        if (getFluidStorage().getAmount() != getFluidStorage().getCapacity()) return;

        BrewBarrelInput input = BrewBarrelInput.input(items, fluidStorage).slots();
        if (input == null) return;
        Optional<RecipeHolder<@NotNull BrewingRecipe>> recipe = ((ServerLevel) level).recipeAccess().getRecipeFor(ModRecipeTypes.BREWING_RECIPE_TYPE, input, level);
        // 如果食谱为空，不存在，则停止发酵，初始化状态
        if (recipe.isEmpty()) {
            init();
            return;
        }
        // 如果有配方且发酵时间为0，则开始发酵
        if (fermentingTime == 0) {
            // 3分钟发酵时间
            fermentingTime = 3600;
            minFermentingTime = 0;
        } else fermentingTime--;

        // 如果发酵时间等于最小发酵时间，则输出结果
        if (fermentingTime == minFermentingTime) {
            List<ItemStack> result = recipe.get().value().getResult(input);
            // 消耗材料
            consumeIngredient(items);
            // 清空液体
            consumeFluid(getFluidStorage(), FluidConstants.BUCKET * 4);
            // 输出材料
            if (!result.isEmpty()) {
                for (int i = 0; i < result.size(); i++) {
                    if (!result.get(i).isEmpty())
                        produceFood(result.get(i), items, i + 4);
                }
            }
            // 初始化
            init();
        }
    }

    public void consumeIngredient(NonNullList<ItemStack> ingredients) {
        if (!ingredients.isEmpty()) {
            for (int i = 0; i <= 2; i++) {
                if (!ingredients.get(i).isEmpty()) {
                    ingredients.get(i).shrink(1);
                }
            }
        }
    }

    public void update() {
        setChanged();
        if(level != null)
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
    }

    @Override
    protected void loadAdditional(@NotNull ValueInput input) {
        super.loadAdditional(input);
        // 加载容器中的物品
        ContainerHelper.loadAllItems(input, items);
        // 加载发酵时间
        input.getIntOr("FermentingTime", this.fermentingTime);
        input.getIntOr("MinFermentingTime", this.minFermentingTime);
        // 保存液体
        this.fluidStorage.readValue(input);
    }

    @Override
    protected void saveAdditional(@NotNull ValueOutput output) {
        super.saveAdditional(output);
        // 保存容器中物品
        ContainerHelper.saveAllItems(output, items);
        // 保存发酵时间
        output.putInt("FermentingTime", this.fermentingTime);
        output.putInt("MinFermentingTime", this.minFermentingTime);
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
        return Component.translatable("container.foodcraft.brew_barrel");
    }

    @Override
    protected @NotNull AbstractContainerMenu createMenu(int containerId, @NotNull Inventory inventory) {
        return new BrewBarrelMenu(containerId, inventory, this, this.dataAccess, this);
    }

    @Override
    public @NotNull BlockPosPayload getScreenOpeningData(@NotNull ServerPlayer player) {
        return new BlockPosPayload(getBlockPos());
    }

    public int getFermentingTime() {
        return fermentingTime;
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
        return this.fluidStorage;
    }
}

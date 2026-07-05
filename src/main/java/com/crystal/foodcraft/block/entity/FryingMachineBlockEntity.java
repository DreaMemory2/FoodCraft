package com.crystal.foodcraft.block.entity;

import com.crystal.foodcraft.api.TickableBlockEntity;
import com.crystal.foodcraft.block.basic.FluidHandlerProvider;
import com.crystal.foodcraft.network.BlockPosPayload;
import com.crystal.foodcraft.recipe.FryingRecipe;
import com.crystal.foodcraft.recipe.ModRecipeTypes;
import com.crystal.foodcraft.recipe.input.FluidAttachedRecipeInput;
import com.crystal.foodcraft.screenhandler.FryingMachineMenu;
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
import net.minecraft.util.Mth;
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
import org.jspecify.annotations.NonNull;

import java.util.Optional;

public class FryingMachineBlockEntity extends BaseMachineBlockEntity implements FluidHandlerProvider, TickableBlockEntity, ExtendedMenuProvider<BlockPosPayload> {
    private NonNullList<ItemStack> items = NonNullList.withSize(4, ItemStack.EMPTY);
    /**
     * <p>设置可存储4000mb液体</p>
     */
    public final SingleFluidStorage fluidStorage = SingleFluidStorage.withFixedCapacity(FluidConstants.BUCKET * 4, this::update);
    private final ContainerStorage inventoryStorage = ContainerStorage.of(this, null);
    public ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int dataId) {
            return switch (dataId) {
                case 0 -> FryingMachineBlockEntity.this.burnTime;
                case 1 -> FryingMachineBlockEntity.this.maxBurnTime;
                case 2 -> FryingMachineBlockEntity.this.cookingTime;
                case 3 -> FryingMachineBlockEntity.this.maxCookingTime;
                default -> -1;
            };
        }

        @Override
        public void set(int dataId, int value) {
            switch (dataId) {
                case 0 -> FryingMachineBlockEntity.this.burnTime = value;
                case 1 -> FryingMachineBlockEntity.this.maxBurnTime = value;
                case 2 -> FryingMachineBlockEntity.this.cookingTime = value;
                case 3 -> FryingMachineBlockEntity.this.maxCookingTime = value;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    };

    public FryingMachineBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.FRYING_MACHINE, worldPosition, blockState);
    }

    public void tick() {
        if (level == null || level.isClientSide()) return;

        Storage<FluidVariant> itemFluidStorge = ContainerItemContext.ofSingleSlot(inventoryStorage.getSlot(1)).find(FluidStorage.ITEM);
        if (itemFluidStorge != null) {
            inputFluid(this.fluidStorage, itemFluidStorge);
        }

        // 执行配方程序
        if (level == null) return;
        boolean changed = false;
        canLit();

        ItemStack ingredient = items.get(0);
        ItemStack fuel = items.get(2);
        Fluid fluid = getFluidStorage().variant.getFluid();
        boolean hasIngredient = !ingredient.isEmpty();
        boolean hasFuel = !fuel.isEmpty();
        boolean hasFluid = !fluid.defaultFluidState().isEmpty();
        if (isLit || hasFuel && (hasIngredient && hasFluid)) {
            if (hasIngredient && hasFluid) {
                FluidAttachedRecipeInput input = new FluidAttachedRecipeInput(ingredient, fluid.defaultFluidState());
                Optional<RecipeHolder<@NonNull FryingRecipe>> recipe = ((ServerLevel) level).recipeAccess().getRecipeFor(ModRecipeTypes.FRYING_RECIPE_TYPE, input, level);
                if (recipe.isPresent()) {
                    ItemStack result = recipe.get().value().assemble(input);
                    if (!result.isEmpty()) {
                        // 如果机器熄火(burnTime = 0)，则尝试重新点火
                        changed = startLit(fuel);

                        if(isLit) {
                            cookingTime++;
                            if (cookingTime == maxCookingTime) {
                                cookingTime = 0;
                                maxCookingTime = 200;
                                // 生产食材，消耗材料和花生油
                                produceFood(result, items, 3);
                                ingredient.shrink(1);
                                consumeFluid(fluidStorage, FluidConstants.BUCKET);
                                changed = true;
                            }
                        } else {
                            cookingTime = 0;
                        }
                    }
                } else {
                    cookingTime = 0;
                }
            } else {
                cookingTime = 0;
            }
        } else if (cookingTime > 0) {
            cookingTime = Mth.clamp(cookingTime - 2, 0, maxCookingTime);
        }
        if (changed) {
            setChanged(level, getBlockPos(), getBlockState());
        }
    }


    public void update() {
        setChanged();
        if(level != null) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    }

    @Override
    protected void saveAdditional(@NotNull ValueOutput tag) {
        super.saveAdditional(tag);
        // 存储库存中的物品
        ContainerHelper.saveAllItems(tag, items);
        this.fluidStorage.writeValue(tag);
    }

    @Override
    protected void loadAdditional(@NotNull ValueInput tag) {
        super.loadAdditional(tag);
        // 加载库存中的物品
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this.items);
        this.fluidStorage.readValue(tag);
    }

    @NotNull
    @Override
    protected AbstractContainerMenu createMenu(int containerId, @NotNull Inventory inventory) {
        return new FryingMachineMenu(containerId, inventory, this, dataAccess, this);
    }

    @Override
    public boolean canPlaceItem(int slot, @NotNull ItemStack itemStack) {
        return this.isPlaceItem(slot, itemStack);
    }

    @Override
    public void setItem(int slot, @NotNull ItemStack itemStack) {
        setItem(this.items, slot, itemStack);
    }

    @Override
    protected void setItems(@NotNull NonNullList<ItemStack> items) {
        this.items = items;
    }

    @NotNull
    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.foodcraft.frying_machine");
    }

    @NotNull
    @Override
    public BlockPosPayload getScreenOpeningData(@NotNull ServerPlayer player) {
        return new BlockPosPayload(this.getBlockPos());
    }

    @NotNull
    @Override
    public NonNullList<ItemStack> getItems() {
        return items;
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

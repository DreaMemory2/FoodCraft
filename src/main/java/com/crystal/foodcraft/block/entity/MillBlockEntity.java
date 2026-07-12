package com.crystal.foodcraft.block.entity;

import com.crystal.foodcraft.api.TickableBlockEntity;
import com.crystal.foodcraft.block.basic.CookableProvider;
import com.crystal.foodcraft.block.basic.HeatableProvider;
import com.crystal.foodcraft.recipe.MillingRecipe;
import com.crystal.foodcraft.recipe.ModRecipeTypes;
import com.crystal.foodcraft.screenhandler.MillMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

public class MillBlockEntity extends BaseMachineBlockEntity implements HeatableProvider, CookableProvider, TickableBlockEntity {
    private NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);
    public ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int dataId) {
            return switch (dataId) {
                case 0 -> MillBlockEntity.this.burnTime;
                case 1 -> MillBlockEntity.this.maxBurnTime;
                case 2 -> MillBlockEntity.this.cookingTime;
                case 3 -> MillBlockEntity.this.maxCookingTime;
                default -> 0;
            };
        }

        @Override
        public void set(int dataId, int value) {
            switch (dataId) {
                case 0 -> MillBlockEntity.this.burnTime = value;
                case 1 -> MillBlockEntity.this.maxBurnTime = value;
                case 2 -> MillBlockEntity.this.cookingTime = value;
                case 3 -> MillBlockEntity.this.maxCookingTime = value;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    };

    public MillBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.MILL, worldPosition, blockState);
    }

    /**
     * @see AbstractFurnaceBlockEntity#serverTick(ServerLevel, BlockPos, BlockState, AbstractFurnaceBlockEntity)
     */
    @Override
    public void tick() {
        if (level == null) return;
        boolean changed = false;
        canLit();

        ItemStack fuel = items.get(1);
        ItemStack ingredient = items.get(0);
        boolean hasIngredient = !ingredient.isEmpty();
        boolean hasFuel = !fuel.isEmpty();
        if (isLit || hasFuel && hasIngredient) {
            if (hasIngredient) {
                SingleRecipeInput input = new SingleRecipeInput(ingredient);
                Optional<RecipeHolder<@NonNull MillingRecipe>> recipe = ((ServerLevel) level).recipeAccess().getRecipeFor(ModRecipeTypes.MILLING_RECIPE_TYPE, input, level);
                if (recipe.isPresent()) {
                    ItemStack result = recipe.get().value().assemble(input);
                    if (!result.isEmpty() && canBurn(items, getMaxStackSize(), result)) {
                        // 如果isLit为false
                        if (!isLit) {
                            int burnDuration = getBurnTime(level.fuelValues(), fuel);
                            burnTime = burnDuration;
                            maxBurnTime = burnDuration;
                            if (burnDuration > 0) {
                                fuel.shrink(1);
                                isLit = true;
                                changed = true;
                            }
                        }

                        if(isLit) {
                            cookingTime++;
                            if (cookingTime == maxCookingTime) {
                                cookingTime = 0;
                                maxCookingTime = 200;
                                produceFood(result, items, 2);
                                ingredient.shrink(1);
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

    @Override
    protected void loadAdditional(@NotNull ValueInput tag) {
        super.loadAdditional(tag);
        // 加载库存中的物品
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this.items);
    }

    @Override
    protected void saveAdditional(@NotNull ValueOutput tag) {
        super.saveAdditional(tag);
        // 存储库存中的物品
        ContainerHelper.saveAllItems(tag, items);
    }

    @Override
    public void setItem(int slot, @NotNull ItemStack itemStack) {
        setItem(this.items, slot, itemStack);
    }

    @Override
    public void setItems(@NotNull NonNullList<ItemStack> items) {
        this.items = items;
    }

    @Override
    public @NotNull AbstractContainerMenu createMenu(int containerId, @NotNull Inventory inventory) {
        return new MillMenu(containerId, inventory, this, this.dataAccess);
    }

    @NotNull
    @Override
    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @NotNull
    @Override
    public Component getDefaultName() {
        return Component.translatable("container.foodcraft.mill");
    }

    @Override
    public int getContainerSize() {
        return 3;
    }
}

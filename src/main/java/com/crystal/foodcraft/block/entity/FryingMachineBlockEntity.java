package com.crystal.foodcraft.block.entity;

import com.crystal.foodcraft.network.SyncFluidTank;
import com.crystal.foodcraft.recipe.FryingRecipe;
import com.crystal.foodcraft.recipe.ModRecipeTypes;
import com.crystal.foodcraft.recipe.input.FluidAttachedRecipeInput;
import com.crystal.foodcraft.screenhandler.FryingMachineMenu;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FryingMachineBlockEntity extends BaseBlockEntity  {
    public static final int WORK_TIME = 200;
    private final NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);
    private final RecipeManager.CachedCheck<@NotNull FluidAttachedRecipeInput, @NotNull FryingRecipe> quickCheck;
    @Nullable
    private RecipeHolder<@NotNull FryingRecipe> lastRecipe = null;
    /**
     * <p>设置4000mb液体</p>
     */
    public final SyncFluidTank fluidTank = new SyncFluidTank((4 * FluidConstants.BUCKET) / 81);

    public int cookingTime = 0;
    public int burnTime = 0;
    public int maxCookingTime = 0;
    public int maxBurnTime = 0;

    public FryingMachineBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.FRYING_MACHINE, worldPosition, blockState);
        this.quickCheck = RecipeManager.createCheck(ModRecipeTypes.FRYING_RECIPE_TYPE);
    }

    @NotNull
    @Override
    public NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(@NotNull NonNullList<ItemStack> items) {
        // this.items = items;
    }

    @NotNull
    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.foodcraft.frying_machine");
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return new FryingMachineMenu(containerId, inventory);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, @NotNull FryingMachineBlockEntity fryingMachine) {
        if (level.isClientSide()) return;
        FluidAttachedRecipeInput recipeInput = new FluidAttachedRecipeInput(fryingMachine.getItem(0));

        if (fryingMachine.burnTime > 0) {
            fryingMachine.burnTime--;
            fryingMachine.setChanged();
        }

        if (fryingMachine.cookingTime > 0) {
            if (fryingMachine.burnTime > 0) {
                fryingMachine.cookingTime--;
                fryingMachine.setChanged();
            } else {
                int fuel = fryingMachine.getBurnItem(level.fuelValues(), fryingMachine.getItem(2));
                if (fuel > 0) {
                    fryingMachine.cookingTime--;
                    fryingMachine.burnTime += fuel;
                    fryingMachine.maxBurnTime = fuel;
                    fryingMachine.items.get(2).shrink(1);
                    fryingMachine.setChanged();
                    detectLit(level, blockPos, blockState, true);
                } else {
                    detectLit(level, blockPos, blockState, false);
                }
            }
        }

        if (fryingMachine.cookingTime == 0) {
            RecipeHolder<@NotNull FryingRecipe> recipeHolder = fryingMachine.quickCheck.getRecipeFor(recipeInput, (ServerLevel) level).orElse(null);
            if (recipeHolder == null) return;
            ItemStack result = recipeHolder.value().assemble(recipeInput);

            if (fryingMachine.items.get(3).isEmpty() && fryingMachine.lastRecipe != null) {
                fryingMachine.items.set(3, result);
            } else if (fryingMachine.items.get(3).is(result.getItem())) {
                fryingMachine.items.get(3).grow(1);
            }
        }

        if (fryingMachine.lastRecipe == null) {
            RecipeHolder<@NotNull FryingRecipe> recipe = fryingMachine.quickCheck.getRecipeFor(recipeInput, (ServerLevel) level).orElse(null);
            if (recipe != null) {
                if (fryingMachine.burnTime == 0 && fryingMachine.items.get(1).isEmpty()) return;
                if (!fryingMachine.items.get(3).isEmpty()) return;

                fryingMachine.lastRecipe = recipe;
                fryingMachine.cookingTime = WORK_TIME;
                fryingMachine.maxCookingTime = WORK_TIME;
                fryingMachine.setChanged();
                detectLit(level, blockPos, blockState, false);
            } else {
                fryingMachine.lastRecipe = null;
                detectLit(level, blockPos, blockState, true);
            }
        }
    }

    @Override
    public int getContainerSize() {
        return 0;
    }
}

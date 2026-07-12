package com.crystal.foodcraft.recipe.input;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import org.jetbrains.annotations.NotNull;

public class FluidAttachedRecipeInput implements RecipeInput {
    private final ItemStack item;
    private final FluidState fluidState;

    public FluidAttachedRecipeInput(ItemStack item, FluidState fluidState) {
        this.item = item;
        this.fluidState = fluidState;
    }

    @NotNull
    @Override
    public ItemStack getItem(int index) {
        return this.item;
    }

    @Override
    public int size() {
        return 2;
    }

    public FluidState getFluidState() {
        return fluidState;
    }

    public boolean isOf(Fluid fluid) {
        return fluidState.is(fluid);
    }
}

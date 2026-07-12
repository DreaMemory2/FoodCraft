package com.crystal.foodcraft.recipe;

import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeInput;
import org.jetbrains.annotations.NotNull;

public abstract class HeatedRecipe<T extends RecipeInput> implements Recipe<@NotNull T> {
    public final int minTime;
    public final int maxTime;

    public HeatedRecipe(int minTime, int maxTime) {
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public int getMinTime() {
        return minTime;
    }

    @Override
    public boolean showNotification() {
        return true;
    }

    @NotNull
    @Override
    public String group() {
        return "";
    }

    @NotNull
    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @NotNull
    @Override
    public RecipeBookCategory recipeBookCategory() {
        return new RecipeBookCategory();
    }
}

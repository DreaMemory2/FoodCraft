package com.crystal.foodcraft.compat.jei;

import com.crystal.foodcraft.recipe.*;
import mezz.jei.api.recipe.types.IRecipeHolderType;
import org.jetbrains.annotations.NotNull;

public final class ModRecipeHolderTypes {
    public static final IRecipeHolderType<@NotNull ChoppingRecipe> CHOPPING_RECIPE_TYPE = IRecipeHolderType.create(ModRecipeTypes.CHOPPING_RECIPE_TYPE);
    public static final IRecipeHolderType<@NotNull MillingRecipe> MILLING_RECIPE_TYPE = IRecipeHolderType.create(ModRecipeTypes.MILLING_RECIPE_TYPE);
    public static final IRecipeHolderType<@NotNull PottingRecipe> POTTING_RECIPE_TYPE = IRecipeHolderType.create(ModRecipeTypes.POT_RECIPE_TYPE);
    public static final IRecipeHolderType<@NotNull PanningRecipe> PANNING_RECIPE_TYPE = IRecipeHolderType.create(ModRecipeTypes.PAN_RECIPE_TYPE);
    public static final IRecipeHolderType<@NotNull FryingRecipe> FRYING_RECIPE_TYPE = IRecipeHolderType.create(ModRecipeTypes.FRYING_RECIPE_TYPE);
    public static final IRecipeHolderType<@NotNull BrewingRecipe> BREWING_RECIPE_TYPE = IRecipeHolderType.create(ModRecipeTypes.BREWING_RECIPE_TYPE);
    public static final IRecipeHolderType<@NotNull PressureCookingRecipe> PRESSURE_COOKING_RECIPE_TYPE = IRecipeHolderType.create(ModRecipeTypes.PRESSURE_COOKING_RECIPE_TYPE);
    // 饮料制作机
    // public static final IRecipeHolderType<@NotNull BeverageMakingRecipe> BEVERAGE_MAKING_RECIPE_TYPE = IRecipeHolderType.create(ModRecipeType.BEVERAGE_MAKING_RECIPE_TYPE);
}

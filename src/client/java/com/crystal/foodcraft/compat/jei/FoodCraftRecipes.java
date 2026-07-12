package com.crystal.foodcraft.compat.jei;

import com.crystal.foodcraft.recipe.*;
import net.fabricmc.fabric.api.recipe.v1.sync.SynchronizedRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FoodCraftRecipes {
    private final SynchronizedRecipes recipeManager;
    
    public FoodCraftRecipes() {
        ClientLevel level = Minecraft.getInstance().level;

        if (level != null) {
            this.recipeManager = level.recipeAccess().getSynchronizedRecipes();
        } else {
            throw new NullPointerException("Minecraft level must not be null");
        }
    }

    public List<RecipeHolder<@NotNull ChoppingRecipe>> getChoppingRecipes() {
        return recipeManager.getAllOfType(ModRecipeTypes.CHOPPING_RECIPE_TYPE).stream().toList();
    }

    public List<RecipeHolder<@NotNull MillingRecipe>> getMillingRecipes() {
        return recipeManager.getAllOfType(ModRecipeTypes.MILLING_RECIPE_TYPE).stream().toList();
    }

    public List<RecipeHolder<@NotNull PottingRecipe>> getPottingRecipes() {
        return recipeManager.getAllOfType(ModRecipeTypes.POT_RECIPE_TYPE).stream().toList();
    }

    public List<RecipeHolder<@NotNull PanningRecipe>> getPanningRecipes() {
        return recipeManager.getAllOfType(ModRecipeTypes.PAN_RECIPE_TYPE).stream().toList();
    }

    public List<RecipeHolder<@NotNull FryingRecipe>> getFryingRecipes() {
        return recipeManager.getAllOfType(ModRecipeTypes.FRYING_RECIPE_TYPE).stream().toList();
    }

    public List<RecipeHolder<@NotNull BrewingRecipe>> getBrewingRecipes() {
        return recipeManager.getAllOfType(ModRecipeTypes.BREWING_RECIPE_TYPE).stream().toList();
    }

    public List<RecipeHolder<@NotNull PressureCookingRecipe>> getPressureCookingRecipes() {
        return recipeManager.getAllOfType(ModRecipeTypes.PRESSURE_COOKING_RECIPE_TYPE).stream().toList();
    }
}

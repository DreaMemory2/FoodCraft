package com.crystal.foodcraft.compat.jei;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.block.ModBlocks;
import com.crystal.foodcraft.recipe.ChoppingRecipe;
import com.crystal.foodcraft.recipe.ModRecipeTypes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeHolderType;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.NotNull;

public class ChoppingRecipeCategory extends AbstractRecipeCategory<RecipeHolder<@NotNull ChoppingRecipe>> {
    private static final Identifier TEXTURES = FoodCraft.of("textures/gui/jei/chopping_board.png");

    public ChoppingRecipeCategory(IGuiHelper helper) {
        super(
                ModRecipeHolderTypes.CHOPPING_RECIPE_TYPE,
                Component.translatable("container.foodcraft.chopping_board"),
                helper.createDrawableItemLike(ModBlocks.CHOPPING_BOARD),
                163,
                65
        );
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull RecipeHolder<@NotNull ChoppingRecipe> recipe, @NotNull IFocusGroup focuses) {

    }

  /*@Override
  public void setRecipe(
      IRecipeLayoutBuilder recipeLayout, RecipeHolder<ChoppingRecipe> recipeHolder,
      IFocusGroup focuses) {
    var recipe = recipeHolder.value();
    var recipeIngredients = recipe.getIngredients();

    recipeLayout.addSlot(RecipeIngredientRole.INPUT, 1, 1).addIngredients(recipeIngredients.get(0));
    recipeLayout.addSlot(RecipeIngredientRole.INPUT, 28, 1).addIngredients(recipeIngredients.get(1));
    recipeLayout.addSlot(RecipeIngredientRole.INPUT, 55, 1).addIngredients(recipeIngredients.get(2));

    recipeLayout.addSlot(RecipeIngredientRole.OUTPUT, 28, 53 - 20).addItemStack( recipe.getResultItem(null));

  }*/
}

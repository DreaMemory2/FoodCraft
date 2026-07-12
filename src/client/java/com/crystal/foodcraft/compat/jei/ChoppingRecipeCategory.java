package com.crystal.foodcraft.compat.jei;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.block.ModBlocks;
import com.crystal.foodcraft.recipe.ChoppingRecipe;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChoppingRecipeCategory extends AbstractRecipeCategory<RecipeHolder<@NotNull ChoppingRecipe>> {
    private static final Identifier TEXTURES = FoodCraft.of("textures/gui/jei/chopping_board.png");
    private final IDrawable background;

    public ChoppingRecipeCategory(IGuiHelper helper) {
        super(
                ModRecipeHolderTypes.CHOPPING_RECIPE_TYPE,
                Component.translatable("container.foodcraft.chopping_board"),
                helper.createDrawableItemLike(ModBlocks.CHOPPING_BOARD),
                140 - 69 + 1,
                69 - 20 + 1
        );
        this.background = helper.createDrawable(TEXTURES, 69, 20, 140 - 69 + 1, 69 - 20 + 1);
    }

    @Override
    public void draw(@NotNull RecipeHolder<@NotNull ChoppingRecipe> recipe, @NotNull IRecipeSlotsView view, @NotNull GuiGraphicsExtractor guiGraphics, double mouseX, double mouseY) {
        this.background.draw(guiGraphics);
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull RecipeHolder<@NotNull ChoppingRecipe> holder, @NotNull IFocusGroup focuses) {
        ChoppingRecipe recipe = holder.value();
        List<Ingredient> ingredients = recipe.getIngredients();
        ItemStackTemplate result = recipe.getResult();
        // 结果输出槽位
        builder.addSlot(RecipeIngredientRole.OUTPUT, 28, 53 - 20).add(result.create());
        // 材料输入槽位
        for (int i = 0; i < ingredients.size(); i++) {
            builder.addSlot(RecipeIngredientRole.INPUT, 1 + i * 27, 1).add(ingredients.get(i));
        }
    }
}

package com.crystal.foodcraft.compat.jei;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.block.ModBlocks;
import com.crystal.foodcraft.recipe.PottingRecipe;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PottingRecipeCategory extends AbstractRecipeCategory<RecipeHolder<@NotNull PottingRecipe>> {
    public static final Identifier TEXTURES = FoodCraft.of("textures/gui/jei/pot.png");
    private final IDrawable background;
    protected final IDrawableStatic staticFlame;
    protected final IDrawableAnimated arrow;

    public PottingRecipeCategory(IGuiHelper helper) {
        super(
                ModRecipeHolderTypes.POTTING_RECIPE_TYPE,
                Component.translatable("container.foodcraft.pot"),
                helper.createDrawableItemLike(ModBlocks.POT),
                144,
                56
        );
        // 配方背景图
        this.background = helper.createDrawable(TEXTURES, 17, 16, 144, 56);
        // 火焰，表示加热效果
        this.staticFlame = helper.createDrawable(TEXTURES, 176, 0, 14, 14);
        // 制作进度箭头
        this.arrow = helper.drawableBuilder(TEXTURES, 177, 18, 20, 9)
                .buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public void draw(@NotNull RecipeHolder<@NotNull PottingRecipe> recipe, @NotNull IRecipeSlotsView view, @NotNull GuiGraphicsExtractor guiGraphics, double mouseX, double mouseY) {
        background.draw(guiGraphics);
        staticFlame.draw(guiGraphics, 81 - 17, 57 -16);
        arrow.draw(guiGraphics, 95 - 17, 21 - 16);
    }

    /* 添加槽位 */
    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull  RecipeHolder<@NotNull  PottingRecipe> holder, @NotNull IFocusGroup focuses) {
        PottingRecipe recipe = holder.value();
        List<Ingredient> ingredients = recipe.getIngredients();
        List<Ingredient> staples = recipe.getStaples();

        for (int i = 0; i < ingredients.size(); i++) {
            builder.addSlot(RecipeIngredientRole.INPUT, i * 18 + 1, 38 - 16 + 1)
                    .add(ingredients.get(i));
        }

        for (int j = 0; j < staples.size(); j++) {
            builder.addSlot(RecipeIngredientRole.INPUT, j * 18 + 1, 1)
                    .add(staples.get(j));
        }

        builder.addSlot(RecipeIngredientRole.OUTPUT, 125 - 16, 1)
                .add(recipe.getResultItem().create());
    }
}

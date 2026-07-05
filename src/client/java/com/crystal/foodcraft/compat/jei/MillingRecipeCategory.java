package com.crystal.foodcraft.compat.jei;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.block.ModBlocks;
import com.crystal.foodcraft.recipe.MillingRecipe;
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
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.NotNull;

public class MillingRecipeCategory extends AbstractRecipeCategory<RecipeHolder<@NotNull MillingRecipe>> {
    public static final Identifier TEXTURES = FoodCraft.of("textures/gui/jei/mill.png");
    private final IDrawable background;
    protected final IDrawableStatic staticFlame;
    protected final IDrawableAnimated flame;
    protected final IDrawableAnimated arrow;
    public MillingRecipeCategory(IGuiHelper helper) {
        super(
                ModRecipeHolderTypes.MILLING_RECIPE_TYPE,
                Component.translatable("container.foodcraft.mill"),
                helper.createDrawableItemLike(ModBlocks.MILL),
                144,
                64
        );
        this.background = helper.createDrawable(TEXTURES, 42, 12, 93, 64);
        this.staticFlame = helper.createDrawable(TEXTURES, 176, 0, 14, 14);
        this.flame = helper.createAnimatedDrawable(staticFlame, 300, IDrawableAnimated.StartDirection.TOP, true);
        this.arrow = helper.drawableBuilder(TEXTURES, 176, 14, 24, 17).buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public void draw(@NotNull RecipeHolder<@NotNull MillingRecipe> recipe, @NotNull IRecipeSlotsView view, @NotNull GuiGraphicsExtractor guiGraphics, double mouseX, double mouseY) {
        this.background.draw(guiGraphics);
        this.arrow.draw(guiGraphics, 74 - 40, 19 - 10);
        this.flame.draw(guiGraphics, 78 - 40, 33 - 10);
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull RecipeHolder<@NotNull MillingRecipe> holder, @NotNull IFocusGroup focuses) {
        MillingRecipe recipe = holder.value();
        Ingredient ingredient = recipe.getIngredient();
        ItemStackTemplate result = recipe.getResultItem();

        builder.addSlot(RecipeIngredientRole.INPUT, 48 - 41, 18 - 11).add(ingredient);

        builder.addSlot(RecipeIngredientRole.OUTPUT, 111 - 41, 18 - 11).add(result.create());
    }
}

package com.crystal.foodcraft.compat.jei;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.block.ModBlocks;
import com.crystal.foodcraft.item.ModItems;
import com.crystal.foodcraft.recipe.PanningRecipe;
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

public class PanningRecipeCategory extends AbstractRecipeCategory<RecipeHolder<@NotNull PanningRecipe>> {
    public static final Identifier TEXTURES = FoodCraft.of("textures/gui/jei/pan.png");
    private final IDrawable background;
    protected final IDrawableStatic staticFlame;
    protected final IDrawableAnimated arrow;

    public PanningRecipeCategory(IGuiHelper helper) {
        super(
                ModRecipeHolderTypes.PANNING_RECIPE_TYPE,
                Component.translatable("container.foodcraft.pan"),
                helper.createDrawableItemLike(ModBlocks.PAN),
                141,
                46
        );
        this.background = helper.createDrawable(TEXTURES, 12, 13, 141, 46);
        this.staticFlame = helper.createDrawable(TEXTURES, 176, 0, 14, 14);
        this.arrow = helper.drawableBuilder(TEXTURES, 176, 14, 24, 17)
                .buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public void draw(@NotNull RecipeHolder<@NotNull PanningRecipe> recipe, @NotNull IRecipeSlotsView view, @NotNull GuiGraphicsExtractor guiGraphics, double mouseX, double mouseY) {
        this.background.draw(guiGraphics);
        this.staticFlame.draw(guiGraphics, 77 - 12, 15 - 13);
        this.arrow.draw(guiGraphics, 69 - 13, 36 - 13);
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull RecipeHolder<@NotNull PanningRecipe> holder, @NotNull IFocusGroup focuses) {
        PanningRecipe recipe = holder.value();
        Ingredient input = recipe.getInput();
        ItemStackTemplate output = recipe.getOutput();
        // 输出槽位
        builder.addSlot(RecipeIngredientRole.INPUT, 105 - 12, 36 - 13).add(output.create());
        // 花生油槽位
        builder.addSlot(RecipeIngredientRole.INPUT, 15 - 12, 36 - 13).add(Ingredient.of(ModItems.PEANUT_OIL));
        // 输入槽位
        builder.addSlot(RecipeIngredientRole.INPUT, 42 - 12, 36 - 13).add(input);
    }
}

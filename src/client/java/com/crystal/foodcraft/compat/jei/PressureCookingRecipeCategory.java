package com.crystal.foodcraft.compat.jei;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.block.ModBlocks;
import com.crystal.foodcraft.recipe.PressureCookingRecipe;
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
import net.minecraft.world.level.material.FluidState;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PressureCookingRecipeCategory extends AbstractRecipeCategory<RecipeHolder<@NotNull PressureCookingRecipe>> {
    private final static Identifier TEXTURES = FoodCraft.of("textures/gui/jei/pressure_cooker.png");
    private final IDrawable background;

    protected final IDrawableStatic staticFlame;
    protected final IDrawableAnimated flame;
    protected final IDrawableAnimated arrow;

    public PressureCookingRecipeCategory(IGuiHelper helper) {
        super(
                ModRecipeHolderTypes.PRESSURE_COOKING_RECIPE_TYPE,
                Component.translatable("container.foodcraft.pressure_cooker"),
                helper.createDrawableItemLike(ModBlocks.PRESSURE_COOKER),
                162,
                64
        );
        this.background = helper.createDrawable(TEXTURES, 7, 8, 162, 64);
        this.staticFlame = helper.createDrawable(TEXTURES, 176, 0, 14, 14);
        this.flame = helper.createAnimatedDrawable(staticFlame, 300, IDrawableAnimated.StartDirection.TOP, true);
        this.arrow = helper.drawableBuilder(TEXTURES, 176, 14, 24, 17)
                .buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public void draw(@NotNull RecipeHolder<@NotNull PressureCookingRecipe> recipe, @NotNull IRecipeSlotsView view, @NotNull GuiGraphicsExtractor guiGraphics, double mouseX, double mouseY) {
        this.background.draw(guiGraphics);
        this.flame.draw(guiGraphics, 106 - 5 + 10, 55 - 6);
        this.arrow.draw(guiGraphics, 103 - 5 + 10, 24 - 6);
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull RecipeHolder<@NotNull PressureCookingRecipe> holder, @NotNull IFocusGroup focuses) {
        PressureCookingRecipe recipe = holder.value();
        List<Ingredient> ingredient = recipe.getIngredients();
        ItemStackTemplate result = recipe.getResult();
        FluidState fluid = recipe.getFluid();

        // 液体容量槽位
        builder.addSlot(RecipeIngredientRole.INPUT, 8, 4)
                .add(fluid.getType())
                .setFluidRenderer(4000, true, 11, 57);
        // 液体输入槽位
        builder.addSlot(RecipeIngredientRole.CRAFTING_STATION, 27, 41 + 6)
                .add(Ingredient.of(fluid.getType().getBucket()));

        // 材料输入槽位
        for (int i = 0; i < ingredient.size(); i++) {
            builder.addSlot(RecipeIngredientRole.INPUT, i * 24 + 33 - 6 + 10, 26 - 7)
                    .add(ingredient.get(i));
        }
        // 结果输出槽位
        builder.addSlot(RecipeIngredientRole.OUTPUT, 131 - 6 + 10, 26 - 7)
                .add(result.create());
    }
}

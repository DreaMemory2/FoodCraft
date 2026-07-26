package com.crystal.foodcraft.compat.jei;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.block.ModBlocks;
import com.crystal.foodcraft.block.entity.BeverageMakingMode;
import com.crystal.foodcraft.recipe.BeverageMakingRecipe;
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

public class BeverageMakingRecipeCategory extends AbstractRecipeCategory<RecipeHolder<@NotNull BeverageMakingRecipe>> {
    private final static Identifier TEXTURES = FoodCraft.of("textures/gui/jei/beverage_making_machine.png");

    private final IDrawable background;
    private final IDrawableAnimated arrow;
    protected final IDrawableStatic staticFlame;
    private final IDrawableAnimated cool;
    protected final IDrawableStatic staticCool;
    private final IDrawableAnimated flame;

    public BeverageMakingRecipeCategory(IGuiHelper helper) {
        super(
                ModRecipeHolderTypes.BEVERAGE_MAKING_RECIPE_TYPE,
                Component.translatable("container.foodcraft.beverage_making_machine"),
                helper.createDrawableItemLike(ModBlocks.BEVERAGE_MAKING_MACHINE),
                150,
                64
        );
        this.background = helper.createDrawable(TEXTURES, 7, 8, 150, 64);
        this.staticFlame = helper.createDrawable(TEXTURES, 176, 0, 14, 14);
        this.flame = helper.createAnimatedDrawable(staticFlame, 200, IDrawableAnimated.StartDirection.TOP, true);

        this.staticCool = helper.createDrawable(TEXTURES, 190, 0, 13, 12);
        this.cool = helper.createAnimatedDrawable(staticCool, 200, IDrawableAnimated.StartDirection.TOP, true);

        this.arrow = helper.drawableBuilder(TEXTURES, 176, 14, 24, 17)
                .buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public void draw(@NotNull RecipeHolder<@NotNull BeverageMakingRecipe> recipe, @NotNull IRecipeSlotsView view, @NotNull GuiGraphicsExtractor guiGraphics, double mouseX, double mouseY) {
        this.background.draw(guiGraphics);
        arrow.draw(guiGraphics, 47, 18);
        if (recipe.value().getMode() == BeverageMakingMode.COOL) {
            cool.draw(guiGraphics, 136, 42);
        }
        if (recipe.value().getMode() == BeverageMakingMode.HEAT) {
            flame.draw(guiGraphics, 134, 11);
        }
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull RecipeHolder<@NotNull BeverageMakingRecipe> holder, @NotNull IFocusGroup focuses) {
        BeverageMakingRecipe recipe = holder.value();
        Ingredient ingredient = recipe.getIngredient();
        ItemStackTemplate result = recipe.getResult();
        FluidState fluid = recipe.getFluid();
        // 结果输出槽位
        builder.addSlot(RecipeIngredientRole.OUTPUT, 81 - 6, 26 - 7).add(result.create());
        // 液体容量槽位
        builder.addSlot(RecipeIngredientRole.INPUT, 8, 4)
                .add(fluid.getType())
                .setFluidRenderer(4000, true, 11, 57);
        // 液体输入槽位
        builder.addSlot(RecipeIngredientRole.INPUT, 8, 4)
                .add(fluid.getType())
                .setFluidRenderer(4000, true, 11, 57);
        // 物品输入槽位
        builder.addSlot(RecipeIngredientRole.INPUT, 33 - 6, 26 - 7).add(ingredient);
    }
}

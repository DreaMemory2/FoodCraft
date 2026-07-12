package com.crystal.foodcraft.compat.jei;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.block.ModBlocks;
import com.crystal.foodcraft.item.ModItems;
import com.crystal.foodcraft.recipe.FryingRecipe;
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

public class FryingRecipeCategory extends AbstractRecipeCategory<RecipeHolder<@NotNull FryingRecipe>> {
    private final static Identifier TEXTURES = FoodCraft.of("textures/gui/jei/frying_machine.png");
    private final IDrawable background;

    protected final IDrawableStatic staticFlame;
    protected final IDrawableAnimated flame;
    protected final IDrawableAnimated arrow;

    public FryingRecipeCategory(IGuiHelper helper) {
        super(
                ModRecipeHolderTypes.FRYING_RECIPE_TYPE,
                Component.translatable("container.foodcraft.frying_machine"),
                helper.createDrawableItemLike(ModBlocks.FRYING_MACHINE),
                142,
                64
        );
        this.background = helper.createDrawable(TEXTURES, 7, 8, 142, 64);
        this.staticFlame = helper.createDrawable(TEXTURES, 176, 0, 14, 14);
        this.flame = helper.createAnimatedDrawable(staticFlame, 300, IDrawableAnimated.StartDirection.TOP, true);
        this.arrow = helper.drawableBuilder(TEXTURES, 176, 14, 24, 17)
                .buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public void draw(@NotNull RecipeHolder<@NotNull FryingRecipe> recipe, @NotNull IRecipeSlotsView view, @NotNull GuiGraphicsExtractor guiGraphics, double mouseX, double mouseY) {
        this.background.draw(guiGraphics);
        this.arrow.draw(guiGraphics, 89 - 8, 20 - 9);
        this.flame.draw(guiGraphics, 119 - 8, 52 - 9);
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull RecipeHolder<@NotNull FryingRecipe> holder, @NotNull IFocusGroup focuses) {
        FryingRecipe recipe = holder.value();
        Ingredient ingredient = recipe.getIngredient();
        ItemStackTemplate result = recipe.getResultItem();
        FluidState fluid = recipe.getFluid();

        // 结果输出槽位
        builder.addSlot(RecipeIngredientRole.OUTPUT, 126 - 6, 20 -7)
                .add(result.create());
        // 液体容量槽位
        builder.addSlot(RecipeIngredientRole.INPUT, 8, 5)
                .add(fluid.getType())
                .setFluidRenderer(4000, true, 11, 57);
        // 液体输入槽位
        builder.addSlot(RecipeIngredientRole.CRAFTING_STATION, 27, 41)
                .add(Ingredient.of(ModItems.PEANUT_OIL));

        // 材料输入槽位
        builder.addSlot(RecipeIngredientRole.INPUT, 54 - 6, 20 - 7)
                .add(ingredient);
    }
}

package com.crystal.foodcraft.compat.jei;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.block.ModBlocks;
import com.crystal.foodcraft.recipe.BrewingRecipe;
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
import net.minecraft.world.level.material.FluidState;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BrewingRecipeCategory extends AbstractRecipeCategory<RecipeHolder<@NotNull BrewingRecipe>> {
    private final static Identifier TEXTURES = FoodCraft.of("textures/gui/jei/brew_barrel.png");
    private final IDrawable background;

    public BrewingRecipeCategory(IGuiHelper helper) {
        super(
                ModRecipeHolderTypes.BREWING_RECIPE_TYPE,
                Component.translatable("container.foodcraft.brew_barrel"),
                helper.createDrawableItemLike(ModBlocks.BREW_BARREL),
                150,
                64
        );
        this.background = helper.createDrawable(TEXTURES, 7, 8, 150, 64);
    }

    @Override
    public void draw(@NotNull RecipeHolder<@NotNull BrewingRecipe> recipe, @NotNull IRecipeSlotsView view, @NotNull GuiGraphicsExtractor guiGraphics, double mouseX, double mouseY) {
        this.background.draw(guiGraphics);
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull RecipeHolder<@NotNull BrewingRecipe> holder, @NotNull IFocusGroup focuses) {
        BrewingRecipe recipe = holder.value();
        List<Ingredient> ingredients = recipe.getIngredients();
        List<ItemStackTemplate> results = recipe.getResults();
        FluidState fluid = recipe.getFluid();
        // 结果输出槽位
        for (int j = 0; j < results.size(); j++) {
            builder.addSlot(RecipeIngredientRole.INPUT, 128, 20 + 27 * j).add(results.get(j).create());
        }
        // 液体容量槽位
        builder.addSlot(RecipeIngredientRole.INPUT, 8, 5)
                .add(fluid.getType())
                .setFluidRenderer(4000, true, 11, 57);
        // 液体输入槽位
        builder.addSlot(RecipeIngredientRole.INPUT, 27, 47)
                .add(Ingredient.of(fluid.getType().getBucket()));
        // 材料输入槽位
        for (int i = 0; i < ingredients.size(); i++) {
            builder.addSlot(RecipeIngredientRole.INPUT, 44 + 24 * i, 20).add(ingredients.get(i));
        }
    }
}

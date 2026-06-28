package com.crystal.foodcraft.datagen.provider;

import com.crystal.foodcraft.recipe.MillingRecipe;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

public class MillingRecipeBuilder extends ModRecipeBuilder {
    private final ItemStackTemplate result;
    private final HolderGetter<Item> items;
    private Ingredient ingredient;

    private MillingRecipeBuilder(HolderGetter<Item> items, ItemStackTemplate result) {
        this.items = items;
        this.result = result;
    }

    public static MillingRecipeBuilder milling(HolderGetter<Item> items, Item result) {
        return new MillingRecipeBuilder(items, new ItemStackTemplate(result));
    }

    public MillingRecipeBuilder ingredient(TagKey<Item> tag) {
        this.ingredient = Ingredient.of(this.items.getOrThrow(tag));
        return this;
    }

    public MillingRecipeBuilder ingredient(ItemLike item) {
        this.ingredient = Ingredient.of(item);
        return this;
    }

    @NotNull
    @Override
    public ResourceKey<Recipe<?>> defaultId() {
        return RecipeBuilder.getDefaultRecipeId(this.result);
    }

    @Override
    public void save(@NotNull RecipeOutput consumer, @NotNull ResourceKey<Recipe<?>> id) {
        MillingRecipe recipe = new MillingRecipe(this.result, this.ingredient);
        consumer.accept(id, recipe, null);
    }
}

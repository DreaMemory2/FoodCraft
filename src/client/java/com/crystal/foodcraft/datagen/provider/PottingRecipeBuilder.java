package com.crystal.foodcraft.datagen.provider;

import com.crystal.foodcraft.recipe.PottingRecipe;
import net.minecraft.advancements.Criterion;
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

import java.util.ArrayList;
import java.util.List;

public class PottingRecipeBuilder extends ModRecipeBuilder {
    private final HolderGetter<Item> items;
    private final int minTime;
    private final int maxTime;
    private final List<Ingredient> ingredients = new ArrayList<>();
    private final List<Ingredient> staples = new ArrayList<>();
    private final ItemStackTemplate result;

    private PottingRecipeBuilder(HolderGetter<Item> items, ItemStackTemplate result, int minTime, int maxTime) {
        this.items = items;
        this.result = result;
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    public static PottingRecipeBuilder potting(HolderGetter<Item> items, ItemLike result, int minTime, int maxTime) {
        return potting(items, result, 1, minTime, maxTime);
    }

    public static PottingRecipeBuilder potting(HolderGetter<Item> items, ItemLike result, int count, int minTime, int maxTime) {
        return new PottingRecipeBuilder(items, new ItemStackTemplate(result.asItem(), count), minTime, maxTime);
    }

    public PottingRecipeBuilder ingredient(TagKey<Item> tag) {
        return this.ingredient(Ingredient.of(this.items.getOrThrow(tag)), 1);
    }

    public PottingRecipeBuilder ingredient(ItemLike item) {
        return this.ingredient(item, 1);
    }

    public PottingRecipeBuilder ingredient(ItemLike item, int count) {
        for (int i = 0; i < count; i++) {
            this.ingredient(Ingredient.of(item), 1);
        }

        return this;
    }

    public PottingRecipeBuilder ingredient(Ingredient ingredient) {
        return this.ingredient(ingredient, 1);
    }

    public PottingRecipeBuilder ingredient(Ingredient ingredient, int count) {
        for (int i = 0; i < count; i++) {
            this.ingredients.add(ingredient);
        }

        return this;
    }

    public PottingRecipeBuilder staple(TagKey<Item> tag) {
        return this.staple(Ingredient.of(this.items.getOrThrow(tag)), 1);
    }

    public PottingRecipeBuilder staple(ItemLike item) {
        return this.staple(item, 1);
    }

    public PottingRecipeBuilder staple(ItemLike item, int count) {
        for (int i = 0; i < count; i++) {
            this.staple(Ingredient.of(item), 1);
        }

        return this;
    }

    public PottingRecipeBuilder staple(Ingredient ingredient) {
        return this.staple(ingredient, 1);
    }

    public PottingRecipeBuilder staple(Ingredient ingredient, int count) {
        for (int i = 0; i < count; i++) {
            this.staples.add(ingredient);
        }

        return this;
    }

    @NotNull
    @Override
    public ResourceKey<Recipe<?>> defaultId() {
        return RecipeBuilder.getDefaultRecipeId(this.result);
    }

    @Override
    public void save(RecipeOutput consumer, @NotNull ResourceKey<Recipe<?>> id) {
        PottingRecipe recipe = new PottingRecipe(this.result, this.staples, this.ingredients, this.minTime, this.maxTime);
        consumer.accept(id, recipe, null);
    }
}

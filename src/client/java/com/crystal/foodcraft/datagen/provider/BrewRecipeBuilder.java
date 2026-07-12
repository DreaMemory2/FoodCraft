package com.crystal.foodcraft.datagen.provider;

import com.crystal.foodcraft.recipe.BrewingRecipe;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BrewRecipeBuilder extends ModRecipeBuilder {
    private final List<ItemStackTemplate> result;
    private final HolderGetter<Item> items;
    private final HolderGetter<Fluid> fluids;
    private final List<Ingredient> ingredients = new ArrayList<>();
    private FluidState fluidState;

    private BrewRecipeBuilder(HolderGetter<Item> items, HolderGetter<Fluid> fluids, List<ItemStackTemplate> result) {
        this.items = items;
        this.fluids = fluids;
        this.result = result;
    }

    public static BrewRecipeBuilder brewing(HolderGetter<Item> items, HolderGetter<Fluid> fluids, Item result, int count) {
        return new BrewRecipeBuilder(items, fluids, List.of(new ItemStackTemplate(result, count)));
    }

    public static BrewRecipeBuilder brewing(HolderGetter<Item> items, HolderGetter<Fluid> fluids, Item result1, int count1, Item result2, int count2) {
        return new BrewRecipeBuilder(items, fluids, List.of(new ItemStackTemplate(result1, count1), new ItemStackTemplate(result2, count2)));
    }

    public BrewRecipeBuilder fluidState(TagKey<Fluid> tag) {
        HolderSet.Named<Fluid> fluidNamed = this.fluids.getOrThrow(tag);
        fluidNamed.stream().forEach(holder ->
                this.fluidState = holder.value().defaultFluidState()
        );
        return this;
    }

    public BrewRecipeBuilder fluidState(Fluid fluid) {
        this.fluidState = fluid.defaultFluidState();
        return this;
    }

    public BrewRecipeBuilder ingredient(TagKey<Item> tag) {
        return this.ingredient(Ingredient.of(this.items.getOrThrow(tag)), 1);
    }

    public BrewRecipeBuilder ingredient(ItemLike item) {
        return this.ingredient(item, 1);
    }

    public BrewRecipeBuilder ingredient(ItemLike item, int count) {
        for (int i = 0; i < count; i++) {
            this.ingredient(Ingredient.of(item), 1);
        }

        return this;
    }

    public BrewRecipeBuilder ingredient(Ingredient ingredient) {
        return this.ingredient(ingredient, 1);
    }

    public BrewRecipeBuilder ingredient(Ingredient ingredient, int count) {
        for (int i = 0; i < count; i++) {
            this.ingredients.add(ingredient);
        }

        return this;
    }

    @Override
    public @NotNull ResourceKey<Recipe<?>> defaultId() {
        return RecipeBuilder.getDefaultRecipeId(this.result.getFirst());
    }

    @Override
    public void save(RecipeOutput consumer, @NotNull ResourceKey<Recipe<?>> id) {
        BrewingRecipe recipe = new BrewingRecipe(ingredients, fluidState, result);
        consumer.accept(id, recipe, null);
    }
}

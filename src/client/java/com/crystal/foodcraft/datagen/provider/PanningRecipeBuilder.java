package com.crystal.foodcraft.datagen.provider;

import com.crystal.foodcraft.recipe.PanningRecipe;
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

public class PanningRecipeBuilder extends ModRecipeBuilder {
    private final HolderGetter<Item> items;
    private final int minTime;
    private final int maxTime;
    private Ingredient input;
    private final ItemStackTemplate output;

    private PanningRecipeBuilder(HolderGetter<Item> items, ItemStackTemplate output, int minTime, int maxTime) {
        this.items = items;
        this.output = output;
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    public static PanningRecipeBuilder panning(HolderGetter<Item> items, ItemLike result, int minTime, int maxTime) {
        return panning(items, result, 1, minTime, maxTime);
    }

    private static PanningRecipeBuilder panning(HolderGetter<Item> items, ItemLike result, int count, int minTime, int maxTime) {
        return new PanningRecipeBuilder(items, new ItemStackTemplate(result.asItem(), count), minTime, maxTime);
    }

    public PanningRecipeBuilder ingredient(TagKey<Item> tag) {
        this.input = Ingredient.of(this.items.getOrThrow(tag));
        return this;
    }

    public PanningRecipeBuilder ingredient(ItemLike item) {
        this.input = Ingredient.of(item);
        return this;
    }

    @NotNull
    @Override
    public ResourceKey<Recipe<?>> defaultId() {
        return RecipeBuilder.getDefaultRecipeId(this.output);
    }

    @Override
    public void save(RecipeOutput consumer, @NotNull ResourceKey<Recipe<?>> id) {
        PanningRecipe recipe = new PanningRecipe(this.output, this.input, this.minTime, this.maxTime);
        consumer.accept(id, recipe, null);
    }
}

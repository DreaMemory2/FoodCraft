package com.crystal.foodcraft.datagen.provider;

import com.crystal.foodcraft.recipe.ChoppingRecipe;
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

public class ChoppingRecipeBuilder extends ModRecipeBuilder {
    private final ItemStackTemplate result;
    private final HolderGetter<Item> items;
    private final List<Ingredient> ingredients = new ArrayList<>();

    private ChoppingRecipeBuilder(HolderGetter<Item> items, ItemStackTemplate result) {
        this.items = items;
        this.result = result;
    }

    public static ChoppingRecipeBuilder chopping(HolderGetter<Item> items, ItemLike result, int count) {
        return new ChoppingRecipeBuilder(items, new ItemStackTemplate(result.asItem(), count));
    }

    public ChoppingRecipeBuilder ingredient(TagKey<Item> tag) {
        return this.ingredient(Ingredient.of(this.items.getOrThrow(tag)), 1);
    }

    public ChoppingRecipeBuilder ingredient(ItemLike item, int count) {
        if (count <= 3) {
            for (int i = 0; i < count; i++) {
                this.ingredient(Ingredient.of(item), 1);
            }
        }

        return this;
    }

    public ChoppingRecipeBuilder ingredient(Ingredient ingredient, int count) {
        for (int i = 0; i < count; i++) {
            this.ingredients.add(ingredient);
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
        ChoppingRecipe recipe = new ChoppingRecipe(this.result, this.ingredients);
        consumer.accept(id, recipe, null);
    }
}

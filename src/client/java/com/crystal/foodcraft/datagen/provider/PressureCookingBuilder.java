package com.crystal.foodcraft.datagen.provider;

import com.crystal.foodcraft.recipe.PressureCookingRecipe;
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

public class PressureCookingBuilder extends ModRecipeBuilder {
    private final ItemStackTemplate result;
    private final HolderGetter<Item> items;
    private final HolderGetter<Fluid> fluids;
    private final List<Ingredient> ingredients = new ArrayList<>();
    private FluidState fluidState;

    private PressureCookingBuilder(HolderGetter<Item> items, HolderGetter<Fluid> fluids, ItemStackTemplate result) {
        this.items = items;
        this.fluids = fluids;
        this.result = result;
    }

    public static PressureCookingBuilder pressureCooking(HolderGetter<Item> items, HolderGetter<Fluid> fluids, Item result) {
        return pressureCooking(items, fluids, result, 1);
    }

    public static PressureCookingBuilder pressureCooking(HolderGetter<Item> items, HolderGetter<Fluid> fluids, Item result, int count) {
        return new PressureCookingBuilder(items, fluids, new ItemStackTemplate(result, count));
    }

    public PressureCookingBuilder fluidState(TagKey<Fluid> tag) {
        HolderSet.Named<Fluid> fluidNamed = this.fluids.getOrThrow(tag);
        fluidNamed.stream().forEach(holder ->
                this.fluidState = holder.value().defaultFluidState()
        );
        return this;
    }

    public PressureCookingBuilder fluidState(Fluid fluid) {
        this.fluidState = fluid.defaultFluidState();
        return this;
    }

    public PressureCookingBuilder ingredient(TagKey<Item> tag) {
        return this.ingredient(Ingredient.of(this.items.getOrThrow(tag)), 1);
    }

    public PressureCookingBuilder ingredient(ItemLike item) {
        return this.ingredient(item, 1);
    }

    public PressureCookingBuilder ingredient(ItemLike item, int count) {
        for (int i = 0; i < count; i++) {
            this.ingredient(Ingredient.of(item), 1);
        }

        return this;
    }

    public PressureCookingBuilder ingredient(Ingredient ingredient) {
        return this.ingredient(ingredient, 1);
    }

    public PressureCookingBuilder ingredient(Ingredient ingredient, int count) {
        for (int i = 0; i < count; i++) {
            this.ingredients.add(ingredient);
        }

        return this;
    }

    @Override
    public @NotNull ResourceKey<Recipe<?>> defaultId() {
        return RecipeBuilder.getDefaultRecipeId(this.result);
    }

    @Override
    public void save(RecipeOutput consumer, @NotNull ResourceKey<Recipe<?>> id) {
        PressureCookingRecipe recipe = new PressureCookingRecipe(ingredients, fluidState, result);
        consumer.accept(id, recipe, null);
    }
}

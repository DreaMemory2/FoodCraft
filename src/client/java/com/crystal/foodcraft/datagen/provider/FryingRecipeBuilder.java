package com.crystal.foodcraft.datagen.provider;

import com.crystal.foodcraft.recipe.FryingRecipe;
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

public class FryingRecipeBuilder extends ModRecipeBuilder {
    private final ItemStackTemplate result;
    private final HolderGetter<Item> items;
    private final HolderGetter<Fluid> fluids;
    private FluidState fluidState;
    private Ingredient ingredient;

    private FryingRecipeBuilder(HolderGetter<Item> items, HolderGetter<Fluid> fluids, ItemStackTemplate result) {
        this.items = items;
        this.fluids = fluids;
        this.result = result;
    }

    public static FryingRecipeBuilder frying(HolderGetter<Item> items, HolderGetter<Fluid> fluids, Item result) {
        return new FryingRecipeBuilder(items, fluids, new ItemStackTemplate(result));
    }

    public FryingRecipeBuilder fluidState(TagKey<Fluid> tag) {
        HolderSet.Named<Fluid> fluidNamed = this.fluids.getOrThrow(tag);
        fluidNamed.stream().forEach(holder ->
                this.fluidState = holder.value().defaultFluidState()
        );
        return this;
    }

    public FryingRecipeBuilder fluidState(Fluid fluid) {
        this.fluidState = fluid.defaultFluidState();
        return this;
    }

    public FryingRecipeBuilder ingredient(TagKey<Item> tag) {
        this.ingredient = Ingredient.of(this.items.getOrThrow(tag));
        return this;
    }

    public FryingRecipeBuilder ingredient(ItemLike item) {
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
        FryingRecipe recipe = new FryingRecipe(result, ingredient, fluidState);
        consumer.accept(id, recipe, null);
    }
}

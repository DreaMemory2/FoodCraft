package com.crystal.foodcraft.datagen.provider;

import com.crystal.foodcraft.block.entity.BeverageMakingMode;
import com.crystal.foodcraft.recipe.BeverageMakingRecipe;
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

public class BeverageMakingBuilder extends ModRecipeBuilder {
    private final ItemStackTemplate result;
    private final BeverageMakingMode mode;
    private final HolderGetter<Item> items;
    private final HolderGetter<Fluid> fluids;
    private Ingredient ingredient;
    private FluidState fluidState;

    private BeverageMakingBuilder(HolderGetter<Item> items, HolderGetter<Fluid> fluids, BeverageMakingMode mode, ItemStackTemplate result) {
        this.items = items;
        this.mode = mode;
        this.fluids = fluids;
        this.result = result;
    }


    public static BeverageMakingBuilder beverageMaking(HolderGetter<Item> items, HolderGetter<Fluid> fluids, BeverageMakingMode mode, Item result) {
        return beverageMaking(items, fluids, mode, result, 1);
    }

    public static BeverageMakingBuilder beverageMaking(HolderGetter<Item> items, HolderGetter<Fluid> fluids, BeverageMakingMode mode, Item result, int count) {
        return new BeverageMakingBuilder(items, fluids, mode, new ItemStackTemplate(result, count));
    }

    public static BeverageMakingBuilder beverageMaking(HolderGetter<Item> items, HolderGetter<Fluid> fluids, BeverageMakingMode mode, ItemStackTemplate result) {
        return new BeverageMakingBuilder(items, fluids, mode, result);
    }

    public BeverageMakingBuilder fluidState(TagKey<Fluid> tag) {
        HolderSet.Named<Fluid> fluidNamed = this.fluids.getOrThrow(tag);
        fluidNamed.stream().forEach(holder ->
                this.fluidState = holder.value().defaultFluidState()
        );
        return this;
    }

    public BeverageMakingBuilder fluidState(Fluid fluid) {
        this.fluidState = fluid.defaultFluidState();
        return this;
    }

    public BeverageMakingBuilder ingredient(TagKey<Item> tag) {
        this.ingredient = Ingredient.of(this.items.getOrThrow(tag));
        return this;
    }

    public BeverageMakingBuilder ingredient(ItemLike item) {
        this.ingredient = Ingredient.of(item);
        return this;
    }

    @Override
    public @NotNull ResourceKey<Recipe<?>> defaultId() {
        return RecipeBuilder.getDefaultRecipeId(this.result);
    }

    @Override
    public void save(RecipeOutput consumer, @NotNull ResourceKey<Recipe<?>> id) {
        BeverageMakingRecipe recipe = new BeverageMakingRecipe(mode, ingredient, fluidState, result);
        consumer.accept(id, recipe, null);
    }
}

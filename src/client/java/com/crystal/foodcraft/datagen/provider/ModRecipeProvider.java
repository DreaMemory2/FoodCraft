package com.crystal.foodcraft.datagen.provider;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.level.ItemLike;

public abstract class ModRecipeProvider extends RecipeProvider {
    private final HolderGetter<Item> items;

    protected ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);

        this.items = registries.lookupOrThrow(Registries.ITEM);
    }

    public ChoppingRecipeBuilder chopping(ItemLike result, int count) {
        return ChoppingRecipeBuilder.chopping(this.items, result, count);
    }

    public MillingRecipeBuilder milling(Item result) {
        return MillingRecipeBuilder.milling(this.items, result);
    }

    public PottingRecipeBuilder potting(Item result, int count, int minTime, int maxTime) {
        return PottingRecipeBuilder.potting(this.items, result, count, minTime, maxTime);
    }

    public PottingRecipeBuilder potting(Item result, int minTime, int maxTime) {
        return PottingRecipeBuilder.potting(this.items, result, minTime, maxTime);
    }

    public PanningRecipeBuilder panning(Item result, int minTime, int maxTime) {
        return PanningRecipeBuilder.panning(this.items, result, minTime, maxTime);
    }
}

package com.crystal.foodcraft.datagen.provider;

import com.crystal.foodcraft.FoodCraft;
import net.minecraft.advancements.Criterion;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeUnlockAdvancementBuilder;
import net.minecraft.resources.ResourceKey;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

public abstract class ModRecipeBuilder implements RecipeBuilder {
    private final RecipeUnlockAdvancementBuilder advancementBuilder = new RecipeUnlockAdvancementBuilder();

    @Override
    public @NotNull RecipeBuilder unlockedBy(@NotNull String name, @NotNull Criterion<?> criterion) {
        this.advancementBuilder.unlockedBy(name, criterion);
        return this;
    }

    @NotNull
    @Override
    public RecipeBuilder group(@Nullable String group) {
        return this;
    }

    @Override
    public void save(@NotNull RecipeOutput consumer, @NotNull String path) {
        save(consumer, ResourceKey.create(Registries.RECIPE, FoodCraft.of(path)));
    }
}

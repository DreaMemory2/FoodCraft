package com.crystal.foodcraft.recipe;

import com.google.gson.JsonParseException;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class MillingRecipe implements Recipe<@NotNull RecipeInput> {
    public static final MapCodec<MillingRecipe> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    ItemStackTemplate.CODEC.fieldOf("output").forGetter(recipe -> recipe.output),
                    Ingredient.CODEC.fieldOf("ingredient").forGetter(recipe -> recipe.ingredient)
            ).apply(instance, MillingRecipe::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, MillingRecipe> PACKET_CODEC = StreamCodec.composite(
            ItemStackTemplate.STREAM_CODEC,
            recipe -> recipe.output,
            Ingredient.CONTENTS_STREAM_CODEC,
            recipe -> recipe.ingredient,
            MillingRecipe::new
    );
    private final ItemStackTemplate output;
    private final Ingredient ingredient;

    public MillingRecipe(ItemStackTemplate output, Ingredient ingredient) {
        this.output = output;

        if (ingredient.isEmpty()) {
            throw new JsonParseException("No ingredients for milling recipe");
        }

        this.ingredient = ingredient;
    }

    @Override
    public boolean matches(RecipeInput container, @NotNull Level level) {
        return this.ingredient.test(container.getItem(0));
    }

    @NotNull
    @Override
    public ItemStack assemble(RecipeInput input) {
        return this.output.create();
    }

    @Override
    public boolean showNotification() {
        return true;
    }

    @NotNull
    @Override
    public String group() {
        return "";
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @NotNull
    @Override
    public RecipeSerializer<? extends @NotNull Recipe<@NotNull RecipeInput>> getSerializer() {
        return ModRecipeTypes.MILLING_RECIPE_SERIALIZER;
    }

    @NotNull
    @Override
    public RecipeType<? extends @NotNull Recipe<@NotNull RecipeInput>> getType() {
        return ModRecipeTypes.MILLING_RECIPE_TYPE;
    }

    @NotNull
    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @NotNull
    @Override
    public RecipeBookCategory recipeBookCategory() {
        return new RecipeBookCategory();
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public ItemStackTemplate getResultItem() {
        return output;
    }
}

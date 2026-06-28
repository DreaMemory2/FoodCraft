package com.crystal.foodcraft.recipe;

import com.crystal.foodcraft.util.RecipeMatcher;
import com.google.gson.JsonParseException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PottingRecipe implements Recipe<@NotNull RecipeInput> {
    public static final int INGREDIENT_SLOTS = 8;
    public static final int STAPLE_SLOTS = 4;
    public static final MapCodec<PottingRecipe> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    ItemStackTemplate.CODEC.fieldOf("output").forGetter(recipe -> recipe.output),
                    Ingredient.CODEC.listOf().fieldOf("ingredients").forGetter(recipe -> recipe.ingredients),
                    Ingredient.CODEC.listOf().fieldOf("staples").forGetter(recipe -> recipe.staples),
                    Codec.INT.optionalFieldOf("minTime", 400).forGetter(recipe -> recipe.minTime),
                    Codec.INT.optionalFieldOf("maxTime", 1000).forGetter(recipe -> recipe.maxTime)
            ).apply(instance, PottingRecipe::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, PottingRecipe> PACKET_CODEC = StreamCodec.composite(
            ItemStackTemplate.STREAM_CODEC,
            recipe -> recipe.output,
            Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()),
            recipe -> recipe.ingredients,
            Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()),
            recipe -> recipe.staples,
            ByteBufCodecs.INT,
            recipe -> recipe.minTime,
            ByteBufCodecs.INT,
            recipe -> recipe.maxTime,
            PottingRecipe::new
    );

    private final ItemStackTemplate output;
    private final List<Ingredient> ingredients;
    private final List<Ingredient> staples;
    private final int minTime;
    private final int maxTime;

    public PottingRecipe(ItemStackTemplate output, List<Ingredient> ingredients, List<Ingredient> staples, int minTime, int maxTime) {
        this.output = output;

        if (ingredients.isEmpty()) {
            throw new JsonParseException("No ingredients for pot recipe");
        } else if (ingredients.size() > INGREDIENT_SLOTS) {
            throw new JsonParseException(
                    "Too many ingredients for pot recipe! The max is 8");
        }

        if (staples.size() > STAPLE_SLOTS) {
            throw new JsonParseException(
                    "Too many staples for pot recipe! The max is 4");
        }

        this.ingredients = ingredients;
        this.staples = staples;
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    @Override
    public boolean matches(RecipeInput container, @NotNull Level level) {
        ArrayList<ItemStack> inputs = new ArrayList<>();
        int inputSize = 0;
        for (int i = 0; i < INGREDIENT_SLOTS; ++i) {
            ItemStack stack = container.getItem(i);
            if (!stack.isEmpty()) {
                ++inputSize;
                inputs.add(stack);
            }
        }

        ArrayList<ItemStack> stapleInputs = new ArrayList<>();
        int stapleSize = 0;
        for (int j = 0; j < STAPLE_SLOTS; ++j) {
            ItemStack stack = container.getItem(INGREDIENT_SLOTS + j);
            if (!stack.isEmpty()) {
                ++stapleSize;
                stapleInputs.add(stack);
            }
        }

        return this.ingredients.size() <= inputSize && RecipeMatcher.findMatches(inputs, ingredients) != null
                && this.staples.size() <= stapleSize && RecipeMatcher.findMatches(stapleInputs, staples) != null;
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

    @NotNull
    @Override
    public RecipeSerializer<? extends @NotNull Recipe<@NotNull RecipeInput>> getSerializer() {
        return ModRecipeTypes.POT_RECIPE_SERIALIZER;
    }

    @NotNull
    @Override
    public RecipeType<? extends @NotNull Recipe<@NotNull RecipeInput>> getType() {
        return ModRecipeTypes.POT_RECIPE_TYPE;
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

    public int getMinTime() {
        return this.minTime;
    }


    public int getMaxTime() {
        return this.maxTime;
    }
}

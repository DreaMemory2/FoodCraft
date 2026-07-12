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

public class PottingRecipe extends HeatedRecipe<@NotNull RecipeInput> {
    public static final int INGREDIENT_SLOTS = 8;
    public static final int STAPLE_SLOTS = 4;
    public static final MapCodec<PottingRecipe> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    ItemStackTemplate.CODEC.fieldOf("output").forGetter(recipe -> recipe.output),
                    Ingredient.CODEC.listOf().fieldOf("staples").forGetter(recipe -> recipe.staples),
                    Ingredient.CODEC.listOf().fieldOf("ingredients").forGetter(recipe -> recipe.ingredients),
                    Codec.INT.optionalFieldOf("minTime", 400).forGetter(recipe -> recipe.minTime),
                    Codec.INT.optionalFieldOf("maxTime", 1000).forGetter(recipe -> recipe.maxTime)
            ).apply(instance, PottingRecipe::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, PottingRecipe> PACKET_CODEC = StreamCodec.composite(
            ItemStackTemplate.STREAM_CODEC,
            recipe -> recipe.output,
            Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()),
            recipe -> recipe.staples,
            Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()),
            recipe -> recipe.ingredients,
            ByteBufCodecs.INT,
            recipe -> recipe.minTime,
            ByteBufCodecs.INT,
            recipe -> recipe.maxTime,
            PottingRecipe::new
    );

    private final ItemStackTemplate output;
    private final List<Ingredient> staples;
    private final List<Ingredient> ingredients;

    public PottingRecipe(ItemStackTemplate output, List<Ingredient> staples, List<Ingredient> ingredients, int minTime, int maxTime) {
        super(minTime, maxTime);
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

        this.staples = staples;
        this.ingredients = ingredients;
    }

    @Override
    public boolean matches(RecipeInput container, @NotNull Level level) {
        ArrayList<ItemStack> stapleInputs = new ArrayList<>();
        int stapleSize = 0;
        for (int i = 0; i < 4; i++) {
            ItemStack stack = container.getItem(i);
            if (!stack.isEmpty()) {
                ++stapleSize;
                stapleInputs.add(stack);
            }
        }

        ArrayList<ItemStack> ingredientInputs = new ArrayList<>();
        int inputSize = 0;
        for (int j = 4; j < 8; j++) {
            ItemStack stack = container.getItem(j);
            if (!stack.isEmpty()) {
                ++inputSize;
                ingredientInputs.add(stack);
            }
        }

        return this.staples.size() <= stapleSize && RecipeMatcher.findMatches(stapleInputs, staples) != null
                && this.ingredients.size() <= inputSize && RecipeMatcher.findMatches(ingredientInputs, ingredients) != null;
    }

    @NotNull
    @Override
    public ItemStack assemble(RecipeInput input) {
        return this.output.create();
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Ingredient> getStaples() {
        return staples;
    }

    public ItemStackTemplate getResultItem() {
        return output;
    }
}

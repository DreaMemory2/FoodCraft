package com.crystal.foodcraft.recipe;

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

public class PanningRecipe extends HeatedRecipe<RecipeInput> {
    public static final MapCodec<PanningRecipe> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    ItemStackTemplate.CODEC.fieldOf("output").forGetter(recipe -> recipe.output),
                    Ingredient.CODEC.fieldOf("input").forGetter(recipe -> recipe.input),
                    Codec.INT.fieldOf("minTime").forGetter(recipe -> recipe.minTime),
                    Codec.INT.fieldOf("maxTime").forGetter(recipe -> recipe.maxTime)
            ).apply(instance, PanningRecipe::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, PanningRecipe> PACKET_CODEC = StreamCodec.composite(
            ItemStackTemplate.STREAM_CODEC,
            recipe -> recipe.output,
            Ingredient.CONTENTS_STREAM_CODEC,
            recipe -> recipe.input,
            ByteBufCodecs.INT,
            recipe -> recipe.minTime,
            ByteBufCodecs.INT,
            recipe -> recipe.maxTime,
            PanningRecipe::new
    );
    private final ItemStackTemplate output;
    private final Ingredient input;

    public PanningRecipe(ItemStackTemplate output, Ingredient input, int minTime, int maxTime) {
        super(minTime, maxTime);
        this.output = output;

        if (input.isEmpty()) {
            throw new JsonParseException("No ingredients for pan recipe");
        }

        this.input = input;
    }

    @Override
    public boolean matches(RecipeInput container, @NotNull Level level) {
        return this.input.test(container.getItem(0));
    }

    @NotNull
    @Override
    public ItemStack assemble(RecipeInput input) {
        return this.output.create();
    }

    @NotNull
    @Override
    public RecipeSerializer<? extends @NotNull Recipe<@NotNull RecipeInput>> getSerializer() {
        return ModRecipeTypes.PAN_RECIPE_SERIALIZER;
    }

    @NotNull
    @Override
    public RecipeType<? extends @NotNull Recipe<@NotNull RecipeInput>> getType() {
        return ModRecipeTypes.PAN_RECIPE_TYPE;
    }

    public Ingredient getInput() {
        return input;
    }

    public ItemStackTemplate getOutput() {
        return output;
    }
}

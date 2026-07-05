package com.crystal.foodcraft.recipe;

import com.crystal.foodcraft.recipe.input.FluidAttachedRecipeInput;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import org.jetbrains.annotations.NotNull;

public class FryingRecipe implements Recipe<@NotNull FluidAttachedRecipeInput> {
    public static final MapCodec<FryingRecipe> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    ItemStackTemplate.CODEC.fieldOf("result").forGetter(recipe -> recipe.result),
                    Ingredient.CODEC.fieldOf("ingredient").forGetter(recipe -> recipe.ingredient),
                    FluidState.CODEC.fieldOf("fluidInput").forGetter(recipe -> recipe.fluidInput)
            ).apply(instance, FryingRecipe::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, FryingRecipe> PACKET_CODEC = StreamCodec.composite(
            ItemStackTemplate.STREAM_CODEC,
            recipe -> recipe.result,
            Ingredient.CONTENTS_STREAM_CODEC,
            recipe -> recipe.ingredient,
            ByteBufCodecs.fromCodec(FluidState.CODEC),
            recipe -> recipe.fluidInput,
            FryingRecipe::new
    );
    private final ItemStackTemplate result;
    private final Ingredient ingredient;
    private final FluidState fluidInput;

    public FryingRecipe(ItemStackTemplate result, Ingredient ingredient, FluidState fluidInput) {
        this.result = result;
        this.ingredient = ingredient;
        this.fluidInput = fluidInput;
    }

    @Override
    public boolean matches(FluidAttachedRecipeInput container, @NotNull Level level) {
        return this.ingredient.test(container.getItem(0))
                && container.isOf(this.fluidInput.getType());
    }

    @NotNull
    @Override
    public ItemStack assemble(FluidAttachedRecipeInput input) {
        return this.result.create();
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
    public RecipeSerializer<? extends @NotNull Recipe<@NotNull FluidAttachedRecipeInput>> getSerializer() {
        return ModRecipeTypes.FRYING_RECIPE_SERIALIZER;
    }

    @NotNull
    @Override
    public RecipeType<? extends @NotNull Recipe<@NotNull FluidAttachedRecipeInput>> getType() {
        return ModRecipeTypes.FRYING_RECIPE_TYPE;
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
        return result;
    }

    public FluidState getFluid() {
        return fluidInput;
    }
}

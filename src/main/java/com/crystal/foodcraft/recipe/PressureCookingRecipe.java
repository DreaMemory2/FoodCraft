package com.crystal.foodcraft.recipe;

import com.crystal.foodcraft.recipe.input.PressureCookerInput;
import com.crystal.foodcraft.util.RecipeMatcher;
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

import java.util.ArrayList;
import java.util.List;

public class PressureCookingRecipe implements Recipe<@NotNull PressureCookerInput> {
    public static final MapCodec<PressureCookingRecipe> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    Ingredient.CODEC.listOf().fieldOf("ingredient").forGetter(recipe -> recipe.ingredients),
                    FluidState.CODEC.fieldOf("fluidInput").forGetter(recipe -> recipe.fluidState),
                    ItemStackTemplate.CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
            ).apply(instance, PressureCookingRecipe::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, PressureCookingRecipe> PACKET_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()),
            recipe -> recipe.ingredients,
            ByteBufCodecs.fromCodec(FluidState.CODEC),
            recipe -> recipe.fluidState,
            ItemStackTemplate.STREAM_CODEC,
            recipe -> recipe.result,
            PressureCookingRecipe::new
    );
    private final List<Ingredient> ingredients;
    private final FluidState fluidState;
    private final ItemStackTemplate result;

    public PressureCookingRecipe(List<Ingredient> ingredients, FluidState fluidState, ItemStackTemplate result) {
        this.ingredients = ingredients;
        this.fluidState = fluidState;
        this.result = result;
    }

    @Override
    public boolean matches(PressureCookerInput input, @NotNull Level level) {
        List<ItemStack> ingredients = new ArrayList<>();
        int size = 0;
        for (int i = 0; i < this.ingredients.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (!stack.isEmpty()) {
                ++size;
                ingredients.add(stack);
            }
        }
        return this.ingredients.size() <= size && RecipeMatcher.findMatches(ingredients, this.ingredients) != null
                && input.isOf(this.fluidState.getType());
    }

    @Override
    public @NotNull ItemStack assemble(PressureCookerInput input) {
        return this.result.create();
    }

    @Override
    public boolean showNotification() {
        return true;
    }

    @Override
    public @NotNull String group() {
        return "";
    }

    @Override
    public @NotNull RecipeSerializer<? extends @NotNull Recipe<@NotNull PressureCookerInput>> getSerializer() {
        return ModRecipeTypes.PRESSURE_COOKING_SERIALIZER;
    }

    @Override
    public @NotNull RecipeType<? extends @NotNull Recipe<@NotNull PressureCookerInput>> getType() {
        return ModRecipeTypes.PRESSURE_COOKING_RECIPE_TYPE;
    }

    @Override
    public @NotNull PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public @NotNull RecipeBookCategory recipeBookCategory() {
        return new RecipeBookCategory();
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public FluidState getFluid() {
        return fluidState;
    }

    public ItemStackTemplate getResult() {
        return result;
    }
}

package com.crystal.foodcraft.recipe;

import com.crystal.foodcraft.api.FabricStreamCodecs;
import com.crystal.foodcraft.block.entity.BeverageMakingMode;
import com.crystal.foodcraft.recipe.input.BMMachineInput;
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

public class BeverageMakingRecipe implements Recipe<@NotNull BMMachineInput> {
    public static final MapCodec<BeverageMakingRecipe> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    BeverageMakingMode.CODEC.fieldOf("mode").forGetter(recipe -> recipe.mode),
                    Ingredient.CODEC.fieldOf("ingredient").forGetter(recipe -> recipe.ingredient),
                    FluidState.CODEC.fieldOf("fluidInput").forGetter(recipe -> recipe.fluidState),
                    ItemStackTemplate.CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
            ).apply(instance, BeverageMakingRecipe::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, BeverageMakingRecipe> PACKET_CODEC = StreamCodec.composite(
            FabricStreamCodecs.enumCodec(BeverageMakingMode.class),
            recipe -> recipe.mode,
            Ingredient.CONTENTS_STREAM_CODEC,
            recipe -> recipe.ingredient,
            ByteBufCodecs.fromCodec(FluidState.CODEC),
            recipe -> recipe.fluidState,
            ItemStackTemplate.STREAM_CODEC,
            recipe -> recipe.result,
            BeverageMakingRecipe::new
    );

    private final BeverageMakingMode mode;
    private final Ingredient ingredient;
    private final FluidState fluidState;
    private final ItemStackTemplate result;

    public BeverageMakingRecipe(BeverageMakingMode mode, Ingredient ingredient, FluidState fluidState, ItemStackTemplate result) {
        this.mode = mode;
        this.ingredient = ingredient;
        this.fluidState = fluidState;
        this.result = result;
    }

    @Override
    public boolean matches(BMMachineInput input, @NotNull Level level) {
        if (input.getMode() != null) {
            return matches(input.getMode(), input);
        }
        return false;
    }

    public boolean matches(BeverageMakingMode mode, BMMachineInput input) {
        if (this.getMode() != mode) {
            return false;
        }

        return ingredient.test(input.getItem(0));
    }

    @Override
    public @NotNull ItemStack assemble(BMMachineInput input) {
        return this.result.create();
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public @NotNull String group() {
        return "";
    }

    @Override
    public @NotNull RecipeSerializer<? extends @NotNull Recipe<@NotNull BMMachineInput>> getSerializer() {
        return ModRecipeTypes.BEVERAGE_MAKING_SERIALIZER;
    }

    @Override
    public @NotNull RecipeType< ? extends @NotNull Recipe<@NotNull BMMachineInput>> getType() {
        return ModRecipeTypes.BEVERAGE_MAKING_RECIPE_TYPE;
    }

    @Override
    public @NotNull PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public @NotNull RecipeBookCategory recipeBookCategory() {
        return new RecipeBookCategory();
    }

    public BeverageMakingMode getMode() {
        return this.mode;
    }

    public Ingredient getIngredient() {
        return this.ingredient;
    }

    public ItemStackTemplate getResult() {
        return this.result;
    }

    public FluidState getFluid() {
        return this.fluidState;
    }
}

package com.crystal.foodcraft.recipe;

import com.crystal.foodcraft.FoodCraft;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

public class ModRecipeTypes {
    public static final RecipeType<@NonNull ChoppingRecipe> CHOPPING_RECIPE_TYPE = Registry.register(
            BuiltInRegistries.RECIPE_TYPE,
            FoodCraft.of("chopping"),
            new RecipeType<@NonNull ChoppingRecipe>() {}
    );
    public static final RecipeType<@NonNull MillingRecipe> MILLING_RECIPE_TYPE = Registry.register(
            BuiltInRegistries.RECIPE_TYPE,
            FoodCraft.of("milling"),
            new RecipeType<@NonNull MillingRecipe>() {}
    );
    public static final RecipeType<@NonNull PottingRecipe> POT_RECIPE_TYPE = Registry.register(
            BuiltInRegistries.RECIPE_TYPE,
            FoodCraft.of("potting"),
            new RecipeType<@NonNull PottingRecipe>() {}
    );
    public static final RecipeType<@NotNull PanningRecipe> PAN_RECIPE_TYPE = Registry.register(
            BuiltInRegistries.RECIPE_TYPE,
            FoodCraft.of("panning"),
            new RecipeType<@NonNull PanningRecipe>() {}
    );
    public static final RecipeType<@NonNull FryingRecipe> FRYING_RECIPE_TYPE = Registry.register(
            BuiltInRegistries.RECIPE_TYPE,
            FoodCraft.of("frying"),
            new RecipeType<@NonNull FryingRecipe>() {}
    );

    public static final RecipeSerializer<@NonNull ChoppingRecipe> CHOPPING_RECIPE_SERIALIZER = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER,
            FoodCraft.of("chopping"),
            new RecipeSerializer<>(ChoppingRecipe.CODEC, ChoppingRecipe.PACKET_CODEC)
    );
    public static final RecipeSerializer<@NonNull MillingRecipe> MILLING_RECIPE_SERIALIZER = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER,
            FoodCraft.of("milling"),
            new RecipeSerializer<>(MillingRecipe.CODEC, MillingRecipe.PACKET_CODEC)
    );
    public static final RecipeSerializer<@NonNull PottingRecipe> POT_RECIPE_SERIALIZER = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER,
            FoodCraft.of("potting"),
            new RecipeSerializer<>(PottingRecipe.CODEC, PottingRecipe.PACKET_CODEC)
    );
    public static final RecipeSerializer<@NotNull PanningRecipe> PAN_RECIPE_SERIALIZER = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER,
            FoodCraft.of("panning"),
            new RecipeSerializer<>(PanningRecipe.CODEC, PanningRecipe.PACKET_CODEC)
    );
    public static final RecipeSerializer<@NotNull FryingRecipe> FRYING_RECIPE_SERIALIZER = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER,
            FoodCraft.of("frying"),
            new RecipeSerializer<>(FryingRecipe.CODEC, FryingRecipe.PACKET_CODEC)
    );
}

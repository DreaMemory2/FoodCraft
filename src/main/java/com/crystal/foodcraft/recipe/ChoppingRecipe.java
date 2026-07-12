package com.crystal.foodcraft.recipe;

import com.google.gson.JsonParseException;
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

import java.util.List;

/**
 * @see ShapelessRecipe
 */
public class ChoppingRecipe implements Recipe<@NotNull CraftingInput> {
    public static final int INGREDIENT_SLOTS = 3;
    public static final MapCodec<ChoppingRecipe> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    ItemStackTemplate.CODEC.fieldOf("result").forGetter(recipe -> recipe.output),
                    Ingredient.CODEC.listOf(1, 3).fieldOf("ingredients").forGetter(recipe -> recipe.ingredients)
            ).apply(instance, ChoppingRecipe::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, ChoppingRecipe> PACKET_CODEC = StreamCodec.composite(
            ItemStackTemplate.STREAM_CODEC,
            recipe -> recipe.output,
            Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()),
            recipe -> recipe.ingredients,
            ChoppingRecipe::new
    );

    private final ItemStackTemplate output;
    private final List<Ingredient> ingredients;

    public ChoppingRecipe(ItemStackTemplate output, List<Ingredient> ingredients) {
        this.output = output;

        if (ingredients.isEmpty()) {
            throw new JsonParseException("No ingredients for chopping recipe");
        } else if (ingredients.size() > INGREDIENT_SLOTS) {
            throw new JsonParseException(
                "Too many ingredients for chopping recipe! The max is 3");
        }

        this.ingredients = ingredients;
    }

    @Override
    public boolean matches(@NotNull CraftingInput input, @NotNull Level level) {
        if (input.ingredientCount() != this.ingredients.size()) {
            return false;
        } else {
            boolean test = this.ingredients.getFirst().test(input.getItem(0));
            return input.size() == 1 && this.ingredients.size() == 1 ? test : input.stackedContents().canCraft(this, null);
        }
    }

    @NotNull
    @Override
    public ItemStack assemble(@NotNull CraftingInput input) {
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
    public RecipeSerializer<? extends @NotNull Recipe<@NotNull CraftingInput>> getSerializer() {
        return ModRecipeTypes.CHOPPING_RECIPE_SERIALIZER;
    }

    @NotNull
    @Override
    public RecipeType<? extends @NotNull Recipe<@NotNull CraftingInput>> getType() {
        return ModRecipeTypes.CHOPPING_RECIPE_TYPE;
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public ItemStackTemplate getResult() {
        return output;
    }
}

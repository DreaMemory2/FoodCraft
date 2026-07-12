package com.crystal.foodcraft.recipe;

import com.crystal.foodcraft.recipe.input.BrewBarrelInput;
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

public class BrewingRecipe implements Recipe<@NotNull BrewBarrelInput> {
    public static final MapCodec<BrewingRecipe> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    Ingredient.CODEC.listOf().fieldOf("ingredients").forGetter(recipe -> recipe.ingredients),
                    FluidState.CODEC.fieldOf("fluidInput").forGetter(recipe -> recipe.fluidState),
                    ItemStackTemplate.CODEC.listOf().fieldOf("results").forGetter(recipe -> recipe.results)
            ).apply(instance, BrewingRecipe::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, BrewingRecipe> PACKET_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()),
            recipe -> recipe.ingredients,
            ByteBufCodecs.fromCodec(FluidState.CODEC),
            recipe -> recipe.fluidState,
            ItemStackTemplate.STREAM_CODEC.apply(ByteBufCodecs.list()),
            recipe -> recipe.results,
            BrewingRecipe::new
    );

    private final List<Ingredient> ingredients;
    private final FluidState fluidState;
    private final List<ItemStackTemplate> results;

    public BrewingRecipe(List<Ingredient> ingredients, FluidState fluidState, List<ItemStackTemplate> results) {
        this.ingredients = ingredients;
        this.fluidState = fluidState;
        this.results = results;
    }

    @Override
    public boolean matches(BrewBarrelInput input, @NotNull Level level) {
        // 物品比较
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

    /**
     * <p>如果结果输出只有一个输出槽位且槽位下标0，则获取该槽位物品</p>
     */
    @Override
    public @NotNull ItemStack assemble(BrewBarrelInput input) {
        return this.results.getFirst().create();
    }

    /**
     * <p>获取输出所有输出结果（输出槽位1和输出槽位2）</p>
     */
    public List<ItemStack> getResult(BrewBarrelInput input) {
        List<ItemStack> results = new ArrayList<>();
        for (int i = 0; i < this.results.size(); i++) {
            results.add(i, this.results.get(i).create());
        }
        return results;
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
    public @NotNull RecipeSerializer<? extends @NotNull Recipe<@NotNull BrewBarrelInput>> getSerializer() {
        return ModRecipeTypes.BREWING_RECIPE_SERIALIZER;
    }

    @Override
    public @NotNull RecipeType<? extends @NotNull Recipe<@NotNull BrewBarrelInput>> getType() {
        return ModRecipeTypes.BREWING_RECIPE_TYPE;
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

    public List<ItemStackTemplate> getResults() {
        return results;
    }

    public FluidState getFluid() {
        return fluidState;
    }
}

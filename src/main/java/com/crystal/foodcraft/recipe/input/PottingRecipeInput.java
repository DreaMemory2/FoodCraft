package com.crystal.foodcraft.recipe.input;

import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeInput;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PottingRecipeInput implements RecipeInput {
    public static final PottingRecipeInput EMPTY = new PottingRecipeInput(List.of());
    private final List<ItemStack> items;
    private final int ingredientCount;
    private final StackedItemContents stackedContents = new StackedItemContents();

    private PottingRecipeInput(List<ItemStack> items) {
        this.items = items;
        int ingredientCount = 0;

        for (ItemStack item : items) {
            if (!item.isEmpty()) {
                ingredientCount++;
                this.stackedContents.accountStack(item, 1);
            }
        }

        this.ingredientCount = ingredientCount;
    }

    public static Positioned input(List<ItemStack> items) {
        Map<Integer, ItemStack> ingredients = new HashMap<>();
        Map<Integer, ItemStack> staples = new HashMap<>();
        // 0 - 13 (0-7, 8-13)
        for (int i = 0; i < items.size(); i++) {
            if (!items.get(i).isEmpty()) {
                if (i < 8) {
                    staples.put(i, items.get(i));
                }
                if (i > 7 && i <= 13) {
                    ingredients.put(i, items.get(i));
                }
            }
        }
        return new Positioned(new PottingRecipeInput(items), ingredients, staples);
    }

    @NotNull
    @Override
    public ItemStack getItem(int index) {
        return this.items.get(index);
    }

    @Override
    public int size() {
        return items.size();
    }

    public record Positioned(PottingRecipeInput input, Map<Integer, ItemStack> ingredients, Map<Integer, ItemStack> staples) {
        public static final Positioned EMPTY = new Positioned(PottingRecipeInput.EMPTY, new HashMap<>(), new HashMap<>());
    }
}

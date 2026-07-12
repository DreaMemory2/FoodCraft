package com.crystal.foodcraft.recipe.input;

import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PottingRecipeInput implements RecipeInput {
    public static final PottingRecipeInput EMPTY = new PottingRecipeInput(List.of());
    private final List<ItemStack> items;
    private final int count;

    private PottingRecipeInput(List<ItemStack> items) {
        this.items = items;
        int count = 0;
        StackedItemContents contents = new StackedItemContents();

        for (ItemStack stack : items) {
            if (stack.isEmpty()) continue;

            ++count;
            contents.accountStack(stack);
        }

        this.count = count;
    }

    public static Positioned input(List<ItemStack> items) {
        Map<Integer, ItemStack> staples = new HashMap<>();
        Map<Integer, ItemStack> flavoring = new HashMap<>();
        // 0 - 13 (0-7, 8-13)
        for (int i = 0; i < items.size(); i++) {
            if (!items.get(i).isEmpty()) {
                if (i <= 3) {
                    // 0 1 2
                    staples.put(i, items.get(i));
                }
                if (i >= 4 && i <= 11) {
                    // 0 1 2 3 4 5 6 7
                    flavoring.put(i - 4, items.get(i));
                }
            }
        }
        return new Positioned(new PottingRecipeInput(items), staples, flavoring);
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

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * <p>方块容器内食材和佐料存储位置</p>
     * <p>分为两块：主材料区和调料品区</p>
     * @param slots 输入槽位
     * @param staples 主材料（食材）
     * @param flavoring 调料品（佐料）
     */
    public record Positioned(PottingRecipeInput slots, Map<Integer, ItemStack> staples, Map<Integer, ItemStack> flavoring) {
        public static final Positioned EMPTY = new Positioned(PottingRecipeInput.EMPTY, new HashMap<>(), new HashMap<>());
    }
}

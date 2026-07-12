package com.crystal.foodcraft.recipe.input;

import com.crystal.foodcraft.util.SimpleFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.material.Fluid;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrewBarrelInput implements RecipeInput {
    public static final BrewBarrelInput EMPTY = new BrewBarrelInput(List.of(), SimpleFluidStorage.EMPTY);
    private final List<ItemStack> items;
    private final SingleFluidStorage storage;
    private final int count;

    private BrewBarrelInput(List<ItemStack> items, SingleFluidStorage storage) {
        this.items = items;
        this.storage = storage;
        int count = 0;
        StackedItemContents contents = new StackedItemContents();

        for (ItemStack stack : items) {
            if (stack.isEmpty()) continue;

            ++count;
            contents.accountStack(stack);
        }

        this.count = count;
    }

    public static Positioned input(List<ItemStack> items, SingleFluidStorage storage) {
        Map<Integer, ItemStack> ingredients = new HashMap<>();
        Map<Integer, ItemStack> results = new HashMap<>();

        for (int i = 0; i < items.size(); i++) {
            if (!items.get(i).isEmpty()) {
                // 0 - 2 输入槽位
                if (i >= 2) {
                    // 0 1 2
                    ingredients.put(i, items.get(i));
                }
                // 3和4 输出槽位
                if (i == 3 || i == 4) {
                    results.put(i, items.get(i));
                }
            }
        }

        return new Positioned(new BrewBarrelInput(items, storage), ingredients, storage, results);
    }

    @Override
    public @NotNull ItemStack getItem(int index) {
        return this.items.get(index);
    }

    public SingleFluidStorage getFluidStorage() {
        return this.storage;
    }

    public boolean isOf(Fluid fluid) {
        return this.storage.variant.isOf(fluid);
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    public record Positioned(BrewBarrelInput slots, Map<Integer, ItemStack> ingredients, SingleFluidStorage storage, Map<Integer, ItemStack> results) {
        public static final Positioned EMPTY = new Positioned(BrewBarrelInput.EMPTY, new HashMap<>(), SimpleFluidStorage.EMPTY, new HashMap<>());
    }
}

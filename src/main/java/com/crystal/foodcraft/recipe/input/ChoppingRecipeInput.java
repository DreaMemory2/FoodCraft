package com.crystal.foodcraft.recipe.input;

import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import org.jetbrains.annotations.NotNull;

public record ChoppingRecipeInput(CraftingContainer craftSlots) implements RecipeInput {
    @NotNull
    @Override
    public ItemStack getItem(int index) {
        return craftSlots.getItem(index);
    }

    @Override
    public int size() {
        return craftSlots.getContainerSize();
    }
}

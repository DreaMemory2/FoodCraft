package com.crystal.foodcraft.recipe.input;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.TransferVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.material.Fluid;
import org.jetbrains.annotations.NotNull;

public class FluidAttachedRecipeInput extends SingleVariantStorage<@NotNull FluidVariant> implements RecipeInput {
    private final ItemStack item;

    public FluidAttachedRecipeInput(ItemStack item) {
        this.item = item;
    }

    @NotNull
    @Override
    public ItemStack getItem(int index) {
        return this.item;
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    protected FluidVariant getBlankVariant() {
        return FluidVariant.blank();
    }

    @Override
    protected long getCapacity(FluidVariant variant) {
        return 0;
    }
}

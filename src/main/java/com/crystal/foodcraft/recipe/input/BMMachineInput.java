package com.crystal.foodcraft.recipe.input;

import com.crystal.foodcraft.block.entity.BeverageMakingMode;
import com.crystal.foodcraft.util.SimpleFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.material.Fluid;
import org.jetbrains.annotations.NotNull;

public class BMMachineInput implements RecipeInput {
    private final static BMMachineInput EMPTY = new BMMachineInput(null, ItemStack.EMPTY, SimpleFluidStorage.EMPTY);
    private final BeverageMakingMode mode;
    private final ItemStack input;
    private final SingleFluidStorage storage;

    public BMMachineInput(BeverageMakingMode mode, ItemStack input, SingleFluidStorage storage) {
        this.mode = mode;
        this.input = input;
        this.storage = storage;
    }

    @Override
    public @NotNull ItemStack getItem(int index) {
        return this.input;
    }

    public SingleFluidStorage getFluidStorage() {
        return this.storage;
    }

    public boolean isOf(Fluid fluid) {
        return this.storage.variant.isOf(fluid);
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return input.isEmpty();
    }

    public BeverageMakingMode getMode() {
        return mode;
    }
}

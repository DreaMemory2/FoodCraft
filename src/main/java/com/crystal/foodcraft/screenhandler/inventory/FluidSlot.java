package com.crystal.foodcraft.screenhandler.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * <p>液体槽：用于处理液体</p>
 */
public class FluidSlot extends Slot {
    private final int slot;

    public FluidSlot(Container container, int slot, int x, int y) {
        super(container, slot, x, y);
        this.slot = slot;
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack itemStack) {
        return container.canPlaceItem(slot, itemStack);
    }
}

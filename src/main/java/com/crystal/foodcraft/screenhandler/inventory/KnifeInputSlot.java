package com.crystal.foodcraft.screenhandler.inventory;

import com.crystal.foodcraft.screenhandler.ChoppingBoardMenu;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class KnifeInputSlot extends Slot {
    private final ChoppingBoardMenu menu;

    public KnifeInputSlot(ChoppingBoardMenu menu, Container container, int slot, int x, int y) {
        super(container, slot, x, y);
        this.menu = menu;
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack itemStack) {
        return this.menu.isKnife(itemStack);
    }

    @Override
    public int getMaxStackSize(@NotNull ItemStack itemStack) {
        return this.menu.isKnife(itemStack) ? 1 : super.getMaxStackSize(itemStack);
    }
}

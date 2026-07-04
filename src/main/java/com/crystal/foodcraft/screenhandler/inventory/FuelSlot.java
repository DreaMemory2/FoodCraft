package com.crystal.foodcraft.screenhandler.inventory;

import com.crystal.foodcraft.screenhandler.BaseMachineMenu;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

/**
 * <p>燃料槽，用于处理燃料</p>
 */
public class FuelSlot extends Slot {
    private final BaseMachineMenu menu;

    public FuelSlot(BaseMachineMenu menu, Container container, int slot, int x, int y) {
        super(container, slot, x, y);
        this.menu = menu;
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack itemStack) {
        return this.menu.isFuel(itemStack) || isBucket(itemStack);
    }

    @Override
    public int getMaxStackSize(@NotNull ItemStack itemStack) {
        return isBucket(itemStack) ? 1 : super.getMaxStackSize(itemStack);
    }

    public static boolean isBucket(ItemStack itemStack) {
        return itemStack.is(Items.BUCKET);
    }
}

package com.crystal.foodcraft.screenhandler;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

public class ScreenHandler extends AbstractContainerMenu {
    private final Container container;

    protected ScreenHandler(@Nullable MenuType<?> menuType, int containerId, Container container) {
        super(menuType, containerId);
        this.container = container;
    }

    @NotNull
    @Override
    public ItemStack quickMoveStack(@NotNull Player player, int slotIndex) {
        Slot slot = this.slots.get(slotIndex);

        if (!slot.hasItem()) return ItemStack.EMPTY;

        ItemStack stack = slot.getItem();
        ItemStack clicked = stack.copy();

        if (slotIndex < container.getContainerSize()) {
            if (!this.moveItemStackTo(stack, container.getContainerSize(), this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }
        } else if (!this.moveItemStackTo(stack, 0, container.getContainerSize(), false)) {
            return ItemStack.EMPTY;
        }

        if (stack.isEmpty()) {
            slot.setByPlayer(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }

        return clicked;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return this.container.stillValid(player);
    }
}

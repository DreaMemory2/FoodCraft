package com.crystal.foodcraft.screenhandler;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BaseMachineMenu extends AbstractContainerMenu {
    private final Container container;
    private final ContainerData data;
    private final Level level;

    public BaseMachineMenu(@Nullable MenuType<?> menuType, int containerId, Inventory inventory, Container container, ContainerData data) {
        super(menuType, containerId);
        this.container = container;
        this.data = data;
        this.level = inventory.player.level();
    }

    public boolean isFuel(ItemStack stack) {
        return level.fuelValues().isFuel(stack);
    }

    public Container getContainer() {
        return container;
    }

    public ContainerData getData() {
        return data;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return container.stillValid(player);
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int slotIndex) {
        return ItemStack.EMPTY;
    }
}

package com.crystal.foodcraft.screenhandler;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.FurnaceResultSlot;
import net.minecraft.world.inventory.Slot;

public class BrewBarrelMenu extends ScreenHandler {

    public BrewBarrelMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, new SimpleContainer(6));
    }

    protected BrewBarrelMenu(int containerId, Inventory inventory, Container container) {
        super(ModMenuTypes.BREW_BARREL, containerId, container);
        checkContainerSize(container, 6);
        // 输入槽
        this.addSlot(new Slot(container, 0, 54, 32));
        this.addSlot(new Slot(container, 1, 78, 32));
        this.addSlot(new Slot(container, 2, 101, 32));
        // 液体槽
        this.addSlot(new Slot(container, 3, 37, 59));
        // 输出槽
        this.addSlot(new FurnaceResultSlot(inventory.player, container, 4, 138, 32));
        this.addSlot(new FurnaceResultSlot(inventory.player, container, 5, 138, 59));
        // 物品栏，以左上角为原点，距离左侧偏移8px，距离顶部84px（相对定位）
        this.addStandardInventorySlots(inventory, 8, 84);
    }
}

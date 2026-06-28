package com.crystal.foodcraft.screenhandler;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.FurnaceResultSlot;
import net.minecraft.world.inventory.Slot;

public class BeverageMakingMachineMenu extends ScreenHandler {

    public BeverageMakingMachineMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, new SimpleContainer(5));
    }

    protected BeverageMakingMachineMenu(int containerId, Inventory inventory, Container container) {
        super(ModMenuTypes.BEVERAGE_MAKING_MACHINE, containerId, container);
        checkContainerSize(container, 5);
        // 输入槽
        this.addSlot(new Slot(container, 0, 37, 31));
        // 液体槽
        this.addSlot(new Slot(container, 1, 37, 59));
        // 加热槽
        this.addSlot(new Slot(container, 2, 118, 20));
        // 制冷槽
        this.addSlot(new Slot(container, 3, 118, 52));
        // 输出槽
        this.addSlot(new FurnaceResultSlot(inventory.player, container, 4, 85, 31));
        // 物品栏，以左上角为原点，距离左侧偏移8px，距离顶部84px（相对定位）
        this.addStandardInventorySlots(inventory, 8, 84);
    }
}

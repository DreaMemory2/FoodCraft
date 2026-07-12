package com.crystal.foodcraft.screenhandler;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.*;

public class PotMenu extends BaseMachineMenu {
    protected PotMenu(int containerId, Inventory inventory) {
       this(containerId, inventory, new SimpleContainer(14), new SimpleContainerData(5));
    }

    public PotMenu(int containerId, Inventory inventory, Container container, ContainerData data) {
        super(ModMenuTypes.POT, containerId, inventory, container, data);

        checkContainerSize(container, 14);
        checkContainerDataCount(data, 5);
        // 食材槽
        this.addSlot(new Slot(container, 0, 17, 19));
        this.addSlot(new Slot(container, 1, 35, 19));
        this.addSlot(new Slot(container, 2, 53, 19));
        this.addSlot(new Slot(container, 3, 71, 19));
        // 配料槽（佐料槽）
        this.addSlot(new Slot(container, 4, 17, 45));
        this.addSlot(new Slot(container, 5, 35, 45));
        this.addSlot(new Slot(container, 6, 53, 45));
        this.addSlot(new Slot(container, 7, 71, 45));
        this.addSlot(new Slot(container, 8, 89, 45));
        this.addSlot(new Slot(container, 9, 107, 45));
        this.addSlot(new Slot(container, 10, 125, 45));
        this.addSlot(new Slot(container, 11, 143, 45));
        // 输出槽
        this.addSlot(new FurnaceResultSlot(inventory.player, container, 12, 125, 19));
        this.addSlot(new FurnaceResultSlot(inventory.player, container, 13, 143, 19));
        // 物品栏，以左上角为原点，距离左侧偏移8px，距离顶部84px（相对定位）
        this.addStandardInventorySlots(inventory, 8, 84);
        this.addDataSlots(data);
    }
}

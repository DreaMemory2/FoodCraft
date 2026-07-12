package com.crystal.foodcraft.screenhandler;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.FurnaceResultSlot;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;

public class PanMenu extends BaseMachineMenu {
    protected PanMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, new SimpleContainer(4), new SimpleContainerData(5));
    }

    public PanMenu(int containerId, Inventory inventory, Container container, ContainerData data) {
        super(ModMenuTypes.PAN, containerId, inventory, container, data);
        checkContainerSize(container, 4);
        checkContainerDataCount(data, 5);
        // 花生油
        this.addSlot(new Slot(container, 0, 18, 39));
        // 输入槽
        this.addSlot(new Slot(container, 1, 45, 39));
        // 输出槽
        this.addSlot(new FurnaceResultSlot(inventory.player, container, 2, 108, 39));
        this.addSlot(new FurnaceResultSlot(inventory.player, container, 3, 137, 39));
        // 物品栏，以左上角为原点，距离左侧偏移8px，距离顶部84px（相对定位）
        this.addStandardInventorySlots(inventory, 8, 84);
        this.addDataSlots(data);
    }
}

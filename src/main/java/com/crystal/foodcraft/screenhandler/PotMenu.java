package com.crystal.foodcraft.screenhandler;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.FurnaceResultSlot;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;

public class PotMenu extends BaseMachineMenu {
    private final ContainerData data;

    protected PotMenu(int containerId, Inventory inventory) {
       this(containerId, inventory, new SimpleContainer(14), new SimpleContainerData(6));
    }

    public PotMenu(int containerId, Inventory inventory, Container container, ContainerData data) {
        super(ModMenuTypes.POT, containerId, inventory, container, data);
        this.data = data;

        checkContainerSize(container, 14);
        checkContainerDataCount(data, 6);
        // 配料槽
        this.addSlot(new Slot(container, 0, 17, 45));
        this.addSlot(new Slot(container, 1, 35, 45));
        this.addSlot(new Slot(container, 2, 53, 45));
        this.addSlot(new Slot(container, 3, 71, 45));
        this.addSlot(new Slot(container, 4, 89, 45));
        this.addSlot(new Slot(container, 5, 107, 45));
        this.addSlot(new Slot(container, 6, 125, 45));
        this.addSlot(new Slot(container, 7, 143, 45));
        // 食材槽
        this.addSlot(new Slot(container, 8, 17, 19));
        this.addSlot(new Slot(container, 9, 35, 19));
        this.addSlot(new Slot(container, 10, 53, 19));
        this.addSlot(new Slot(container, 11, 71, 19));
        // 输出槽
        this.addSlot(new FurnaceResultSlot(inventory.player, container, 12, 125, 19));
        this.addSlot(new FurnaceResultSlot(inventory.player, container, 13, 143, 19));
        // 物品栏，以左上角为原点，距离左侧偏移8px，距离顶部84px（相对定位）
        this.addStandardInventorySlots(inventory, 8, 84);
        this.addDataSlots(data);
    }

    public ContainerData getContainerData() {
        return this.data;
    }

    @Override
    public int getMachineSlots() {
        return 14;
    }
}

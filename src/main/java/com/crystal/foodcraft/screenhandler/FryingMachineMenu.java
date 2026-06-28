package com.crystal.foodcraft.screenhandler;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.FurnaceResultSlot;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;

public class FryingMachineMenu extends BaseMachineMenu {

    public FryingMachineMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, new SimpleContainer(4), new SimpleContainerData(5));
    }

    protected FryingMachineMenu(int containerId, Inventory inventory, Container container, ContainerData data) {
        super(ModMenuTypes.FRYING_MACHINE, containerId, inventory, container, data);
        checkContainerSize(container, 4);
        checkContainerSize(container, 5);
        // 输入槽
        this.addSlot(new Slot(container, 0, 58, 31));
        // 液体槽
        this.addSlot(new Slot(container, 1, 37, 59));
        // 燃料槽
        this.addSlot(new Slot(container, 2, 95, 59));
        // 输出槽
        this.addSlot(new FurnaceResultSlot(inventory.player, container, 3, 130, 31));
        // 物品栏，以左上角为原点，距离左侧偏移8px，距离顶部84px（相对定位）
        this.addStandardInventorySlots(inventory, 8, 84);
        this.addDataSlots(data);
    }

    @Override
    public int getMachineSlots() {
        return 0;
    }
}

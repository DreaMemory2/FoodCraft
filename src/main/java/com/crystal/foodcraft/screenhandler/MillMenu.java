package com.crystal.foodcraft.screenhandler;

import com.crystal.foodcraft.screenhandler.inventory.FuelSlot;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.FurnaceResultSlot;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;

public class MillMenu extends BaseMachineMenu {

    public MillMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, new SimpleContainer(3), new SimpleContainerData(4));
    }

    public MillMenu(int containerId, Inventory inventory, Container container, ContainerData data) {
        super(ModMenuTypes.MILL, containerId, inventory, container, data);

        checkContainerSize(container, 3);
        checkContainerDataCount(data, 4);
        // 输入槽
        this.addSlot(new Slot(container, 0, 49, 19));
        // 燃料槽
        this.addSlot(new FuelSlot(this, container, 1, 80, 54));
        // 输出槽
        this.addSlot(new FurnaceResultSlot(inventory.player, container, 2, 112, 19));
        // 物品栏，以左上角为原点，距离左侧偏移8px，距离顶部84px（相对定位）
        this.addStandardInventorySlots(inventory, 8, 84);
        // 数据槽位
        this.addDataSlots(data);
    }
}

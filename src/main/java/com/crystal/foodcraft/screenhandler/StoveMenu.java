package com.crystal.foodcraft.screenhandler;

import com.crystal.foodcraft.screenhandler.inventory.FuelSlot;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;

public class StoveMenu extends BaseMachineMenu {

    public StoveMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, new SimpleContainer(1), new SimpleContainerData(2));
    }

    public StoveMenu(int containerId, Inventory inventory, Container container, ContainerData data) {
        super(ModMenuTypes.STOVE, containerId, inventory, container, data);

        checkContainerSize(container, 1);
        checkContainerDataCount(data, 2);
        // 燃烧槽
        this.addSlot(new FuelSlot(this, container, 0, 80, 54));
        // 物品栏，以左上角为原点，距离左侧偏移8px，距离顶部84px（相对定位）
        this.addStandardInventorySlots(inventory, 8, 84);
        // 数据槽位
        this.addDataSlots(data);
    }
}

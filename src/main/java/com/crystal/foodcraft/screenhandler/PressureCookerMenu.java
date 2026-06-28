package com.crystal.foodcraft.screenhandler;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.FurnaceResultSlot;
import net.minecraft.world.inventory.Slot;

public class PressureCookerMenu extends ScreenHandler {

    public PressureCookerMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, new SimpleContainer(6));
    }

    protected PressureCookerMenu(int containerId, Inventory inventory, Container container) {
        super(ModMenuTypes.PRESSURE_COOKER, containerId, container);
        checkContainerSize(container, 6);
        // 输入槽
        this.addSlot(new Slot(container, 0, 47 ,31));
        this.addSlot(new Slot(container, 1, 71 , 31));
        this.addSlot(new Slot(container, 2, 95 , 31));
        // 液体槽
        this.addSlot(new Slot(container, 3, 37 , 59));
        // 燃料槽
        this.addSlot(new Slot(container, 4, 95 , 59));
        // 输出槽
        this.addSlot(new FurnaceResultSlot(inventory.player, container, 5, 145 , 31));
        // 物品栏，以左上角为原点，距离左侧偏移8px，距离顶部84px（相对定位）
        this.addStandardInventorySlots(inventory, 8, 84);
    }
}

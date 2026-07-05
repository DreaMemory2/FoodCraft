package com.crystal.foodcraft.screenhandler;

import com.crystal.foodcraft.block.entity.BrewBarrelBlockEntity;
import com.crystal.foodcraft.network.BlockPosPayload;
import com.crystal.foodcraft.screenhandler.inventory.FluidSlot;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.FurnaceResultSlot;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;

public class BrewBarrelMenu extends BaseMachineMenu {
    private final BrewBarrelBlockEntity blockEntity;

    public BrewBarrelMenu(int containerId, Inventory inventory, BlockPosPayload posPayload) {
        this(containerId, inventory, new SimpleContainer(6), new SimpleContainerData(2), (BrewBarrelBlockEntity) inventory.player.level().getBlockEntity(posPayload.pos()));
    }

    public BrewBarrelMenu(int containerId, Inventory inventory, Container container, ContainerData data, BrewBarrelBlockEntity blockEntity) {
        super(ModMenuTypes.BREW_BARREL, containerId, inventory, container, data);
        this.blockEntity = blockEntity;

        checkContainerSize(container, 6);
        checkContainerDataCount(data, 2);
        // 输入槽
        this.addSlot(new Slot(container, 0, 54, 32));
        this.addSlot(new Slot(container, 1, 78, 32));
        this.addSlot(new Slot(container, 2, 101, 32));
        // 液体槽
        this.addSlot(new FluidSlot(container, 3, 37, 59));
        // 输出槽
        this.addSlot(new FurnaceResultSlot(inventory.player, container, 4, 138, 32));
        this.addSlot(new FurnaceResultSlot(inventory.player, container, 5, 138, 59));
        // 物品栏，以左上角为原点，距离左侧偏移8px，距离顶部84px（相对定位）
        this.addStandardInventorySlots(inventory, 8, 84);
        this.addDataSlots(data);
    }

    public BrewBarrelBlockEntity getBlockEntity() {
        return blockEntity;
    }
}

package com.crystal.foodcraft.screenhandler;

import com.crystal.foodcraft.block.entity.BMMachineBlockEntity;
import com.crystal.foodcraft.network.BlockPosPayload;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.FurnaceResultSlot;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import org.jetbrains.annotations.NotNull;

public class BeverageMakingMachineMenu extends BaseMachineMenu {
    private final BMMachineBlockEntity blockEntity;
    private final Container container;

    public BeverageMakingMachineMenu(int containerId, Inventory inventory, BlockPosPayload payload) {
        this(containerId, inventory, new SimpleContainer(5), new SimpleContainerData(5), (BMMachineBlockEntity) inventory.player.level().getBlockEntity(payload.pos()));
    }

    public BeverageMakingMachineMenu(int containerId, Inventory inventory, Container container, ContainerData data, BMMachineBlockEntity blockEntity) {
        super(ModMenuTypes.BEVERAGE_MAKING_MACHINE, containerId, inventory, container, data);
        checkContainerSize(container, 5);
        checkContainerDataCount(data, 5);
        this.blockEntity = blockEntity;
        this.container = container;
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
        this.addDataSlots(data);
    }

    public BMMachineBlockEntity getBlockEntity() {
        return blockEntity;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return container.stillValid(player);
    }
}

package com.crystal.foodcraft.screenhandler;

import com.crystal.foodcraft.block.entity.FryingMachineBlockEntity;
import com.crystal.foodcraft.network.BlockPosPayload;
import com.crystal.foodcraft.screenhandler.inventory.FluidSlot;
import com.crystal.foodcraft.screenhandler.inventory.FuelSlot;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import org.jetbrains.annotations.NotNull;

public class FryingMachineMenu extends BaseMachineMenu {
    private final FryingMachineBlockEntity blockEntity;
    private final Container container;

    public FryingMachineMenu(int containerId, Inventory inventory, BlockPosPayload payload) {
        this(containerId, inventory, new SimpleContainer(4), new SimpleContainerData(4), (FryingMachineBlockEntity) inventory.player.level().getBlockEntity(payload.pos()));
    }

    public FryingMachineMenu(int containerId, Inventory inventory, Container container, ContainerData data, FryingMachineBlockEntity blockEntity) {
        super(ModMenuTypes.FRYING_MACHINE, containerId, inventory, container, data);
        checkContainerSize(container, 4);
        checkContainerDataCount(data, 4);
        this.blockEntity = blockEntity;
        this.container = container;
        // 输入槽
        this.addSlot(new Slot(container, 0, 58, 31));
        // 液体槽
        this.addSlot(new FluidSlot(container, 1, 37, 59));
        // 燃料槽
        this.addSlot(new FuelSlot(this, container, 2, 95, 59));
        // 输出槽
        this.addSlot(new FurnaceResultSlot(inventory.player, container, 3, 130, 31));
        // 物品栏，以左上角为原点，距离左侧偏移8px，距离顶部84px（相对定位）
        this.addStandardInventorySlots(inventory, 8, 84);
        this.addDataSlots(data);
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return container.stillValid(player);
    }

    public FryingMachineBlockEntity getBlockEntity() {
        return blockEntity;
    }
}

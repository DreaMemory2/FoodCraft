package com.crystal.foodcraft.screenhandler;

import com.crystal.foodcraft.block.entity.PressureCookerBlockEntity;
import com.crystal.foodcraft.network.BlockPosPayload;
import com.crystal.foodcraft.screenhandler.inventory.FluidSlot;
import com.crystal.foodcraft.screenhandler.inventory.FuelSlot;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.FurnaceResultSlot;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import org.jetbrains.annotations.NotNull;

public class PressureCookerMenu extends BaseMachineMenu {
    private final PressureCookerBlockEntity blockEntity;
    private final Container container;

    public PressureCookerMenu(int containerId, Inventory inventory, BlockPosPayload payload) {
        this(containerId, inventory, new SimpleContainer(6), new SimpleContainerData(4), (PressureCookerBlockEntity) inventory.player.level().getBlockEntity(payload.pos()));
    }

    public PressureCookerMenu(int containerId, Inventory inventory, Container container, ContainerData data, PressureCookerBlockEntity blockEntity) {
        super(ModMenuTypes.PRESSURE_COOKER, containerId, inventory, container, data);
        this.blockEntity = blockEntity;
        this.container = container;
        checkContainerSize(container, 6);
        checkContainerDataCount(data, 4);
        // 输入槽
        this.addSlot(new Slot(container, 0, 47 ,31));
        this.addSlot(new Slot(container, 1, 71 , 31));
        this.addSlot(new Slot(container, 2, 95 , 31));
        // 液体槽
        this.addSlot(new FluidSlot(container, 3, 37 , 59));
        // 燃料槽
        this.addSlot(new FuelSlot(this, container, 4, 95 , 59));
        // 输出槽
        this.addSlot(new FurnaceResultSlot(inventory.player, container, 5, 145 , 31));
        // 物品栏，以左上角为原点，距离左侧偏移8px，距离顶部84px（相对定位）
        this.addStandardInventorySlots(inventory, 8, 84);
        this.addDataSlots(data);
    }

    public PressureCookerBlockEntity getBlockEntity() {
        return blockEntity;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return container.stillValid(player);
    }
}

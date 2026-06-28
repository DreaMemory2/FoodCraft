package com.crystal.foodcraft.block.entity;

import com.crystal.foodcraft.screenhandler.BeverageMakingMachineMenu;
import com.crystal.foodcraft.screenhandler.inventory.ImplementedContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

public class BMMachineBlockEntity extends BlockEntity implements ImplementedContainer, MenuProvider {
    private final NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);

    public BMMachineBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.BEVERAGE_MAKING_MACHINE, worldPosition, blockState);
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return items;
    }

    @NotNull
    @Override
    public Component getDisplayName() {
        return Component.translatable("container.foodcraft.beverage_making_machine");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, @NotNull Inventory inventory, @NotNull Player player) {
        return new BeverageMakingMachineMenu(containerId, inventory);
    }
}

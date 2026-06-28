package com.crystal.foodcraft.block.entity;

import com.crystal.foodcraft.screenhandler.BrewBarrelMenu;
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

public class BrewBarrelBlockEntity extends BlockEntity implements ImplementedContainer, MenuProvider {
    private final NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);

    public BrewBarrelBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.BREW_BARREL, worldPosition, blockState);
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return items;
    }

    @NotNull
    @Override
    public Component getDisplayName() {
        return Component.translatable("container.foodcraft.brew_barrel");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, @NotNull Inventory inventory, @NotNull Player player) {
        return new BrewBarrelMenu(containerId, inventory);
    }
}

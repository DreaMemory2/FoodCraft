package com.crystal.foodcraft.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.FuelValues;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.NotNull;

public abstract class BaseBlockEntity extends BaseContainerBlockEntity {

    protected BaseBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @NotNull
    @Override
    public CompoundTag getUpdateTag(@NotNull HolderLookup.Provider registries) {
        return this.saveWithoutMetadata(registries);
    }

    protected void inventoryChanged() {
        super.setChanged();
        if (this.level != null) {
            this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 2);
        }
    }

    public static void detectLit(Level level, BlockPos blockPos, BlockState blockState, boolean working) {
        if (blockState.getValue(BlockStateProperties.LIT) != working) {
            level.setBlock(blockPos, blockState.setValue(BlockStateProperties.LIT, working), 3);
        }
    }

    public static void consumeFuel(NonNullList<ItemStack> items, int index, ItemStack fuel) {
        Item fuelItem = fuel.getItem();
        fuel.shrink(1);
        if (fuel.isEmpty()) {
            ItemStackTemplate remainder = fuelItem.getCraftingRemainder();
            items.set(index, remainder != null ? remainder.create() : ItemStack.EMPTY);
        }
    }

    public int getBurnItem(FuelValues fuelValues, ItemStack itemStack) {
        return fuelValues.burnDuration(itemStack);
    }

    public static boolean canBurn(NonNullList<ItemStack> items, int maxStackSize, ItemStack burnResult) {
        ItemStack resultItemStack = items.get(2);
        if (resultItemStack.isEmpty()) {
            return true;
        } else if (!ItemStack.isSameItemSameComponents(resultItemStack, burnResult)) {
            return false;
        } else {
            int resultCount = resultItemStack.getCount() + burnResult.count();
            int maxResultCount = Math.min(maxStackSize, burnResult.getMaxStackSize());
            return resultCount <= maxResultCount;
        }
    }

    public static void burn(NonNullList<ItemStack> items, ItemStack inputItemStack, ItemStack result) {
        ItemStack resultItemStack = items.get(2);
        if (resultItemStack.isEmpty()) {
            items.set(2, result.copy());
        } else {
            resultItemStack.grow(result.getCount());
        }

        if (inputItemStack.is(Items.WET_SPONGE) && !items.get(1).isEmpty() && items.get(1).is(Items.BUCKET)) {
            items.set(1, new ItemStack(Items.WATER_BUCKET));
        }

        inputItemStack.shrink(1);
    }
}

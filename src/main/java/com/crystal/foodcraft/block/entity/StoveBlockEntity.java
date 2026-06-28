package com.crystal.foodcraft.block.entity;

import com.crystal.foodcraft.api.ItemHandlerProvider;
import com.crystal.foodcraft.screenhandler.StoveMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;

public class StoveBlockEntity extends BaseBlockEntity implements ItemHandlerProvider {
    public NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);
    protected final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int i) {
            return switch (i) {
                case 0 -> StoveBlockEntity.this.burnTime;
                case 1 -> StoveBlockEntity.this.maxBurnTime;
                default -> 0;
            };
        }

        @Override
        public void set(int i, int i1) {
            switch (i) {
                case 0 -> StoveBlockEntity.this.burnTime = i1;
                case 1 -> StoveBlockEntity.this.maxBurnTime = i1;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    };
    /**
     * <p>燃烧时间</p>
     */
    public int burnTime;
    /**
     * <p>最大燃烧时间</p>
     */
    public int maxBurnTime;

    public StoveBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.STOVE, pos, state);
    }

    @Override
    protected void loadAdditional(@NotNull ValueInput tag) {
        super.loadAdditional(tag);
        // 加载库存中的物品
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, items);

        this.burnTime = tag.getIntOr("BurnTime", 0);
        this.maxBurnTime = tag.getIntOr("MaxBurnTime", 0);
    }

    @Override
    protected void saveAdditional(@NotNull ValueOutput tag) {
        super.saveAdditional(tag);
        // 存储库存中的物品
        ContainerHelper.saveAllItems(tag, items);
        tag.putInt("BurnTime", this.burnTime);
        tag.putInt("MaxBurnTime", this.maxBurnTime);
    }

    @NotNull
    @Override
    public NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(@NotNull NonNullList<ItemStack> items) {
        this.items = items;
    }

    @NotNull
    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.foodcraft.stove");
    }

    @NotNull
    @Override
    public AbstractContainerMenu createMenu(int containerId, @NotNull Inventory inventory) {
        return new StoveMenu(containerId, inventory, this, dataAccess);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, StoveBlockEntity stove) {
        ItemStack fuelItem = stove.items.getFirst();
        // 如果灶炉正在燃烧
        if (stove.burnTime > 0) {
            stove.burnTime--;
            stove.setChanged();
        }
        // 如果灶炉库存有物品
        else if (!fuelItem.isEmpty() && level.fuelValues().isFuel(fuelItem)) {
            int burnTime = stove.getBurnItem(level.fuelValues(), fuelItem);
            if (burnTime > 0) {
                burnTime *= 2;
                stove.maxBurnTime = burnTime;
                stove.burnTime = burnTime;
                consumeFuel(stove.items, 0, fuelItem);
                detectLit(level, blockPos, blockState, true);
                stove.setChanged();
            } else {
                detectLit(level, blockPos, blockState, false);
                stove.setChanged();
            }
        }
        // 如果灶炉熄火了
        else {
            stove.maxBurnTime = 0;
            detectLit(level, blockPos, blockState, false);
            stove.setChanged();
        }
    }

    @Override
    public int getContainerSize() {
        return 1;
    }

    @Override
    public ContainerData getInventory() {
        return this.dataAccess;
    }
}

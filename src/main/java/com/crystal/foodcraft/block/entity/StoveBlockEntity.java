package com.crystal.foodcraft.block.entity;

import com.crystal.foodcraft.api.ItemHandlerProvider;
import com.crystal.foodcraft.api.TickableBlockEntity;
import com.crystal.foodcraft.block.basic.HeatableProvider;
import com.crystal.foodcraft.network.NetWorkBlockEntity;
import com.crystal.foodcraft.screenhandler.StoveMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;

public class StoveBlockEntity extends NetWorkBlockEntity implements ItemHandlerProvider, TickableBlockEntity, HeatableProvider {
    public NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);
    protected final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int syncId) {
            return switch (syncId) {
                case 0 -> StoveBlockEntity.this.burnTime;
                case 1 -> StoveBlockEntity.this.maxBurnTime;
                default -> 0;
            };
        }

        @Override
        public void set(int syncId, int value) {
            switch (syncId) {
                case 0 -> StoveBlockEntity.this.burnTime = value;
                case 1 -> StoveBlockEntity.this.maxBurnTime = value;
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

    /**
     * <p>初始化方块状态</p>
     */
    public void init() {
        burnTime = 0;
        maxBurnTime = 0;
        detectLit(level, getBlockPos(), getBlockState(), false);
        setChanged();
    }

    @Override
    public void tick() {
        // 获取燃料
        ItemStack fuelItem = items.getFirst();
        // 如果灶炉正在燃烧
        if (burnTime > 0) {
            burnTime--;
            setChanged();
            return;
        } else if (burnTime == 0 && maxBurnTime > 0) {
            init(); // 初始化
            return;
        }

        // 如果灶炉库中的物品是燃料
        if (!fuelItem.isEmpty() && level != null && level.fuelValues().isFuel(fuelItem)) {
            // 获取该物品燃料时长
            int burnDuration = getBurnTime(level.fuelValues(), fuelItem);
            if (burnDuration > 0) {
                burnDuration *= 2; // 延长时间
                maxBurnTime = burnDuration;
                burnTime = burnDuration;
                fuelItem.shrink(1);
                detectLit(level, getBlockPos(), getBlockState(), true);
                setChanged();
            }
        }
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
    public AbstractContainerMenu createMenu(int containerId, @NotNull Inventory inventory) {
        return new StoveMenu(containerId, inventory, this, dataAccess);
    }

    @NotNull
    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.foodcraft.stove");
    }

    @Override
    protected void setItems(@NotNull NonNullList<ItemStack> items) {
        this.items = items;
    }

    @NotNull
    @Override
    public NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    public int getContainerSize() {
        return items.size();
    }

    @Override
    public ContainerData getInventory() {
        return this.dataAccess;
    }
}

package com.crystal.foodcraft.block.entity;

import com.crystal.foodcraft.block.basic.CookableProvider;
import com.crystal.foodcraft.block.basic.HeatableProvider;
import com.crystal.foodcraft.network.NetWorkBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class BaseMachineBlockEntity extends NetWorkBlockEntity implements CookableProvider, HeatableProvider {
    public int cookingTime;
    public int burnTime;
    public int maxCookingTime;
    public int maxBurnTime;
    public boolean isLit;

    protected BaseMachineBlockEntity(BlockEntityType<?> type, BlockPos worldPosition, BlockState blockState) {
        super(type, worldPosition, blockState);
    }

    @Override
    protected void saveAdditional(@NotNull ValueOutput output) {
        super.saveAdditional(output);

        output.putInt("CookingTime", this.cookingTime);
        output.putInt("BurnTime", this.burnTime);
        output.putInt("MaxCookingTime", this.maxCookingTime);
        output.putInt("MaxBurnTime", this.maxBurnTime);
        output.putBoolean("IsLit", this.isLit);
    }

    @Override
    protected void loadAdditional(@NotNull ValueInput input) {
        super.loadAdditional(input);

        this.cookingTime = input.getIntOr("cookingTime", 0);
        this.burnTime = input.getIntOr("burnTime", 0);
        this.maxCookingTime = input.getIntOr("maxCookingTime", 0);
        this.maxBurnTime = input.getIntOr("maxBurnTime", 0);
        this.isLit = input.getBooleanOr("isLit", false);
    }

    public void setItem(NonNullList<ItemStack> items, int slot, ItemStack itemStack) {
        ItemStack oldStack = items.get(slot);
        boolean same = !itemStack.isEmpty() && ItemStack.isSameItemSameComponents(oldStack, itemStack);
        items.set(slot, itemStack);
        itemStack.limitSize(this.getMaxStackSize(itemStack));
        if (slot == 0 && !same) {
            this.maxCookingTime = 200;
            this.cookingTime = 0;
            this.setChanged();
        }
    }

    protected static boolean canBurn(NonNullList<ItemStack> items, int maxStackSize, ItemStack burnResult) {
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

    /**
     * <p>是否已经处于燃烧状态（lit=true）</p>
     * <p>如果燃烧时间为零，则初始化状态</p>
     */
    protected void canLit() {
        if (burnTime > 0) {
            burnTime--;
            isLit = true;
            detectLit(level, getBlockPos(), getBlockState(), true);
        } else {
            burnTime = 0;
            maxBurnTime = 0;
            isLit = false;
            detectLit(level, getBlockPos(), getBlockState(), false);
        }
    }

    /**
     * @param fuel 燃料
     * @return 是否开始重新点燃, change表示方块状态更新
     */
    public boolean startLit(ItemStack fuel) {
        if (!isLit) {
            int burnDuration = getBurnTime(Objects.requireNonNull(level).fuelValues(), fuel);
            burnTime = burnDuration;
            maxBurnTime = burnDuration;
            if (burnDuration > 0) {
                fuel.shrink(1);
                isLit = true;
                return true;
            }
        }
        return false;
    }
}

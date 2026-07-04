package com.crystal.foodcraft.block.basic;

import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.world.item.ItemStack;

/**
 * <p>提供液体处理接口</p>
 */
public interface FluidHandlerProvider {

    default void inputFluid(SingleFluidStorage fluidStorage, Storage<FluidVariant> itemFluidStorage) {
        // 遍历查找所有物品，找到这个物品可以提取液体种类（事务模拟）
        FluidVariant match = null;
        for (StorageView<FluidVariant> view : itemFluidStorage.nonEmptyViews()) {
            // 如果存储节点为空，跳出循环；例如：空桶里没有液体可提取
            if (view.isResourceBlank()) continue;
            try(Transaction transaction = Transaction.openOuter()) {
                // 假如输入是桶物品时，则获取桶式存储节点
                if (fluidStorage.insert(view.getResource(), FluidConstants.BUCKET, transaction) > 0) {
                    match = view.getResource();
                    break;
                }
            }
        }
        if (match == null || match.isBlank()) return;
        // 如果成功查找物品（桶），则提取桶中的液体
        try(Transaction transaction = Transaction.openOuter()) {
            // inserted 实际插入量， FluidConstants.BUCKET 所输入最大值
            long inserted = fluidStorage.insert(match, FluidConstants.BUCKET, transaction);
            // 从itemFluidStorage物品中提取液体
            // extracted 实际提取量， inserted 所提取最大值
            long extracted = itemFluidStorage.extract(match, inserted, transaction);
            // 例如：输入的物品为水瓶
            if (extracted < FluidConstants.BUCKET) {
                long extra = FluidConstants.BUCKET - extracted;
                // 移除多余的液体
                fluidStorage.extract(match, extra, transaction);
            }

            transaction.commit();
        }
    }

    /**
     * @param slot 槽位下标（设置输入槽的下标为1）
     * @param stack 物品
     * @return 检查槽位中物品是否合法物品（例如：岩浆桶，水桶等）
     */
    default boolean isPlaceItem(int slot, ItemStack stack) {
        // 完全支持液体提取的检查
        // 例如：检查输入槽是否为空桶或输入槽为空槽
        if (stack.isEmpty()) return true;
        // 除了输入槽（下标为1）之外均无无效
        if (slot != 1) return false;
        // 对液体存储物品访问，也就是说，从输入槽中物品提取液体，液体按照滴为单位存储
        // 例如：输出槽位中有水桶，从水桶中提取液体，能提取81000水滴
        Storage<FluidVariant> storage = ContainerItemContext.withConstant(stack).find(FluidStorage.ITEM);
        return storage != null;
    }

    /**
     * <p>消耗食用油（花生油），每次成功制作食材，则消耗一瓶花生油</p>
     * @param fluidStorage 方块容器所存储的液体
     */
    default void consumeFluid(SingleFluidStorage fluidStorage) {
        try(Transaction transaction = Transaction.openOuter()) {
            fluidStorage.extract(fluidStorage.getResource(), FluidConstants.BOTTLE, transaction);

            transaction.commit();
        }
    }
}

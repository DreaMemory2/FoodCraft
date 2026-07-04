package com.crystal.foodcraft.block.basic;

import com.crystal.foodcraft.tag.ForeignModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.FuelValues;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

/**
 * <p>可加热能力插件</p>
 */
public interface HeatableProvider {
    /**
     * <p>是否下方方块提供了热源</p>
     */
    default boolean isHeated(Level level, BlockPos pos, BlockState state) {
        // 方块底部
        BlockState stateBelow = level.getBlockState(pos.below());
        // 如果方块下方有热源，且方块属性为Lit
        return stateBelow.is(ForeignModTags.HEAT_SOURCES)
                && stateBelow.hasProperty(BlockStateProperties.LIT)
                && stateBelow.getValue(BlockStateProperties.LIT);
    }

    /**
     * <p>检测方块是否开启状态</p>
     * @param level 世界
     * @param blockPos 方块位置
     * @param blockState 方块状态
     * @param working 是否开启工作
     */
    default void detectLit(Level level, BlockPos blockPos, BlockState blockState, boolean working) {
        if (blockState.getValue(BlockStateProperties.LIT) != working) {
            level.setBlock(blockPos, blockState.setValue(BlockStateProperties.LIT, working), 3);
        }
    }

    /**
     * <p>从物品中获取燃料值</p>
     * @param fuelValues 燃料值
     * @param itemStack 燃料
     */
    default int getBurnTime(FuelValues fuelValues, ItemStack itemStack) {
        return fuelValues.burnDuration(itemStack);
    }
}

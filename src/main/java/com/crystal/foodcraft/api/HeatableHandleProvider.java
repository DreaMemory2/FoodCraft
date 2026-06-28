package com.crystal.foodcraft.api;

import com.crystal.foodcraft.tag.ForeignModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public interface HeatableHandleProvider {

    default boolean isHeated(Level level, BlockPos pos) {
        BlockState stateBelow = level.getBlockState(pos.below());
        if (stateBelow.is(ForeignModTags.HEAT_SOURCES)) {
            return !stateBelow.hasProperty(BlockStateProperties.LIT) || stateBelow.getValue(
                BlockStateProperties.LIT);
        } else {
            if (!this.requiresDirectHeat() && stateBelow.is(ForeignModTags.HEAT_CONDUCTORS)) {
                BlockState stateFurtherBelow = level.getBlockState(pos.below(2));
                if (stateFurtherBelow.is(ForeignModTags.HEAT_SOURCES)) {
                    if (stateFurtherBelow.hasProperty(BlockStateProperties.LIT)) {
                        return stateFurtherBelow.getValue(BlockStateProperties.LIT);
                    }
                    return true;
                }
            }
            return false;
        }
    }

    default boolean requiresDirectHeat() {
        return false;
    }
}

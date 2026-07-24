package com.crystal.foodcraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.TintedParticleLeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;

public class BigLeavesBlock extends TintedParticleLeavesBlock {

    public BigLeavesBlock(float leafParticleChance, Properties properties) {
        super(leafParticleChance, properties);
    }

    @Override
    protected void tick(@NotNull BlockState state, ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        level.setBlock(pos, updateDistance(state, level, pos), 3);
    }

    private static BlockState updateDistance(BlockState state, LevelAccessor level, BlockPos pos) {
        int newDistance = 7;
        BlockPos.MutableBlockPos neighborPos = new BlockPos.MutableBlockPos();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -1; dz <= 1; dz++) {
                    neighborPos.setWithOffset(pos, dx, dy, dz);
                    newDistance = Math.min(newDistance, getDistanceAt(level.getBlockState(neighborPos)) + 1);
                    if (newDistance == 1) break;
                }
            }
        }
        return state.setValue(DISTANCE, newDistance);
    }

    private static int getDistanceAt(BlockState state) {
        return getOptionalDistanceAt(state).orElse(7);
    }

    @Override
    public @NotNull BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState replacedFluidState = context.getLevel().getFluidState(context.getClickedPos());
        BlockState state = this.defaultBlockState().setValue(PERSISTENT, true).setValue(WATERLOGGED, replacedFluidState.is(Fluids.WATER));
        return updateDistance(state, context.getLevel(), context.getClickedPos());
    }
}

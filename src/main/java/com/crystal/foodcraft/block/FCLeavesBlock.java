package com.crystal.foodcraft.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import org.jetbrains.annotations.NotNull;

public class FCLeavesBlock extends LeavesBlock {
    public static final MapCodec<FCLeavesBlock> CODEC = simpleCodec(FCLeavesBlock::new);

    public FCLeavesBlock(Properties properties) {
        super(0, properties);
    }

    @NotNull
    @Override
    public MapCodec<? extends LeavesBlock> codec() {
        return CODEC;
    }

    @Override
    protected void spawnFallingLeavesParticle(@NotNull Level level, @NotNull BlockPos pos, @NotNull RandomSource random) {

    }
}

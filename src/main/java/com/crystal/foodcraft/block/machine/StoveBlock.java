package com.crystal.foodcraft.block.machine;

import com.crystal.foodcraft.block.BaseMachineBlock;
import com.crystal.foodcraft.block.entity.ModBlockEntities;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

/**
 * <p>灶炉方块</p>
 */
public class StoveBlock extends BaseMachineBlock {
    public static final MapCodec<StoveBlock> CODEC = simpleCodec(StoveBlock::new);

    public StoveBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull MapCodec<? extends BaseMachineBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockEntityType<? extends BlockEntity> getType() {
        return ModBlockEntities.STOVE;
    }

    @Override
    public void animateTick(BlockState state, @NotNull Level level, BlockPos pos, @NotNull RandomSource random) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        // 如果炉灶没有处于加热状态，则没有火苗生成
        if (!state.getValue(LIT)) return;

        if (random.nextDouble() < 0.1)
            level.playLocalSound(x + 0.5F, y + 0.5F, z + 0.5F, SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0F + random.nextFloat(), random.nextFloat() * 0.7F + 0.3F, false);
        // 如果炉灶上发为实体方块，则没有火苗生成
        if (level.getBlockState(pos.above()).getBlock() instanceof AirBlock) {
            for (float i = 0.25F; i <= 0.75F; i += 0.25F) {
                for (float j = 0.25F; j <= 0.75F; j += 0.25F) {
                    level.addParticle(ParticleTypes.SMOKE, x + i, y + 1.2F, z + j, 0.0D, 0.0D, 0.0D);
                    level.addParticle(ParticleTypes.FLAME, x + i, y + 1.2F, z + j, 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }
}

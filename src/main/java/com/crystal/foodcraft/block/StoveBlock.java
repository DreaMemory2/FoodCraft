package com.crystal.foodcraft.block;

import com.crystal.foodcraft.block.entity.ModBlockEntities;
import com.crystal.foodcraft.block.entity.StoveBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

public class StoveBlock extends BaseMachineBlock {
    public static final MapCodec<StoveBlock> CODEC = simpleCodec(StoveBlock::new);

    public StoveBlock(Properties properties) {
        super(properties);
    }

    @NotNull
    @Override
    protected MapCodec<? extends BaseMachineBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new StoveBlockEntity(pos, state);
    }

    @NotNull
    @Override
    protected InteractionResult useWithoutItem(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull BlockHitResult hitResult) {
        if (!level.isClientSide() && level.getBlockEntity(pos) instanceof StoveBlockEntity stove) {
            player.openMenu(stove);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState blockState, @NotNull BlockEntityType<T> type) {
        return !level.isClientSide() ? createTickerHelper(type, ModBlockEntities.STOVE, StoveBlockEntity::tick) : null;
    }

    @Override
    public void animateTick(BlockState state, @NotNull Level level, BlockPos pos, @NotNull RandomSource random) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        if (state.getValue(LIT)) {
            if (random.nextDouble() < 0.1) {
                level.playLocalSound(x + 0.5F, y + 0.5F, z + 0.5F, SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0F + random.nextFloat(), random.nextFloat() * 0.7F + 0.3F, false);
            }
            if (level.getBlockState(pos.above()).getBlock() instanceof AirBlock) {
                level.addParticle(ParticleTypes.SMOKE, x + 0.25F, y + 1.2F, z + 0.25F, 0.0D, 0.0D, 0.0D);
                level.addParticle(ParticleTypes.FLAME, x + 0.25F, y + 1.2F, z + 0.25F, 0.0D, 0.0D, 0.0D);

                level.addParticle(ParticleTypes.SMOKE, x + 0.5F, y + 1.2F, z + 0.25F, 0.0D, 0.0D, 0.0D);
                level.addParticle(ParticleTypes.FLAME, x + 0.5F, y + 1.2F, z + 0.25F, 0.0D, 0.0D, 0.0D);

                level.addParticle(ParticleTypes.SMOKE, x + 0.75F, y + 1.2F, z + 0.25F, 0.0D, 0.0D, 0.0D);
                level.addParticle(ParticleTypes.FLAME, x + 0.75F, y + 1.2F, z + 0.25F, 0.0D, 0.0D, 0.0D);

                level.addParticle(ParticleTypes.SMOKE, x + 0.25F, y + 1.2F, z + 0.5F, 0.0D, 0.0D, 0.0D);
                level.addParticle(ParticleTypes.FLAME, x + 0.25F, y + 1.2F, z + 0.5F, 0.0D, 0.0D, 0.0D);

                level.addParticle(ParticleTypes.SMOKE, x + 0.5F, y + 1.2F, z + 0.5F, 0.0D, 0.0D, 0.0D);
                level.addParticle(ParticleTypes.FLAME, x + 0.5F, y + 1.2F, z + 0.5F, 0.0D, 0.0D, 0.0D);

                level.addParticle(ParticleTypes.SMOKE, x + 0.75F, y + 1.2F, z + 0.5F, 0.0D, 0.0D, 0.0D);
                level.addParticle(ParticleTypes.FLAME, x + 0.75F, y + 1.2F, z + 0.5F, 0.0D, 0.0D, 0.0D);

                level.addParticle(ParticleTypes.SMOKE, x + 0.25F, y + 1.2F, z + 0.75F, 0.0D, 0.0D, 0.0D);
                level.addParticle(ParticleTypes.FLAME, x + 0.25F, y + 1.2F, z + 0.75F, 0.0D, 0.0D, 0.0D);

                level.addParticle(ParticleTypes.SMOKE, x + 0.5F, y + 1.2F, z + 0.75F, 0.0D, 0.0D, 0.0D);
                level.addParticle(ParticleTypes.FLAME, x + 0.5F, y + 1.2F, z + 0.75F, 0.0D, 0.0D, 0.0D);

                level.addParticle(ParticleTypes.SMOKE, x + 0.75F, y + 1.2F, z + 0.75F, 0.0D, 0.0D, 0.0D);
                level.addParticle(ParticleTypes.FLAME, x + 0.75F, y + 1.2F, z + 0.75F, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}

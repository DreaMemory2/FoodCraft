package com.crystal.foodcraft.block;

import com.crystal.foodcraft.block.entity.BMMachineBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

public class BeverageMakingMachine extends BaseMachineBlock {
    public static final MapCodec<BaseMachineBlock> CODEC = simpleCodec(BeverageMakingMachine::new);

    public BeverageMakingMachine(Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull MapCodec<? extends BaseMachineBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new BMMachineBlockEntity(pos, state);
    }

    @NotNull
    @Override
    protected InteractionResult useWithoutItem(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull BlockHitResult hitResult) {
        if (!level.isClientSide() && level.getBlockEntity(pos) instanceof BMMachineBlockEntity machine) {
            player.openMenu(machine);
        }

        return InteractionResult.SUCCESS;
    }
}

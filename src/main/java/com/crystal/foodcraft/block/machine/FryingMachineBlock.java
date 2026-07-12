package com.crystal.foodcraft.block.machine;

import com.crystal.foodcraft.block.BaseMachineBlock;
import com.crystal.foodcraft.block.entity.ModBlockEntities;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.NotNull;

/**
 * <p>油炸机器</p>
 */
public class FryingMachineBlock extends BaseMachineBlock {
    public static final MapCodec<FryingMachineBlock> CODEC = simpleCodec(FryingMachineBlock::new);

    public FryingMachineBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockEntityType<? extends BlockEntity> getType() {
        return ModBlockEntities.FRYING_MACHINE;
    }
}

package com.crystal.foodcraft.block.machine;

import com.crystal.foodcraft.block.BaseMachineBlock;
import com.crystal.foodcraft.block.entity.ModBlockEntities;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.NotNull;

/**
 * <p>饮料制造机</p>
 */
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
    public BlockEntityType<? extends BlockEntity> getType() {
        return ModBlockEntities.BEVERAGE_MAKING_MACHINE;
    }
}

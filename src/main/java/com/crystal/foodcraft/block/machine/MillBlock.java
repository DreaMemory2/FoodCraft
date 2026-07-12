package com.crystal.foodcraft.block.machine;

import com.crystal.foodcraft.block.BaseMachineBlock;
import com.crystal.foodcraft.block.entity.ModBlockEntities;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.NotNull;

/**
 * <p>磨粉机</p>
 * @see net.minecraft.world.level.block.FurnaceBlock
 */
public class MillBlock extends BaseMachineBlock {
    public static final MapCodec<MillBlock> CODEC = simpleCodec(MillBlock::new);

    public MillBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull MapCodec<? extends BaseMachineBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockEntityType<? extends BlockEntity> getType() {
        return ModBlockEntities.MILL;
    }
}

package com.crystal.foodcraft.block.machine;

import com.crystal.foodcraft.block.BaseMachineBlock;
import com.crystal.foodcraft.block.entity.ModBlockEntities;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class PanBlock extends BaseMachineBlock {
    public static final MapCodec<PanBlock> CODEC = simpleCodec(PanBlock::new);
    private static final VoxelShape SHAPES = Block.box(0, 0, 0, 16, 2, 16);

    public PanBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull MapCodec<? extends PanBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockEntityType<? extends BlockEntity> getType() {
        return ModBlockEntities.PAN;
    }

    @Override
    public VoxelShape getShape() {
        return SHAPES;
    }
}

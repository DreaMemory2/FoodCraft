package com.crystal.foodcraft.block.machine;

import com.crystal.foodcraft.block.BaseMachineBlock;
import com.crystal.foodcraft.block.entity.ModBlockEntities;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class PotBlock extends BaseMachineBlock {
    public static final MapCodec<PotBlock> CODEC = simpleCodec(PotBlock::new);
    public static final VoxelShape SHAPE = Block.box(1, 0, 1, 14, 7, 14);

    public PotBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockEntityType<? extends BlockEntity> getType() {
        return ModBlockEntities.POT;
    }

    @Override
    public VoxelShape getShape() {
        return SHAPE;
    }
}

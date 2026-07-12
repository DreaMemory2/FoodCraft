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

/**
 * <p>高压锅</p>
 */
public class PressureCookerBlock extends BaseMachineBlock {
    public static final VoxelShape SHAPE = Block.box(2, 0, 2, 14, 13, 14);
    public static final MapCodec<PressureCookerBlock> CODEC = simpleCodec(PressureCookerBlock::new);

    public PressureCookerBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape() {
        return SHAPE;
    }

    @Override
    public BlockEntityType<? extends BlockEntity> getType() {
        return ModBlockEntities.PRESSURE_COOKER;
    }
}

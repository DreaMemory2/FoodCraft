package com.crystal.foodcraft.block;

import com.crystal.foodcraft.api.TickableBlockEntity;
import com.mojang.serialization.MapCodec;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

/**
 * @see net.minecraft.world.level.block.AbstractFurnaceBlock
 */
public abstract class BaseMachineBlock extends BaseEntityBlock {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final EnumProperty<@NotNull Direction> FACING = HorizontalDirectionalBlock.FACING;

    public BaseMachineBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(LIT, false));
    }

    abstract public BlockEntityType<? extends BlockEntity> getType();

    @NotNull
    abstract protected MapCodec<? extends BaseEntityBlock> codec();

    @NotNull
    @Override
    protected VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return getShape();
    }

    public VoxelShape getShape() {
        return cube(16);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos worldPosition, @NotNull BlockState blockState) {
        return getType().create(worldPosition, blockState);
    }

    @NotNull
    @Override
    protected InteractionResult useWithoutItem(@NotNull BlockState state, @NotNull Level world, @NotNull BlockPos pos, @NotNull Player player, @NotNull BlockHitResult hitResult) {
        if (!world.isClientSide()) {
            player.openMenu((MenuProvider) world.getBlockEntity(pos));
        }

        return InteractionResult.SUCCESS;
    }

    /**
     * <p>当玩家手持方块右键放置时，游戏会自动调用该方法</p>
     * <p>方块方向取反，方块正面始终朝向玩家</p>
     */
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    /**
     * <p>当玩家对方块左键移除方块时，清空方块内物品并掉落容器内所有物品</p>
     * <p>方块破坏后，则触发周边方块红石邻居更新（NC）</p>
     * @see Containers#updateNeighboursAfterDestroy(BlockState, Level, BlockPos)
     */
    @Override
    @Environment(EnvType.SERVER)
    protected void affectNeighborsAfterRemoval(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, boolean movedByPiston) {
        Containers.updateNeighboursAfterDestroy(state, level, pos);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level world, @NotNull BlockState blockState, @NotNull BlockEntityType<T> type) {
        return TickableBlockEntity.getTicker(world);
    }

    /**
     * <p>方块是否支持红石信号传输</p>
     */
    @Override
    protected boolean hasAnalogOutputSignal(@NotNull BlockState state) {
        return true;
    }

    @NotNull
    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @NotNull
    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, @NotNull BlockState> builder) {
        builder.add(FACING, LIT);
    }
}

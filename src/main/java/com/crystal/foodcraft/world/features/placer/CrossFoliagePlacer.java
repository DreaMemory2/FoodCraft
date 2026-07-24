package com.crystal.foodcraft.world.features.placer;

import com.crystal.foodcraft.world.features.config.FruitTreeConfiguration;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;

/**
 * @see net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer FoliagePlacer
 * @see net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer BlobFoliagePlacer
 */
public class CrossFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<CrossFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(
            instance -> foliagePlacerParts(instance)
                    .and(Codec.intRange(3, 5).fieldOf("height").forGetter(placer -> placer.height))
                    .apply(instance, CrossFoliagePlacer::new));
    protected final int height;

    public CrossFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset);
        this.height = height;
    }

    @Override
    protected @NotNull FoliagePlacerType<?> type() {
        return IFoliagePlacerType.FOLIAGE_PLACER;
    }

    @Override
    protected void createFoliage(
            @NotNull WorldGenLevel level,
            @NotNull FoliageSetter setter,
            @NotNull RandomSource random,
            @NotNull TreeConfiguration config,
            int treeHeight,
            @NotNull FoliageAttachment foliageAttachment,
            int foliageHeight,
            int leafRadius,
            int offset
    ) {

    }

    public void createFoliage(
            WorldGenLevel level,
            FoliagePlacer.FoliageSetter foliageSetter,
            RandomSource random,
            FruitTreeConfiguration config,
            int treeHeight,
            FoliagePlacer.FoliageAttachment foliageAttachment,
            int foliageHeight,
            int leafRadius
    ) {
        this.createFoliage(level, foliageSetter, random, config, treeHeight, foliageAttachment, foliageHeight, leafRadius, this.offset(random));
    }

    public void createFoliage(
            WorldGenLevel level,
            FoliageSetter setter,
            RandomSource random,
            FruitTreeConfiguration config,
            int treeHeight,
            FoliageAttachment foliageAttachment,
            int foliageHeight,
            int leafRadius,
            int offset
    ) {
        // 一个附着点
        BlockPos origin = foliageAttachment.pos();
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        // 顶部的树叶
        pos.setWithOffset(origin, 0, 0, 0);
        tryPlaceLeaf(level, setter, random, config, pos);
        // 除了向下方向，总共14个方向的树叶以及托叶
        setStraightLeaf(level, setter, random, config, origin, leafRadius, pos);
        setDiagonalLeaf(level, setter, random, config, origin, leafRadius, pos);
        setStipuleLeaf(level, setter, random, config, origin, pos);
    }

    /**
     * <p>设置十字型树的四个方向的树叶</p>
     */
    private void setStraightLeaf(
            WorldGenLevel level,
            FoliageSetter setter,
            RandomSource random,
            FruitTreeConfiguration config,
            BlockPos origin,
            int leafRadius,
            BlockPos.MutableBlockPos pos
    ) {
        for (int i = 1; i <= leafRadius; i++) {
            pos.setWithOffset(origin, i, -i, 0);
            tryPlaceLeaf(level, setter, random, config, pos);
            pos.setWithOffset(origin, -i, -i, 0);
            tryPlaceLeaf(level, setter, random, config, pos);
        }
        for (int i = 1; i <= leafRadius; i++) {
            pos.setWithOffset(origin, 0, -i, i);
            tryPlaceLeaf(level, setter, random, config, pos);
            pos.setWithOffset(origin, 0, -i, -i);
            tryPlaceLeaf(level, setter, random, config, pos);
        }
    }

    /**
     * <p>设置十字型树的四个对角线方向的树叶</p>
     */
    private void setDiagonalLeaf(
            WorldGenLevel level,
            FoliageSetter setter,
            RandomSource random,
            FruitTreeConfiguration config,
            BlockPos origin,
            int leafRadius,
            BlockPos.MutableBlockPos pos
    ) {
        for (int i = 1; i <= leafRadius - 1; i++) {
            pos.setWithOffset(origin, i, -i - 1, i);
            tryPlaceLeaf(level, setter, random, config, pos);
            pos.setWithOffset(origin, -i, -i - 1, -i);
            tryPlaceLeaf(level, setter, random, config, pos);
        }
        for (int i = 1; i <= leafRadius - 1; i++) {
            pos.setWithOffset(origin, -i, -i - 1, i);
            tryPlaceLeaf(level, setter, random, config, pos);
            pos.setWithOffset(origin, i, -i - 1, -i);
            tryPlaceLeaf(level, setter, random, config, pos);
        }
    }

    /**
     * <p>设置十字型树的四个托叶子的树叶</p>
     */
    private void setStipuleLeaf(
            WorldGenLevel level,
            FoliageSetter setter,
            RandomSource random,
            FruitTreeConfiguration config,
            BlockPos origin,
            BlockPos.MutableBlockPos pos
    ) {
        pos.setWithOffset(origin, 1, -3, 0);
        tryPlaceLeaf(level, setter, random, config, pos);
        pos.setWithOffset(origin, -1, -3, 0);
        tryPlaceLeaf(level, setter, random, config, pos);
        pos.setWithOffset(origin, 0, -3, 1);
        tryPlaceLeaf(level, setter, random, config, pos);
        pos.setWithOffset(origin, 0, -3, -1);
        tryPlaceLeaf(level, setter, random, config, pos);
    }

    private int offset(final RandomSource random) {
        return this.offset.sample(random);
    }

    public int foliageHeight(RandomSource random, int treeHeight, FruitTreeConfiguration config) {
        return this.height;
    }

    protected static boolean tryPlaceLeaf(WorldGenLevel level, FoliageSetter setter, RandomSource random, FruitTreeConfiguration config, BlockPos pos) {
        // 判断当前位置是否存在持久树叶，即不凋落树叶
        boolean isPersistent = level.isStateAtPosition(pos, state -> state.getValueOrElse(BlockStateProperties.PERSISTENT, false));
        /*
          1. 该位置不是玩家手动放置的持久树叶（保护玩家建筑）
          2. 坐标是合法树木生成位置。
         */
        if (!isPersistent && TreeFeature.validTreePos(level, pos)) {
            // 获取树叶方块状态
            BlockState state = config.foliageProvider.getState(level, random, pos);
            // 自动含水方块
            if (state.hasProperty(BlockStateProperties.WATERLOGGED)) {
                state = state.setValue(BlockStateProperties.WATERLOGGED,
                        level.isFluidAtPosition(pos, fluidState -> fluidState.isSourceOfType(Fluids.WATER)));
            }
            // 写入状态
            setter.set(pos, state);
            return true;
        }
        return false;
    }

    @Override
    public int foliageHeight(@NotNull RandomSource random, int treeHeight, @NotNull TreeConfiguration config) {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(@NotNull RandomSource random, int dx, int y, int dz, int currentRadius, boolean doubleTrunk) {
        return true;
    }
}

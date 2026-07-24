package com.crystal.foodcraft.world.features.placer;

import com.crystal.foodcraft.world.features.config.FruitTreeConfiguration;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @see net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer StraightTrunkPlacer
 */
public abstract class FruitTreeTrunkPlacer extends TrunkPlacer {

    /**
     * <p>高度变化梯度单一；两段叠加能产生更多高度组合，树林高低错落更自然</p>
     * <p>两段独立随机叠加，让高度分布更均匀、不会出现阶梯断层</p>
     * <p>例子：</p>
     * <pre><code>
     *     // 树干的基本高度
     *     int baseHeight = 4;
     *     // 第一段树干增长高度
     *     int heightRandA = 0 & 1;
     *     // 第二段树干增长高度
     *     int heightRandB = 0 & 1 & 2 & 3;
     *     // 最小树干的高度
     *     int minHeight = 4 + 0 + 0 = 4;
     *     // 最大树干的高度
     *     int maxHeight = 4 + 1 + 3 = 8;
     *     // 树干高度范围
     *     int treeHeight = [4, 8];
     * </code></pre>
     * @param baseHeight 基本树干高度
     * @param heightRandA 第一段随机高度
     * @param heightRandB 第二段随机高度
     */
    public FruitTreeTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    public @NotNull List<FoliagePlacer.FoliageAttachment> placeTrunk(
            @NotNull WorldGenLevel level,
            @NotNull BiConsumer<BlockPos, BlockState> trunkSetter,
            @NotNull RandomSource random,
            int treeHeight,
            @NotNull BlockPos origin,
            @NotNull TreeConfiguration config
    ) {
        return List.of();
    }

    public abstract CrossFoliagePlacer.FoliageAttachment placeTrunk(
            WorldGenLevel level,
            BiConsumer<BlockPos, BlockState> trunkSetter,
            RandomSource random,
            int treeHeight,
            BlockPos origin,
            FruitTreeConfiguration config
    );

    protected static void placeBelowTrunkBlock(
            WorldGenLevel level,
            BiConsumer<BlockPos, BlockState> trunkSetter,
            RandomSource random,
            BlockPos pos,
            FruitTreeConfiguration config
    ) {
        BlockState blockBelowTrunk = config.belowTrunkProvider.getOptionalState(level, random, pos);
        if (blockBelowTrunk != null) {
            trunkSetter.accept(pos, blockBelowTrunk);
        }
    }

    protected boolean placeLog(
            WorldGenLevel level,
            BiConsumer<BlockPos, BlockState> trunkSetter,
            RandomSource random,
            BlockPos pos,
            FruitTreeConfiguration config
    ) {
        return this.placeLog(level, trunkSetter, random, pos, config, Function.identity());
    }

    protected boolean placeLog(
            WorldGenLevel level,
            BiConsumer<BlockPos, BlockState> trunkSetter,
            RandomSource random,
            BlockPos pos,
            FruitTreeConfiguration config,
            Function<BlockState, BlockState> stateModifier
    ) {
        if (this.validTreePos(level, pos)) {
            trunkSetter.accept(pos, stateModifier.apply(config.trunkProvider.getState(level, random, pos)));
            return true;
        } else {
            return false;
        }
    }
}

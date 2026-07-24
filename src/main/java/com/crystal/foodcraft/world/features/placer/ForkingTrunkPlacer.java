package com.crystal.foodcraft.world.features.placer;

import com.crystal.foodcraft.world.features.config.FruitTreeConfiguration;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.jetbrains.annotations.NotNull;

import java.util.OptionalInt;
import java.util.function.BiConsumer;

public class ForkingTrunkPlacer extends FruitTreeTrunkPlacer {
    public static final MapCodec<ForkingTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(
            instance -> trunkPlacerParts(instance)
                    .apply(instance, ForkingTrunkPlacer::new)
    );

    public ForkingTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected @NotNull TrunkPlacerType<?> type() {
        return ITrunkPlacerType.FORKING_TRUNK_PLACER;
    }

    @Override
    public CrossFoliagePlacer.FoliageAttachment placeTrunk(
            WorldGenLevel level,
            BiConsumer<BlockPos, BlockState> trunkSetter,
            RandomSource random,
            int treeHeight,
            BlockPos origin,
            FruitTreeConfiguration config
    ) {
        // 树根正下方一格放置树根辅助方块（例如草方块、泥土等，防止悬浮）。
        placeBelowTrunkBlock(level, trunkSetter, random, origin.below(), config);
        // 随机选取一个水平方向（东西南北）作为树干倾斜方向
        Direction leanDirection = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        // 树干从这个高度开始倾斜：总高度 - 随机0~4 -1
        int leanHeight = treeHeight - random.nextInt(4) - 1;
        // 倾斜步数：0~4步（最多偏移4格）
        int leanSteps = 4 - random.nextInt(4);
        BlockPos.MutableBlockPos logPos = new BlockPos.MutableBlockPos();
        int tx = origin.getX();
        int tz = origin.getZ();
        // 记录树冠起点Y坐标
        OptionalInt ey = OptionalInt.empty();
        /* 生成倾斜主干 */
        for (int yo = 0; yo < treeHeight; yo++) {
            int yy = origin.getY() + yo;
            // 到达倾斜高度 且 还有剩余偏移步数
            if (yo >= leanHeight && leanSteps > 0) {
                tx += leanDirection.getStepX();
                tz += leanDirection.getStepZ();
                leanSteps--;
            }
            // 放置原木方块，返回true代表成功放置，记录树叶起始高度
            if (this.placeLog(level, trunkSetter, random, logPos.set(tx, yy, tz), config)) {
                ey = OptionalInt.of(yy + 1);
            }
        }
        if (ey.isPresent()) {
            return new CrossFoliagePlacer.FoliageAttachment(new BlockPos(tx, ey.getAsInt(), tz), 1, false);
        }
        return new FoliagePlacer.FoliageAttachment(BlockPos.ZERO, 1, false);
    }
}

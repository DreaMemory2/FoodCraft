package com.crystal.foodcraft.world.features;

import com.crystal.foodcraft.block.BigLeavesBlock;
import com.crystal.foodcraft.world.features.config.FruitTreeConfiguration;
import com.crystal.foodcraft.world.features.placer.CrossFoliagePlacer;
import com.crystal.foodcraft.world.features.placer.FruitPlacer;
import com.crystal.foodcraft.world.features.placer.FruitTreeTrunkPlacer;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.LevelWriter;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.shapes.BitSetDiscreteVoxelShape;
import net.minecraft.world.phys.shapes.DiscreteVoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * @see net.minecraft.world.level.levelgen.feature.TreeFeature TreeFeature
 */
public class FruitTreeFeatures extends Feature<@NotNull FruitTreeConfiguration> {

    public FruitTreeFeatures(Codec<FruitTreeConfiguration> codec) {
        super(codec);
    }

    private boolean doPlace(
            WorldGenLevel level,
            RandomSource random,
            BlockPos origin,
            BiConsumer<BlockPos, BlockState> rootSetter,
            BiConsumer<BlockPos, BlockState> trunkSetter,
            CrossFoliagePlacer.FoliageSetter foliageSetter,
            FruitPlacer.FruitSetter fruitSetter,
            FruitTreeConfiguration config
    ) {
        // 树的总高度（树叶+树干）
        int treeHeight = config.trunkPlacer.getTreeHeight(random);
        // 树叶的高度
        int foliageHeight = ((CrossFoliagePlacer) config.foliagePlacer).foliageHeight(random, treeHeight, config);
        // 树干的高度（树的总高度-叶子的高度）
        int trunkHeight = treeHeight - foliageHeight;
        // 树叶的半径
        int leafRadius = config.foliagePlacer.foliageRadius(random, trunkHeight);
        // 树干起始位置
        BlockPos trunkOrigin = config.rootPlacer.map(rootPlacer -> rootPlacer.getTrunkOrigin(origin, random)).orElse(origin);
        // 树干最低点
        int minY = Math.min(origin.getY(), trunkOrigin.getY());
        // 树干最高点，其中+1表示树干的延申部分
        int maxY = Math.max(origin.getY(), trunkOrigin.getY()) + treeHeight + 1;
        // 树在世界高度范围内，继续生成，否则为false
        if (minY >= level.getMinY() + 1 && maxY <= level.getMaxY() + 1) {
            // 上空空间校验，获取可生成截断高度
            // 最低允许生成高度
            OptionalInt minClippedHeight = config.minimumSize.minClippedHeight();
            // 最高允许生成高度
            int maxClippedTreeHeight = this.getMaxFreeTreeHeight(level, treeHeight, trunkOrigin, config);
            if (maxClippedTreeHeight >= treeHeight || minClippedHeight.isPresent() && maxClippedTreeHeight >= minClippedHeight.getAsInt()) {
                // 上空空间足够，允许生成
                // 生成树干
                FoliagePlacer.FoliageAttachment foliageAttachment = ((FruitTreeTrunkPlacer) config.trunkPlacer)
                        .placeTrunk(level, trunkSetter, random, maxClippedTreeHeight, trunkOrigin, config);
                // 通过一个附着点生成树叶
                ((CrossFoliagePlacer) config.foliagePlacer)
                        .createFoliage(level, foliageSetter, random, config, maxClippedTreeHeight, foliageAttachment, foliageHeight, leafRadius);
                // 通过一个附着点生成果实
                config.fruitPlacer
                        .createFruit(level, fruitSetter, random, config, maxClippedTreeHeight, foliageAttachment);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * 扫描树干上方所有方块，返回无阻挡的最大可用高度
     */
    private int getMaxFreeTreeHeight(WorldGenLevel level, int maxTreeHeight, BlockPos treePos, FruitTreeConfiguration config) {
        BlockPos.MutableBlockPos blockPos = new BlockPos.MutableBlockPos();

        for (int y = 0; y <= maxTreeHeight + 1; y++) {
            int r = config.minimumSize.getSizeAtHeight(maxTreeHeight, y);

            for (int x = -r; x <= r; x++) {
                for (int z = -r; z <= r; z++) {
                    blockPos.setWithOffset(treePos, x, y, z);
                    if (!config.trunkPlacer.isFree(level, blockPos) || !config.ignoreVines && isVine(level, blockPos)) {
                        return y - 2;
                    }
                }
            }
        }

        return maxTreeHeight;
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<@NotNull FruitTreeConfiguration> context) {
        final WorldGenLevel level = context.level();
        RandomSource random = context.random();
        // 特征生成基准坐标，树木花草以此点位为中心生成
        BlockPos origin = context.origin();
        // 香蕉树的配置
        FruitTreeConfiguration config = context.config();
        // 树根的位置
        Set<BlockPos> rootPositions = Sets.newHashSet();
        // 树干的位置
        Set<BlockPos> trunks = Sets.newHashSet();
        // 树叶的位置
        final Set<BlockPos> foliage = Sets.newHashSet();
        // 水果的位置
        final Set<BlockPos> fruit = Sets.newHashSet();
        // 树木的装饰，例如：藤曼，沼泽树等
        Set<BlockPos> decorations = Sets.newHashSet();
        // 设置
        BiConsumer<BlockPos, BlockState> rootSetter = (pos, state) -> {
            rootPositions.add(pos.immutable());
            level.setBlock(pos, state, 19);
        };
        // 树干设置
        BiConsumer<BlockPos, BlockState> trunkSetter = (pos, state) -> {
            trunks.add(pos.immutable());
            level.setBlock(pos, state, 19);
        };
        // 树叶设置
        CrossFoliagePlacer.FoliageSetter foliageSetter = new CrossFoliagePlacer.FoliageSetter() {

            @Override
            public void set(BlockPos pos, @NotNull BlockState state) {
                foliage.add(pos.immutable());
                level.setBlock(pos, state, 19);
            }

            @Override
            public boolean isSet(@NotNull BlockPos pos) {
                return foliage.contains(pos);
            }
        };
        // 水果设置
        FruitPlacer.FruitSetter fruitSetter = new FruitPlacer.FruitSetter() {
            @Override
            public void set(BlockPos pos, BlockState state) {
                fruit.add(pos.immutable());
                level.setBlock(pos, state, 19);
            }

            @Override
            public boolean isSet(BlockPos pos) {
                return fruit.contains(pos);
            }
        };
        // 装饰设置
        BiConsumer<BlockPos, BlockState> decorationSetter = (pos, state) -> {
            decorations.add(pos.immutable());
            level.setBlock(pos, state, 19);
        };
        boolean result = this.doPlace(level, random, origin, rootSetter, trunkSetter, foliageSetter, fruitSetter, config);
        // 树干设置或树叶设置至少有一个不为空，若只生成树根，没有树干和树叶返回false
        if (result && (!trunks.isEmpty() || !foliage.isEmpty())) {
            // 执行树木装饰器
            if (!config.decorators.isEmpty()) {
                TreeDecorator.Context decoratoeContext = new TreeDecorator.Context(level, decorationSetter, random, trunks, foliage, rootPositions);
                config.decorators.forEach(decorator -> decorator.place(decoratoeContext));
            }

            return BoundingBox.encapsulatingPositions(Iterables.concat(rootPositions, trunks, foliage, fruit, decorations)).map(bounds -> {
                DiscreteVoxelShape shape = updateShapeLeaves(level, bounds, trunks, fruit, decorations, rootPositions);
                StructureTemplate.updateShapeAtEdge(level, 3, shape, bounds.minX(), bounds.minY(), bounds.minZ());
                return true;
            }).orElse(false);
        }
        return false;
    }

    /**
     * <p>批量计算树叶DISTANCE属性</p>
     * <p>区块动态计算，而树木生成阶段一次性 BFS 预计算，避免游戏内实时卡顿</p>
     * <p>构建树木体素形状</p>
     * <p>记录整棵树所有实体方块（根、原木、装饰、树叶），用于区块渲染、碰撞、结构模板优化缓存</p>
     */
    private static DiscreteVoxelShape updateShapeLeaves(
            LevelAccessor level,
            BoundingBox bounds,
            Set<BlockPos> logs,
            Set<BlockPos> fruit,
            Set<BlockPos> decorationSet,
            Set<BlockPos> rootPositions
    ) {
        // 根据包围盒长宽高创建空体素
        DiscreteVoxelShape shape = new BitSetDiscreteVoxelShape(bounds.getXSpan(), bounds.getYSpan(), bounds.getZSpan());
        int maxDistance = 7;
        // 创建距离原木0-7的集合列表
        List<Set<BlockPos>> toCheck = Lists.newArrayList();
        for (int i = 0; i < 7; i++) {
            toCheck.add(Sets.newHashSet());
        }

        // 树根、装饰和水果不会参与树叶距离扩散，它们只是固定实体，不作为 BFS 起点
        for (BlockPos pos : Lists.newArrayList(Sets.union(decorationSet, rootPositions))) {
            // 若坐标在包围盒内
            if (bounds.isInside(pos)) {
                // 在体素 shape 标记该位置为实心方块
                shape.fill(pos.getX() - bounds.minX(), pos.getY() - bounds.minY(), pos.getZ() - bounds.minZ());
            }
        }
        for (BlockPos pos : fruit) {
            if (bounds.isInside(pos)) {
                shape.fill(pos.getX() - bounds.minX(), pos.getY() - bounds.minY(), pos.getZ() - bounds.minZ());
            }
        }

        BlockPos.MutableBlockPos neighborPos = new BlockPos.MutableBlockPos();
        int smallestDistance = 0;
        toCheck.getFirst().addAll(logs);

        while (true) {
            while (smallestDistance >= 7 || !toCheck.get(smallestDistance).isEmpty()) {
                // 如果最小距离大于等于7的树叶不会被写入体素，生成后会自动腐烂消失
                if (smallestDistance >= 7) {
                    return shape;
                }

                Iterator<BlockPos> iterator = toCheck.get(smallestDistance).iterator();
                BlockPos posx = (BlockPos)iterator.next();
                iterator.remove();
                // 当前坐标在包围盒内才处理
                if (bounds.isInside(posx)) {
                    // 非原木：更新树叶distance状态
                    if (smallestDistance != 0) {
                        BlockState state = level.getBlockState(posx);
                        setBlockKnownShape(level, posx, state.setValue(BlockStateProperties.DISTANCE, smallestDistance));
                    }
                    // 标记到体素形状
                    shape.fill(posx.getX() - bounds.minX(), posx.getY() - bounds.minY(), posx.getZ() - bounds.minZ());
                    // 遍历3*3*3区域
                    for (int dx = -1; dx <= 1; dx++) {
                        for (int dy = -1; dy <= 1; dy++) {
                            for (int dz = -1; dz <= 1; dz++) {
                                neighborPos.setWithOffset(posx, dx, dy, dz);
                                if (bounds.isInside(neighborPos)) {
                                    int xInShape = neighborPos.getX() - bounds.minX();
                                    int yInShape = neighborPos.getY() - bounds.minY();
                                    int zinShape = neighborPos.getZ() - bounds.minZ();
                                    // 该位置还未被标记为实体
                                    if (!shape.isFull(xInShape, yInShape, zinShape)) {
                                        BlockState currentState = level.getBlockState(neighborPos);
                                        OptionalInt distance = BigLeavesBlock.getOptionalDistanceAt(currentState);
                                        if (distance.isPresent()) {
                                            int newDistance = Math.min(distance.getAsInt(), smallestDistance + 1);
                                            if (newDistance < 7) {
                                                toCheck.get(newDistance).add(neighborPos.immutable());
                                                smallestDistance = Math.min(smallestDistance, newDistance);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            smallestDistance++;
        }
    }

    public static boolean isVine(LevelSimulatedReader level, BlockPos pos) {
        return level.isStateAtPosition(pos, state -> state.is(Blocks.VINE));
    }

    private static void setBlockKnownShape(LevelWriter level, BlockPos pos, BlockState blockState) {
        level.setBlock(pos, blockState, 19);
    }
}

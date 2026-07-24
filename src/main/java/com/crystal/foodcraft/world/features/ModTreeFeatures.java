package com.crystal.foodcraft.world.features;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.block.ModBlocks;
import com.crystal.foodcraft.world.features.config.FruitTreeConfiguration;
import com.crystal.foodcraft.world.features.placer.CrossFoliagePlacer;
import com.crystal.foodcraft.world.features.placer.ForkingTrunkPlacer;
import com.crystal.foodcraft.world.features.placer.FruitPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

/**
 * @see net.minecraft.data.worldgen.features.TreeFeatures TreeFeatures
 */
public class ModTreeFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> PEAR = createKey("pear");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LYCHEE = createKey("lychee");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PEACH = createKey("peach");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORANGE = createKey("orange");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LOQUAT = createKey("loquat");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MANGO = createKey("mango");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LEMON = createKey("lemon");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GRAPEFRUIT = createKey("grapefruit");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PERSIMMON = createKey("persimmon");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PAPAYA = createKey("papaya");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HAWTHORN = createKey("hawthorn");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LONGYAN = createKey("longyan");
    public static final ResourceKey<ConfiguredFeature<?, ?>> POMEGRANATE = createKey("pomegranate");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CHINESE_DATE = createKey("chinese_date");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CHERRY = createKey("cherry");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BANANA = createKey("banana");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COCONUT = createKey("coconut");

    private static TreeConfiguration.TreeConfigurationBuilder createTreeWithLeaves(Block leaves) {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new StraightTrunkPlacer(4, 2, 0),
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                        .add(ModBlocks.LEAVES.defaultBlockState(), 3)
                        .add(leaves.defaultBlockState(), 1)),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1)
        );
    }

    private static FruitTreeConfiguration.Builder createBananaTree() {
        return new FruitTreeConfiguration.Builder(
                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                new com.crystal.foodcraft.world.features.placer.StraightTrunkPlacer(5, 2, 2),
                BlockStateProvider.simple(ModBlocks.JUNGLE_LEAVES),
                new CrossFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), 4),
                BlockStateProvider.simple(ModBlocks.BANANA),
                new FruitPlacer(ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 1)
        );
    }

    private static FruitTreeConfiguration.Builder createCoconutTree() {
        return new FruitTreeConfiguration.Builder(
                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                new ForkingTrunkPlacer(5, 3, 0),
                BlockStateProvider.simple(ModBlocks.JUNGLE_LEAVES),
                new CrossFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), 4),
                BlockStateProvider.simple(ModBlocks.COCONUT),
                new FruitPlacer(ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 1)
        );
    }

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        FeatureUtils.register(context, PEAR, Feature.TREE, createTreeWithLeaves(ModBlocks.PEAR_LEAVES).build());
        FeatureUtils.register(context, LYCHEE, Feature.TREE, createTreeWithLeaves(ModBlocks.LYCHEE_LEAVES).build());
        FeatureUtils.register(context, PEACH, Feature.TREE, createTreeWithLeaves(ModBlocks.PEACH_LEAVES).build());
        FeatureUtils.register(context, ORANGE, Feature.TREE, createTreeWithLeaves(ModBlocks.ORANGE_LEAVES).build());
        FeatureUtils.register(context, LOQUAT, Feature.TREE, createTreeWithLeaves(ModBlocks.LOQUAT_LEAVES).build());
        FeatureUtils.register(context, MANGO, Feature.TREE, createTreeWithLeaves(ModBlocks.MANGO_LEAVES).build());
        FeatureUtils.register(context, LEMON, Feature.TREE, createTreeWithLeaves(ModBlocks.LEMON_LEAVES).build());
        FeatureUtils.register(context, GRAPEFRUIT, Feature.TREE, createTreeWithLeaves(ModBlocks.GRAPEFRUIT_LEAVES).build());
        FeatureUtils.register(context, PERSIMMON, Feature.TREE, createTreeWithLeaves(ModBlocks.PERSIMMON_LEAVES).build());
        FeatureUtils.register(context, PAPAYA, Feature.TREE, createTreeWithLeaves(ModBlocks.PAPAYA_LEAVES).build());
        FeatureUtils.register(context, HAWTHORN, Feature.TREE, createTreeWithLeaves(ModBlocks.HAWTHORN_LEAVES).build());
        FeatureUtils.register(context, LONGYAN, Feature.TREE, createTreeWithLeaves(ModBlocks.LONGYAN_LEAVES).build());
        FeatureUtils.register(context, POMEGRANATE, Feature.TREE, createTreeWithLeaves(ModBlocks.POMEGRANATE_LEAVES).build());
        FeatureUtils.register(context, CHINESE_DATE, Feature.TREE, createTreeWithLeaves(ModBlocks.CHINESE_DATE_LEAVES).build());
        FeatureUtils.register(context, CHERRY, Feature.TREE, createTreeWithLeaves(ModBlocks.CHERRY_LEAVES).build());
        // 香蕉树
        FeatureUtils.register(context, BANANA, ModFeature.FRUIT_TREE, createBananaTree().build());
        // 椰子树
        FeatureUtils.register(context, COCONUT, ModFeature.FRUIT_TREE, createCoconutTree().build());
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, FoodCraft.of(name));
    }
}

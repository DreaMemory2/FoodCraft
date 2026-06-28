package com.crystal.foodcraft.world.placement;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.block.ModBlocks;
import com.crystal.foodcraft.world.features.ModTreeFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModTreePlacements {
    public static final ResourceKey<PlacedFeature> PEAR_CHECKED = createKey("pear_checked");
    public static final ResourceKey<PlacedFeature> LYCHEE_CHECKED = createKey("lychee_checked");
    public static final ResourceKey<PlacedFeature> PEACH_CHECKED = createKey("peach_checked");
    public static final ResourceKey<PlacedFeature> ORANGE_CHECKED = createKey("orange_checked");
    public static final ResourceKey<PlacedFeature> LOQUAT_CHECKED = createKey("loquat_checked");
    public static final ResourceKey<PlacedFeature> MANGO_CHECKED = createKey("mango_checked");
    public static final ResourceKey<PlacedFeature> LEMON_CHECKED = createKey("lemon_checked");
    public static final ResourceKey<PlacedFeature> GRAPEFRUIT_CHECKED = createKey("grapefruit_checked");
    public static final ResourceKey<PlacedFeature> PERSIMMON_CHECKED = createKey("persimmon_checked");
    public static final ResourceKey<PlacedFeature> PAPAYA_CHECKED = createKey("papaya_checked");
    public static final ResourceKey<PlacedFeature> HAWTHORN_CHECKED = createKey("hawthorn_checked");
    public static final ResourceKey<PlacedFeature> LONGYAN = createKey("longyan_checked");
    public static final ResourceKey<PlacedFeature> POMEGRANATE_CHECKED = createKey("pomegranate_checked");
    public static final ResourceKey<PlacedFeature> CHINESE_DATE_CHECKED = createKey("chinese_date_checked");
    public static final ResourceKey<PlacedFeature> CHERRY = createKey("cherry_checked");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        Holder<ConfiguredFeature<?, ?>> pear = configuredFeatures.getOrThrow(ModTreeFeatures.PEAR);
        Holder<ConfiguredFeature<?, ?>> lychee = configuredFeatures.getOrThrow(ModTreeFeatures.LYCHEE);
        Holder<ConfiguredFeature<?, ?>> peach = configuredFeatures.getOrThrow(ModTreeFeatures.PEACH);
        Holder<ConfiguredFeature<?, ?>> orange = configuredFeatures.getOrThrow(ModTreeFeatures.ORANGE);
        Holder<ConfiguredFeature<?, ?>> loquat = configuredFeatures.getOrThrow(ModTreeFeatures.LOQUAT);
        Holder<ConfiguredFeature<?, ?>> mango = configuredFeatures.getOrThrow(ModTreeFeatures.MANGO);
        Holder<ConfiguredFeature<?, ?>> lemon = configuredFeatures.getOrThrow(ModTreeFeatures.LEMON);
        Holder<ConfiguredFeature<?, ?>> grapefruit = configuredFeatures.getOrThrow(ModTreeFeatures.GRAPEFRUIT);
        Holder<ConfiguredFeature<?, ?>> persimmon = configuredFeatures.getOrThrow(ModTreeFeatures.PERSIMMON);
        Holder<ConfiguredFeature<?, ?>> papaya = configuredFeatures.getOrThrow(ModTreeFeatures.PAPAYA);
        Holder<ConfiguredFeature<?, ?>> hawthorn = configuredFeatures.getOrThrow(ModTreeFeatures.HAWTHORN);
        Holder<ConfiguredFeature<?, ?>> longyan = configuredFeatures.getOrThrow(ModTreeFeatures.LONGYAN);
        Holder<ConfiguredFeature<?, ?>> pomegranate = configuredFeatures.getOrThrow(ModTreeFeatures.POMEGRANATE);
        Holder<ConfiguredFeature<?, ?>> chineseDate = configuredFeatures.getOrThrow(ModTreeFeatures.CHINESE_DATE);
        Holder<ConfiguredFeature<?, ?>> cherry = configuredFeatures.getOrThrow(ModTreeFeatures.CHERRY);
        PlacementUtils.register(context, PEAR_CHECKED, pear, PlacementUtils.filteredByBlockSurvival(ModBlocks.PEAR_SAPLING));
        PlacementUtils.register(context, LYCHEE_CHECKED, lychee, PlacementUtils.filteredByBlockSurvival(ModBlocks.LYCHEE_SAPLING));
        PlacementUtils.register(context, PEACH_CHECKED, peach, PlacementUtils.filteredByBlockSurvival(ModBlocks.PEACH_SAPLING));
        PlacementUtils.register(context, ORANGE_CHECKED, orange, PlacementUtils.filteredByBlockSurvival(ModBlocks.ORANGE_SAPLING));
        PlacementUtils.register(context, LOQUAT_CHECKED, loquat, PlacementUtils.filteredByBlockSurvival(ModBlocks.LOQUAT_SAPLING));
        PlacementUtils.register(context, MANGO_CHECKED, mango, PlacementUtils.filteredByBlockSurvival(ModBlocks.MANGO_SAPLING));
        PlacementUtils.register(context, LEMON_CHECKED, lemon, PlacementUtils.filteredByBlockSurvival(ModBlocks.LEMON_SAPLING));
        PlacementUtils.register(context, GRAPEFRUIT_CHECKED, grapefruit, PlacementUtils.filteredByBlockSurvival(ModBlocks.GRAPEFRUIT_SAPLING));
        PlacementUtils.register(context, PERSIMMON_CHECKED, persimmon, PlacementUtils.filteredByBlockSurvival(ModBlocks.PERSIMMON_SAPLING));
        PlacementUtils.register(context, PAPAYA_CHECKED, papaya, PlacementUtils.filteredByBlockSurvival(ModBlocks.PAPAYA_SAPLING));
        PlacementUtils.register(context, HAWTHORN_CHECKED, hawthorn, PlacementUtils.filteredByBlockSurvival(ModBlocks.HAWTHORN_SAPLING));
        PlacementUtils.register(context, LONGYAN, longyan, PlacementUtils.filteredByBlockSurvival(ModBlocks.LONGYAN_SAPLING));
        PlacementUtils.register(context, POMEGRANATE_CHECKED, pomegranate, PlacementUtils.filteredByBlockSurvival(ModBlocks.POMEGRANATE_SAPLING));
        PlacementUtils.register(context, CHINESE_DATE_CHECKED, chineseDate, PlacementUtils.filteredByBlockSurvival(ModBlocks.CHINESE_DATE_SAPLING));
        PlacementUtils.register(context, CHERRY, cherry, PlacementUtils.filteredByBlockSurvival(ModBlocks.CHERRY_SAPLING));
    }

    public static ResourceKey<PlacedFeature> createKey(String name) {
		return ResourceKey.create(Registries.PLACED_FEATURE, FoodCraft.of(name));
	}
}

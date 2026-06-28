package com.crystal.foodcraft.block;

import com.crystal.foodcraft.world.features.ModTreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Optional;

public class ModTreeGrower {
    public static final TreeGrower PEAR = createTree("pear", ModTreeFeatures.PEAR);
    public static final TreeGrower LYCHEE = createTree("lychee", ModTreeFeatures.LYCHEE);
    public static final TreeGrower PEACH = createTree("peach", ModTreeFeatures.PEACH);
    public static final TreeGrower ORANGE = createTree("orange", ModTreeFeatures.ORANGE);
    public static final TreeGrower LOQUAT = createTree("loquat", ModTreeFeatures.LOQUAT);
    public static final TreeGrower MANGO = createTree("mango", ModTreeFeatures.MANGO);
    public static final TreeGrower LEMON = createTree("lemon", ModTreeFeatures.LEMON);
    public static final TreeGrower GRAPEFRUIT = createTree("grapefruit", ModTreeFeatures.GRAPEFRUIT);
    public static final TreeGrower PERSIMMON = createTree("persimmon", ModTreeFeatures.PERSIMMON);
    public static final TreeGrower PAPAYA = createTree("papaya", ModTreeFeatures.PAPAYA);
    public static final TreeGrower HAWTHORN = createTree("hawthorn", ModTreeFeatures.HAWTHORN);
    public static final TreeGrower LONGYAN = createTree("longyan", ModTreeFeatures.LONGYAN);
    public static final TreeGrower POMEGRANATE = createTree("pomegranate", ModTreeFeatures.POMEGRANATE);
    public static final TreeGrower CHINSES_DATE = createTree("chinese_date", ModTreeFeatures.CHINESE_DATE);
    public static final TreeGrower CHERRY = createTree("cherry", ModTreeFeatures.CHERRY);

    public static TreeGrower createTree(String name, ResourceKey<ConfiguredFeature<?, ?>> tree) {
        return new TreeGrower(
                name,
                0.1F,
                Optional.empty(),
                Optional.empty(),
                Optional.of(tree),
                Optional.empty(),
                Optional.empty(),
                Optional.empty());
    }
}

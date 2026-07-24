package com.crystal.foodcraft.world.features.placer;

import com.crystal.foodcraft.FoodCraft;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.jetbrains.annotations.NotNull;

/**
 * @see TrunkPlacerType
 */
public class ITrunkPlacerType {
    public static final TrunkPlacerType<@NotNull StraightTrunkPlacer> FRUIT_TREE_TRUNK_PLACER = register("fruit_tree_trunk_planer", StraightTrunkPlacer.CODEC);
    public static final TrunkPlacerType<@NotNull ForkingTrunkPlacer> FORKING_TRUNK_PLACER = register("forking_fruit_tree_trunk_planer", ForkingTrunkPlacer.CODEC);

    public static <T extends TrunkPlacer> TrunkPlacerType<@NotNull T> register(String name, MapCodec<T> codec) {
        return Registry.register(BuiltInRegistries.TRUNK_PLACER_TYPE, FoodCraft.of(name), new TrunkPlacerType<>(codec));
    }

    public static void init() {

    }
}

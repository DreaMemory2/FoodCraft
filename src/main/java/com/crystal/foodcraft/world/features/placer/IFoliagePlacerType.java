package com.crystal.foodcraft.world.features.placer;

import com.crystal.foodcraft.FoodCraft;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jetbrains.annotations.NotNull;

public class IFoliagePlacerType {
    public static FoliagePlacerType<@NotNull CrossFoliagePlacer> FOLIAGE_PLACER = register("foliage_placer", CrossFoliagePlacer.CODEC);

    public static <P extends FoliagePlacer> FoliagePlacerType<@NotNull P> register(String name, MapCodec<P> codec) {
        return Registry.register(BuiltInRegistries.FOLIAGE_PLACER_TYPE, FoodCraft.of(name), new FoliagePlacerType<>(codec));
    }

    public static void init() {

    }
}

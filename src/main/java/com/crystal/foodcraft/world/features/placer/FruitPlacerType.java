package com.crystal.foodcraft.world.features.placer;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.register.ModRegistries;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;

public class FruitPlacerType<T extends FruitPlacer> {
    public static final FruitPlacerType<FruitPlacer> FRUIT_PLACER = register("fruit_type", FruitPlacer.MAP_CODEC);
    private final MapCodec<T> codec;

    public FruitPlacerType(MapCodec<T> codec) {
        this.codec = codec;
    }

    public MapCodec<T> codec() {
        return this.codec;
    }

    public static <T extends FruitPlacer> FruitPlacerType<T> register(String name, MapCodec<T> codec) {
        return Registry.register(ModRegistries.FRUIT_PLACER, FoodCraft.of(name), new FruitPlacerType<>(codec));
    }

    public static void init() {

    }
}

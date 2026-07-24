package com.crystal.foodcraft.world.features;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.world.features.config.FruitTreeConfiguration;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import org.jetbrains.annotations.NotNull;

/**
 * @see Feature
 */
public class ModFeature {
    public static final Feature<@NotNull FruitTreeConfiguration> FRUIT_TREE = register("fruit_tree", new FruitTreeFeatures(FruitTreeConfiguration.CODEC));

    private static <C extends FeatureConfiguration, F extends Feature<@NotNull C>> F register(String name, F feature) {
        return Registry.register(BuiltInRegistries.FEATURE, FoodCraft.of(name), feature);
    }

    public static void init() {

    }
}

package com.crystal.foodcraft.register;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.item.juice.Juice;
import com.crystal.foodcraft.world.features.placer.FruitPlacerType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class ModRegistryKeys {
    public static final ResourceKey<Registry<Juice>> JUICE = register("juice");
    public static final ResourceKey<Registry<FruitPlacerType<?>>> FRUIT_PLACER_TYPE = register("fruit_placer");

    public static <T> ResourceKey<Registry<T>> register(String name) {
        return ResourceKey.createRegistryKey(FoodCraft.of(name));
    }

}

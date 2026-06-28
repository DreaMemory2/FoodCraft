package com.crystal.foodcraft.register;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.item.juice.Juice;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class ModRegistryKeys {
    public static final ResourceKey<Registry<Juice>> JUICE = register("juice");

    public static <T> ResourceKey<Registry<T>> register(String name) {
        return ResourceKey.createRegistryKey(FoodCraft.of(name));
    }

}

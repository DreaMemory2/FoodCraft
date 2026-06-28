package com.crystal.foodcraft.register;

import com.crystal.foodcraft.item.juice.Juice;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class ModRegistries {
    public static final Registry<Juice> JUICE = register(ModRegistryKeys.JUICE);

    public static <T> Registry<T> register(ResourceKey<Registry<T>> key) {
        return FabricRegistryBuilder.create(key).attribute(RegistryAttribute.SYNCED).buildAndRegister();
    }

}

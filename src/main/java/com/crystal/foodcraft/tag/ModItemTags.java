package com.crystal.foodcraft.tag;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.item.juice.Juice;
import com.crystal.foodcraft.register.ModRegistryKeys;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {
    public static final TagKey<Item> KNIVES = bind("knives");

    public static final TagKey<Juice> JUICE = TagKey.create(ModRegistryKeys.JUICE, FoodCraft.of("juice"));

    private static TagKey<Item> bind(String name) {
        return TagKey.create(Registries.ITEM, FoodCraft.of(name));
    }
}

package com.crystal.foodcraft.tag;

import com.crystal.foodcraft.FoodCraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {
    public static final TagKey<Item> KNIVES = bind("knives");

    private static TagKey<Item> bind(String name) {
        return TagKey.create(Registries.ITEM, FoodCraft.of(name));
    }
}

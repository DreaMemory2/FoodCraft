package com.crystal.foodcraft.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class CommonItemTags {
    public static final TagKey<Item> MUSHROOM = bind("mushroom");
    public static final TagKey<Item> SALT = bind("salt");
    public static final TagKey<Item> EGG = bind("egg");
    public static final TagKey<Item> FISH = bind("fish");
    public static final TagKey<Item> COOKED_FISH = bind("cooked_fish");
    public static final TagKey<Item> DYES = bind("dyes");
    public static final TagKey<Item> BROWN = bind("dyes/brown");

    private static TagKey<Item> bind(String name) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", name));
    }
}

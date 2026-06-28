package com.crystal.foodcraft.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ForeignModTags {
    public static final TagKey<Block> HEAT_SOURCES = fdBlockTag("heat_sources");
    public static final TagKey<Block> HEAT_CONDUCTORS = fdBlockTag("heat_conductors");
    public static final TagKey<Block> TRAY_HEAT_SOURCES = fdBlockTag("tray_heat_sources");

    private static TagKey<Item> fdItemTag(String path) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("farmersdelight", path));
    }

    private static TagKey<Block> fdBlockTag(String path) {
        return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("farmersdelight", path));
    }
}

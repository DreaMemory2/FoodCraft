package com.crystal.foodcraft.tag;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.item.juice.Juice;
import com.crystal.foodcraft.register.ModRegistryKeys;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {
    public static final TagKey<Item> KNIVES = bind("knives");
    public static final TagKey<Item> TANGYUAN_STUFFING = bind("tangyuan_stuffing");
    public static final TagKey<Item> EMERALD_TOOL_MATERIALS = bind("emerald_tool_materials");
    public static final TagKey<Item> LEAVES = vanillaBind("leaves");
    public static final TagKey<Item> CIRCUIT_INGREDIENT = bind("circuit_ingredient");
    public static final TagKey<Item> MACHINE_INGREDIENT = bind("machine_ingredient");

    public static final TagKey<Juice> JUICE = TagKey.create(ModRegistryKeys.JUICE, FoodCraft.of("juice"));

    private static TagKey<Item> vanillaBind(String name) {
        return TagKey.create(Registries.ITEM, Identifier.withDefaultNamespace(name));
    }

    private static TagKey<Item> bind(String name) {
        return TagKey.create(Registries.ITEM, FoodCraft.of(name));
    }
}

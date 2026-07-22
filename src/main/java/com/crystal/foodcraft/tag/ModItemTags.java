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
    // 手册解锁配方
    public static final TagKey<Item> BLOCK_LOVERS = bind("block_lovers");
    public static final TagKey<Item> LOVELY_PLANT = bind("lovely_plant");
    public static final TagKey<Item> DELICIOUS_FOOD_I = bind("delicious_food_one");
    public static final TagKey<Item> DELICIOUS_FOOD_II = bind("delicious_food_two");
    public static final TagKey<Item> KFC_FOOD = bind("kfc_food");
    public static final TagKey<Item> CHINA_TRADITIONAL_FOOD = bind("china_traditional_food");
    public static final TagKey<Item> FRIED_FOOD = bind("fried_food");
    public static final TagKey<Item> ALCOHOLIC_RICH = bind("alcoholic_rich");
    public static final TagKey<Item> SWEET_DRINKS_I = bind("sweet_drinks_one");
    public static final TagKey<Item> SWEET_DRINKS_II = bind("sweet_drinks_two");
    public static final TagKey<Item> SALIVATING_STAPLE_FOOD_I = bind("salivating_staple_food_one");
    public static final TagKey<Item> SALIVATING_STAPLE_FOOD_II = bind("salivating_staple_food_two");
    public static final TagKey<Item> SALIVATING_STAPLE_FOOD_III = bind("salivating_staple_food_three");
    public static final TagKey<Item> SYMBOL_RICH = bind("symbol_rich");

    public static final TagKey<Juice> JUICE = TagKey.create(ModRegistryKeys.JUICE, FoodCraft.of("juice"));

    private static TagKey<Item> vanillaBind(String name) {
        return TagKey.create(Registries.ITEM, Identifier.withDefaultNamespace(name));
    }

    private static TagKey<Item> bind(String name) {
        return TagKey.create(Registries.ITEM, FoodCraft.of(name));
    }
}

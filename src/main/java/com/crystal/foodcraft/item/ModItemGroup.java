package com.crystal.foodcraft.item;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.block.ModBlocks;
import com.crystal.foodcraft.item.juice.JuiceContents;
import com.crystal.foodcraft.item.juice.Juices;
import com.crystal.foodcraft.register.ModRegistryKeys;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTab.DisplayItemsGenerator;
import net.minecraft.world.item.ItemStack;

public class ModItemGroup {

    public static final CreativeModeTab MACHINE = register("machine", FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.IRON_SHEET))
            .title(Component.translatable("itemGroup.foodcraft.machine"))
            .displayItems(machines())
            .build());

    public static final CreativeModeTab PLANT =  register("plant", FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.CHILI))
            .title(Component.translatable("itemGroup.foodcraft.plant"))
            .displayItems(plants())
            .build());

    public static final CreativeModeTab DRINK = register("drink", FabricCreativeModeTab.builder()
            .icon(() -> JuiceContents.createItemStack(ModItems.JUICE, Juices.GRAPE))
            .title(Component.translatable("itemGroup.foodcraft.drink"))
            .displayItems(drinks())
            .build());

    public static final CreativeModeTab STAPLE = register("staple", FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.COOKED_POTATO_WIRE_RICE))
            .title(Component.translatable("itemGroup.foodcraft.staple"))
            .displayItems(staples())
            .build());

    public static final CreativeModeTab INGREDIENT = register("ingredient", FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.FLOUR))
            .title(Component.translatable("itemGroup.foodcraft.ingredient"))
            .displayItems(ingredients())
            .build());

    public static final CreativeModeTab SNACK = register("snack", FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.FRIED_DUMPLING))
            .title(Component.translatable("itemGroup.foodcraft.snack"))
            .displayItems(snacks())
            .build());

    private static DisplayItemsGenerator machines() {
        return (context, entries) -> {
            entries.accept(ModItems.IRON_SHEET);
            entries.accept(ModItems.ITEM_BUCKET);
            entries.accept(ModItems.MACHINE_CORE);
            entries.accept(ModItems.KITCHEN_KNIFE);
            entries.accept(ModItems.GOLDEN_KITCHEN_KNIFE);
            entries.accept(ModItems.EMERALD_KITCHEN_KNIFE);
            entries.accept(ModItems.DIAMOND_KITCHEN_KNIFE);
            entries.accept(ModItems.WRENCH);
            entries.accept(ModItems.GOLDEN_BONE_MEAL);

            entries.accept(ModBlocks.RICE_MACHINE_SHELL);
            entries.accept(ModBlocks.MILL);
            entries.accept(ModBlocks.FRYING_MACHINE);
            entries.accept(ModBlocks.BEVERAGE_MAKING_MACHINE);
            entries.accept(ModBlocks.BREW_BARREL);
            entries.accept(ModBlocks.STOVE);
            entries.accept(ModBlocks.CHOPPING_BOARD);
            entries.accept(ModBlocks.PAN);
            entries.accept(ModBlocks.POT);
            entries.accept(ModBlocks.PRESSURE_COOKER);
            entries.accept(ModBlocks.MILLED_RICE);
            entries.accept(ModBlocks.SALT);
            entries.accept(ModBlocks.COCONUT);
            entries.accept(ModBlocks.BANANA);
            entries.accept(ModBlocks.SUGAR);
            entries.accept(ModBlocks.STICKY_RICE_BLOCK);
            entries.accept(ModBlocks.CHOCOLATE);
            entries.accept(ModBlocks.CARROT_BLOCK);

            entries.accept(ModBlocks.LEAVES);
            entries.accept(ModBlocks.JUNGLE_LEAVES);
            entries.accept(ModBlocks.PEAR_LEAVES);
            entries.accept(ModBlocks.LYCHEE_LEAVES);
            entries.accept(ModBlocks.PEACH_LEAVES);
            entries.accept(ModBlocks.ORANGE_LEAVES);
            entries.accept(ModBlocks.LOQUAT_LEAVES);
            entries.accept(ModBlocks.MANGO_LEAVES);
            entries.accept(ModBlocks.LEMON_LEAVES);
            entries.accept(ModBlocks.GRAPEFRUIT_LEAVES);
            entries.accept(ModBlocks.PERSIMMON_LEAVES);
            entries.accept(ModBlocks.PAPAYA_LEAVES);
            entries.accept(ModBlocks.HAWTHORN_LEAVES);
            entries.accept(ModBlocks.LONGYAN_LEAVES);
            entries.accept(ModBlocks.POMEGRANATE_LEAVES);
            entries.accept(ModBlocks.CHINESE_DATE_LEAVES);
            entries.accept(ModBlocks.CHERRY_LEAVES);
        };
    }

    private static DisplayItemsGenerator plants() {
        return (context, entries) -> {
            entries.accept(ModItems.RICE);
            entries.accept(ModItems.PEANUT);
            entries.accept(ModItems.BEANS);
            entries.accept(ModItems.STICKY_RICE);
            entries.accept(ModItems.RED_BEAN);
            entries.accept(ModItems.GREEN_BEAN);
            entries.accept(ModItems.WHITE_RADDISH);
            entries.accept(ModItems.VEGETABLE);
            entries.accept(ModItems.CHILI);
            entries.accept(ModItems.GREEN_PEPPER);
            entries.accept(ModItems.TOMATO);
            entries.accept(ModItems.EGGPLANT);
            entries.accept(ModItems.SCALLION);
            entries.accept(ModItems.LONG_BEAN);
            entries.accept(ModItems.CUCUMBER);
            entries.accept(ModItems.CORN);
            entries.accept(ModItems.SWEET_POTATO);
            entries.accept(ModItems.BAMBOO_LEAVES);
            entries.accept(ModItems.GRAPES);
            entries.accept(ModItems.GOLDEN_GRAPES);
            entries.accept(ModItems.PEAR);
            entries.accept(ModItems.LYCHEE);
            entries.accept(ModItems.PEACH);
            entries.accept(ModItems.ORANGE);
            entries.accept(ModItems.LOQUAT);
            entries.accept(ModItems.MANGO);
            entries.accept(ModItems.LEMON);
            entries.accept(ModItems.GRAPEFRUIT);
            entries.accept(ModItems.PERSIMMON);
            entries.accept(ModItems.PAPAYA);
            entries.accept(ModItems.HAWTHORN);
            entries.accept(ModItems.LONGYAN);
            entries.accept(ModItems.POMEGRANATE);
            entries.accept(ModItems.CHINESE_DATES);
            entries.accept(ModItems.STRAWBERRY);
            entries.accept(ModItems.COCONUT);
            entries.accept(ModItems.CHERRY);
            entries.accept(ModItems.BANANA);
            entries.accept(ModItems.VEGETABLE_SEEDS);
            entries.accept(ModItems.PEPPER_SEEDS);
            entries.accept(ModItems.TOMATO_SEEDS);
            entries.accept(ModItems.GRAPE_SEEDS);
            entries.accept(ModItems.EGGPLANT_SEEDS);
            entries.accept(ModItems.GREEN_PEPPER_SEEDS);
            entries.accept(ModItems.CORN_SEEDS);
            entries.accept(ModItems.CUCUMBER_SEEDS);
            // 树苗
            entries.accept(ModBlocks.PEAR_SAPLING);
            entries.accept(ModBlocks.LYCHEE_SAPLING);
            entries.accept(ModBlocks.PEACH_SAPLING);
            entries.accept(ModBlocks.ORANGE_SAPLING);
            entries.accept(ModBlocks.LOQUAT_SAPLING);
            entries.accept(ModBlocks.MANGO_SAPLING);
            entries.accept(ModBlocks.LEMON_SAPLING);
            entries.accept(ModBlocks.GRAPEFRUIT_SAPLING);
            entries.accept(ModBlocks.PERSIMMON_SAPLING);
            entries.accept(ModBlocks.PAPAYA_SAPLING);
            entries.accept(ModBlocks.HAWTHORN_SAPLING);
            entries.accept(ModBlocks.LONGYAN_SAPLING);
            entries.accept(ModBlocks.POMEGRANATE_SAPLING);
            entries.accept(ModBlocks.CHINESE_DATE_SAPLING);
            entries.accept(ModBlocks.CHERRY_SAPLING);
            entries.accept(ModBlocks.BANANA_SAPLING);
            entries.accept(ModBlocks.COCONUT_SAPLING);
        };
    }

    private static DisplayItemsGenerator drinks() {
        return (context, entries) -> {
            entries.accept(ModItems.SOY_BEAN_MILK);
            entries.accept(ModItems.RED_WINE);
            entries.accept(ModItems.SPIRIT);
            entries.accept(ModItems.GRAPE_WINE);
            entries.accept(ModItems.CIDER);
            entries.accept(ModItems.GOLDEN_GRAPE_WINE);
            entries.accept(ModItems.GOLDEN_CIDER);
            entries.accept(ModItems.PEACH_WINE);
            entries.accept(ModItems.LYCHEE_WINE);
            entries.accept(ModItems.PEAR_WINE);
            entries.accept(ModItems.MANGO_WINE);
            entries.accept(ModItems.LEMON_WINE);
            entries.accept(ModItems.POMEGRANATE_WINE);

            entries.accept(ModItems.CHOCOLATES_MILK_ICE_CREAM);
            entries.accept(ModItems.GRAPE_JUICE_ICE_CREAM);
            entries.accept(ModItems.APPLE_JUICE_ICE_CREAM);
            entries.accept(ModItems.GOLDEN_GRAPE_JUICE_ICE_CREAM);
            entries.accept(ModItems.GOLDEN_APPLE_JUICE_ICE_CREAM);
            entries.accept(ModItems.MELON_JUICE_ICE_CREAM);
            entries.accept(ModItems.LYCHEE_JUICE_ICE_CREAM);
            entries.accept(ModItems.PEACH_JUICE_ICE_CREAM);
            entries.accept(ModItems.ORANGE_JUICE_ICE_CREAM);
            entries.accept(ModItems.MANGO_JUICE_ICE_CREAM);
            entries.accept(ModItems.LEMON_JUICE_ICE_CREAM);
            entries.accept(ModItems.PAPAYA_JUICE_ICE_CREAM);
            entries.accept(ModItems.STRAWBERRY_JUICE_ICE_CREAM);
            entries.accept(ModItems.COCONUT_JUICE_ICE_CREAM);
            entries.accept(ModItems.BANANA_JUICE_ICE_CREAM);

            context.holders().lookup(ModRegistryKeys.JUICE).ifPresent(juices ->
                    juices.listElements().filter(juice -> juice.value().isEnabled(context.enabledFeatures()))
                            .map(juice -> JuiceContents.createItemStack(ModItems.JUICE, juice))
                            .forEach(stack -> entries.accept(stack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS))
            );
        };
    }

    private static DisplayItemsGenerator staples() {
        return (context, entries) -> {
            entries.accept(ModItems.MUSHROOMS_CHICKEN_SOUP);
            entries.accept(ModItems.EGG_SOUP);
            entries.accept(ModItems.NOODLES);
            entries.accept(ModItems.CROSSING_BRIDGE_NOODLES);
            entries.accept(ModItems.PRESERVED_EGG_PORRIDGE);
            entries.accept(ModItems.LA_BA_PORRIDGE);
            entries.accept(ModItems.TOMATO_EGG_RICE);
            entries.accept(ModItems.POTATO_PEPPER_EGGPLANT);
            entries.accept(ModItems.YUXIANG_SHREDDED_PORK);
            entries.accept(ModItems.KUNG_PAO_CHICKEN);
            entries.accept(ModItems.COOKED_POTATO_WIRE_RICE);
            entries.accept(ModItems.STEAMED_FISH_HEAD);
            entries.accept(ModItems.PEPPERY_DOUFU_RICE);
            entries.accept(ModItems.RED_BRAISED_PORK_BELLY_RICE);
            entries.accept(ModItems.TWICE_COOKED_PORK_SLICES);
            entries.accept(ModItems.ORLEAN_CHICKEN_RICE);
            entries.accept(ModItems.SPICY_CHICKEN);
            entries.accept(ModItems.STEAMED_CHICKEN_CHILI_SAUCE);
            entries.accept(ModItems.WHITE_SLICED_CHICKEN);
            entries.accept(ModItems.CHICKEN_SCALLION_OIL);
            entries.accept(ModItems.BOILED_FISH_SICHUAN_PICKLES);
            entries.accept(ModItems.SPICY_FISH);
            entries.accept(ModItems.STEAMED_FISH);
            entries.accept(ModItems.COLA_CHICKEN_RICE);
            entries.accept(ModItems.CURRY_CHICKEN_RICE);
            entries.accept(ModItems.SICHUAN_BOILED_BEEF);
            entries.accept(ModItems.PASTA);
            entries.accept(ModItems.PASTA_PORK);
            entries.accept(ModItems.PASTA_BEEF);
            entries.accept(ModItems.PASTA_CHICKEN);
            entries.accept(ModItems.JAPAN_DOUFU);
            entries.accept(ModItems.STEAMED_VERMICELLI_ROLL);
        };
    }

    private static DisplayItemsGenerator ingredients() {
        return (context, entries) -> {
            entries.accept(ModItems.WATER_ITEM);
            entries.accept(ModItems.MILLED_RICE);
            entries.accept(ModItems.FLOUR);
            entries.accept(ModItems.SALT);
            entries.accept(ModItems.DUMPLING_WRAPPERS);
            entries.accept(ModItems.WATERCRESS);
            entries.accept(ModItems.CHOCOLATE_DUST);
            entries.accept(ModItems.DUMPLING_MEAT);
            entries.accept(ModItems.PEANUT_OIL);
            entries.accept(ModItems.COOKED_RICE);
            entries.accept(ModItems.DRUMSTICK);
            entries.accept(ModItems.CHICKEN_WING);
            entries.accept(ModItems.BIG_CHICKEN);
            entries.accept(ModItems.MEDIUM_CHICKEN);
            entries.accept(ModItems.SMALL_CHICKEN);
            entries.accept(ModItems.POTATO_CHIPS);
            entries.accept(ModItems.POTATO_WIRE);
            entries.accept(ModItems.SOY_SAUCE);
            entries.accept(ModItems.VINEGAR);
            entries.accept(ModItems.RED_BEAN_PASTE);
            entries.accept(ModItems.STARCHES);
            entries.accept(ModItems.STICKY_RICE_DOUGH);
            entries.accept(ModItems.PEANUT_FILLING);
            entries.accept(ModItems.TOFU_STRIP);
            entries.accept(ModItems.CARROT_STRIP);
            entries.accept(ModItems.WHITE_RADDISH_STRIP);
            entries.accept(ModItems.NOODLE);
            entries.accept(ModItems.BLOCK_CURRY);
            entries.accept(ModItems.SMOKED_MATERIAL);
            entries.accept(ModItems.CHARRED_FOOD);
            entries.accept(ModItems.BISCUIT);
            entries.accept(ModItems.COCONUT_BISCUIT);
            entries.accept(ModItems.GOLDEN_GRAPES_BISCUIT);
            entries.accept(ModItems.GRAPES_BISCUIT);
            entries.accept(ModItems.LEMON_BISCUIT);
            entries.accept(ModItems.ORANGE_BISCUIT);
            entries.accept(ModItems.PEACH_BISCUIT);
            entries.accept(ModItems.PEAR_BISCUIT);
            entries.accept(ModItems.STRAWBERRY_BISCUIT);
            // 手册
            entries.accept(ModItems.BLOCK_LOVERS);
            entries.accept(ModItems.LOVELY_PLANT);
            entries.accept(ModItems.DELICIOUS_FOOD_I);
            entries.accept(ModItems.DELICIOUS_FOOD_II);
            entries.accept(ModItems.KFC_FOOD);
            entries.accept(ModItems.CHINA_TRADITIONAL_FOOD);
            entries.accept(ModItems.FRIED_FOOD);
            entries.accept(ModItems.ALCOHOLIC_RICH);
            entries.accept(ModItems.SWEET_DRINKS_I);
            entries.accept(ModItems.SWEET_DRINKS_II);
            entries.accept(ModItems.SALIVATING_STAPLE_FOOD_I);
            entries.accept(ModItems.SALIVATING_STAPLE_FOOD_II);
            entries.accept(ModItems.SALIVATING_STAPLE_FOOD_III);
            entries.accept(ModItems.SYMBOL_RICH);
        };
    }

    private static DisplayItemsGenerator snacks() {
        return (context, entries) -> {
            entries.accept(ModItems.SQUID_MEAT);
            entries.accept(ModItems.COOKED_SQUID_MEAT);
            entries.accept(ModItems.SQUID_SLICES);
            entries.accept(ModItems.POACHED_EGG);
            entries.accept(ModItems.PANCAKES);
            entries.accept(ModItems.DUMPLING);
            entries.accept(ModItems.FRIED_DUMPLING);
            entries.accept(ModItems.TOFU);
            entries.accept(ModItems.DRIED_TOFU);
            entries.accept(ModItems.FRIED_POTATO_CHIPS);
            entries.accept(ModItems.PORRIDGE);
            entries.accept(ModItems.STICKY_RICE_DUMPLING);
            entries.accept(ModItems.MOONCAKE);
            entries.accept(ModItems.TANGYUAN);
            entries.accept(ModItems.DOUGH_TWIST);
            entries.accept(ModItems.SPRING_ROLLS);
            entries.accept(ModItems.CHIPS);
            entries.accept(ModItems.SAUSAGE);
            entries.accept(ModItems.CHINESE_SAUSAGE);
            entries.accept(ModItems.CHINESE_BEAN);
            entries.accept(ModItems.FRIED_SAUSAGE);
            entries.accept(ModItems.PIZZA);
            entries.accept(ModItems.HAMBURGER);
            entries.accept(ModItems.BREAD_STICK);
            entries.accept(ModItems.CHILI_TOFU_STRIP);
            entries.accept(ModItems.FRIED_CHICKEN);
            entries.accept(ModItems.ORIGINAL_RECIPE);
            entries.accept(ModItems.FRIED_TOFU);
            entries.accept(ModItems.FRENCH_FRIES);
            entries.accept(ModItems.POPCORN_CHICKEN);
            entries.accept(ModItems.ORLEAN_WING);
            entries.accept(ModItems.FRIED_SPRING_ROLLS);
            entries.accept(ModItems.FRIED_DOUGH_TWIST);
            entries.accept(ModItems.FRIED_DRUM_STICK);
            entries.accept(ModItems.COOKED_SWEET_POTATO);
            entries.accept(ModItems.COOKED_CORN);
            entries.accept(ModItems.POPCORN);

            entries.accept(ModItems.CHOCOLATE);
            entries.accept(ModItems.NEW_YEAR_CAKE);
            entries.accept(ModItems.WALNUT_SHORT_BREAD);
            entries.accept(ModItems.GREEN_RICE_BALLS);
            entries.accept(ModItems.STICKY_RICE_CAKE);
            entries.accept(ModItems.CHEESE);
            entries.accept(ModItems.STEAMED_BUNS);
            entries.accept(ModItems.SAUERKRAUT_CAKE);
            entries.accept(ModItems.FRIED_NEW_YEAR_CAKE);
            entries.accept(ModItems.FRIED_BREAD);
            entries.accept(ModItems.HOT_DOG);
            entries.accept(ModItems.SMILEY_COOKIE);
            // Fruit Cakes
            entries.accept(ModBlocks.STRAWBERRY_CAKE.asItem());
            entries.accept(ModBlocks.GRAPES_CAKE.asItem());
            entries.accept(ModBlocks.GOLDEN_GRAPES_CAKE.asItem());
            entries.accept(ModBlocks.PEAR_CAKE.asItem());
            entries.accept(ModBlocks.PEACH_CAKE.asItem());
            entries.accept(ModBlocks.ORANGE_CAKE.asItem());
            entries.accept(ModBlocks.LEMON_CAKE.asItem());
            entries.accept(ModBlocks.COCONUT_CAKE.asItem());
            entries.accept(ModItems.COCONUT_JAM);
            entries.accept(ModItems.GOLDEN_GRAPES_JAM);
            entries.accept(ModItems.GRAPES_JAM);
            entries.accept(ModItems.LEMON_JAM);
            entries.accept(ModItems.ORANGE_JAM);
            entries.accept(ModItems.PEACH_JAM);
            entries.accept(ModItems.PEAR_JAM);
            entries.accept(ModItems.STRAWBERRY_JAM);
        };
    }

    private static CreativeModeTab register(String id, CreativeModeTab itemGroup) {
        ResourceKey<CreativeModeTab> key = ResourceKey.create(Registries.CREATIVE_MODE_TAB, FoodCraft.of(id));
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, key, itemGroup);
    }

    public static void init() {
    }
}

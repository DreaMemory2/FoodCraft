package com.crystal.foodcraft.item;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.block.ModBlocks;
import com.crystal.foodcraft.item.juice.JuiceContents;
import com.crystal.foodcraft.item.juice.Juices;
import com.crystal.foodcraft.register.ModDataComponents;
import com.crystal.foodcraft.item.juice.JuiceItem;
import com.crystal.foodcraft.register.ModFluids;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.function.Function;

/**
 * @see Items
 */
public class ModItems {
    /**------------------------------ Machines and Tools  ------------------------------*/
    public static final Item IRON_SHEET = register("iron_sheet");
    public static final Item ITEM_BUCKET = register("item_bucket");
    public static final Item MACHINE_CORE = register("machine_core");
    public static final Item KITCHEN_KNIFE = register("kitchen_knife", properties -> new KitchenKnifeItem(properties, ToolMaterial.IRON, 3.0F, -2.4F), new Item.Properties().stacksTo(1));
    public static final Item GOLDEN_KITCHEN_KNIFE = register("golden_kitchen_knife", properties -> new KitchenKnifeItem(properties, ToolMaterial.GOLD, 3.0F, -2.4F), new Item.Properties().stacksTo(1));
    public static final Item EMERALD_KITCHEN_KNIFE = register("emerald_kitchen_knife", properties -> new KitchenKnifeItem(properties, IToolMaterial.EMERALD, 8.0F, -7.2F), new Item.Properties().stacksTo(1));
    public static final Item DIAMOND_KITCHEN_KNIFE = register("diamond_kitchen_knife", properties -> new KitchenKnifeItem(properties, ToolMaterial.DIAMOND, 6.0F, -4.8F), new Item.Properties().stacksTo(1));
    public static final Item WRENCH = register("wrench");
    public static final Item GOLDEN_BONE_MEAL = register("golden_bone_meal");
    public static final Item ITEM_NULL = register("item_null");
    /**------------------------------ Plants  ------------------------------*/
    public static final Item RICE = register("rice", cropBlock(ModBlocks.RICE), new Item.Properties());
    public static final Item PEANUT = register("peanut", cropBlock(ModBlocks.PEANUT), new Item.Properties());
    public static final Item BEANS = register("beans", cropBlock(ModBlocks.BEANS), new Item.Properties());
    public static final Item STICKY_RICE = register("sticky_rice", cropBlock(ModBlocks.STICKY_RICE), new Item.Properties());
    public static final Item RED_BEAN = register("red_bean", cropBlock(ModBlocks.RED_BEAN), new Item.Properties());
    public static final Item GREEN_BEAN = register("green_bean", cropBlock(ModBlocks.GREEN_BEANS), new Item.Properties());
    public static final Item WHITE_RADDISH = register("white_raddish", cropBlock(ModBlocks.WHITE_RADDISH), new Item.Properties());
    public static final Item VEGETABLE = food("vegetable", ModFoods.VEGETABLE);
    public static final Item CHILI = register("chili");
    public static final Item GREEN_PEPPER = food("green_pepper", ModFoods.GREEN_PEPPER);
    public static final Item TOMATO = food("tomato", ModFoods.TOMATO);
    public static final Item EGGPLANT = food("eggplant", ModFoods.EGGPLANT);
    public static final Item SCALLION = register("scallion");
    public static final Item LONG_BEAN = food("long_bean", ModFoods.LONG_BEAN);
    public static final Item CUCUMBER = food("cucumber", ModFoods.CUCUMBER);
    public static final Item CORN = food("corn", ModFoods.CORN);
    public static final Item SWEET_POTATO = register("sweet_potato", cropBlock(ModBlocks.SWEET_POTATO), new Item.Properties());
    public static final Item BAMBOO_LEAVES = register("bamboo_leaves");
    public static final Item GRAPES = food("grapes", ModFoods.GRAPES);
    public static final Item GOLDEN_GRAPES = food("golden_grapes", ModFoods.GOLDEN_GRAPE);
    public static final Item PEAR = food("pear", ModFoods.PEAR);
    public static final Item LYCHEE = food("lychee", ModFoods.LYCHEE);
    public static final Item PEACH = food("peach", ModFoods.PEACH);
    public static final Item ORANGE = food("orange", ModFoods.ORANGE);
    public static final Item LOQUAT = food("loquat", ModFoods.LOQUAT);
    public static final Item MANGO = food("mango", ModFoods.MANGO);
    public static final Item LEMON = food("lemon", ModFoods.LEMON);
    public static final Item GRAPEFRUIT = food("grapefruit", ModFoods.GRAPEFRUIT);
    public static final Item PERSIMMON = food("persimmon", ModFoods.PERSIMMON);
    public static final Item PAPAYA = food("papaya", ModFoods.PAPAYA);
    public static final Item HAWTHORN = food("hawthorn", ModFoods.HAWTHORN);
    public static final Item LONGYAN = food("longyan", ModFoods.LONGYAN);
    public static final Item POMEGRANATE = food("pomegranate", ModFoods.POMEGRANATE);
    public static final Item CHINESE_DATES = food("chinese_date", ModFoods.CHINESE_DATES);
    public static final Item STRAWBERRY = register("strawberry", cropBlock(ModBlocks.STRAWBERRY), new Item.Properties());
    public static final Item COCONUT = food("coconut", ModFoods.COCONUT);
    public static final Item CHERRY = food("cherry", ModFoods.CHERRY);
    public static final Item BANANA = food("banana", ModFoods.BANANA);
    public static final Item VEGETABLE_SEEDS = register("vegetable_seeds", cropBlock(ModBlocks.VEGETABLE), new Item.Properties());
    public static final Item PEPPER_SEEDS = register("pepper_seeds", cropBlock(ModBlocks.CHILI), new Item.Properties());
    public static final Item TOMATO_SEEDS = register("tomato_seeds", cropBlock(ModBlocks.TOMATO), new Item.Properties());
    public static final Item GRAPE_SEEDS = register("grape_seeds", cropBlock(ModBlocks.GRAPES), new Item.Properties());
    public static final Item EGGPLANT_SEEDS = register("eggplant_seeds", cropBlock(ModBlocks.EGGPLANT), new Item.Properties());
    public static final Item GREEN_PEPPER_SEEDS = register("green_pepper_seeds", cropBlock(ModBlocks.GREEN_PEPPER), new Item.Properties());
    public static final Item CORN_SEEDS = register("corn_seeds", cropBlock(ModBlocks.CORN), new Item.Properties());
    public static final Item CUCUMBER_SEEDS = register("cucumber_seeds", cropBlock(ModBlocks.CUCUMBER), new Item.Properties());
    /**------------------------------  Drinks  ------------------------------*/
    public static final Item SOY_BEAN_MILK = drink("soy_bean_milk", ModFoods.SOYBEAN_MILK);
    public static final Item RED_WINE = drink("red_wine", ModFoods.RED_WINE);
    public static final Item SPIRIT = drink("spirit", ModFoods.SPIRIT);
    public static final Item GRAPE_WINE = drink("grape_wine", ModFoods.GRAPE_WINE);
    public static final Item CIDER = drink("cider", ModFoods.CIDER);
    public static final Item GOLDEN_GRAPE_WINE = drink("golden_grape_wine", ModFoods.GOLDEN_GRAPE_WINE);
    public static final Item GOLDEN_CIDER = drink("golden_cider", ModFoods.GOLDEN_CIDER);
    public static final Item PEACH_WINE = drink("peach_wine", ModFoods.PEACH_WINE);
    public static final Item LYCHEE_WINE = drink("lychee_wine", ModFoods.LYCHEE_WINE);
    public static final Item PEAR_WINE = drink("pear_wine", ModFoods.PEAR_WINE);
    public static final Item MANGO_WINE = drink("mango_wine", ModFoods.MANGO_WINE);
    public static final Item LEMON_WINE = drink("lemon_wine", ModFoods.LEMON_WINE);
    public static final Item POMEGRANATE_WINE = drink("pomegranate_wine", ModFoods.POMEGRANATE_WINE);
    public static final Item JUICE_BOTTLE = register("juice_bottle");
    // 原味冰淇淋
    public static final Item VANILLA_ICE_CREAM = food("vanilla_ice_cream", ModFoods.COCONUT_JUICE_ICE_CREAM);
    public static final Item CHOCOLATES_MILK_ICE_CREAM = food("chocolate_milk_ice_cream", ModFoods.CHOCOLATES_MILK_ICE_CREAM);
    public static final Item GRAPE_JUICE_ICE_CREAM = food("grape_juice_ice_cream", ModFoods.GRAPE_JUICE_ICE_CREAM);
    public static final Item APPLE_JUICE_ICE_CREAM = food("apple_juice_ice_cream", ModFoods.APPLE_JUICE_ICE_CREAM);
    public static final Item GOLDEN_GRAPE_JUICE_ICE_CREAM = food("golden_grape_juice_ice_cream", ModFoods.GOLDEN_GRAPE_JUICE_ICE_CREAM);
    public static final Item GOLDEN_APPLE_JUICE_ICE_CREAM = food("golden_apple_juice_ice_cream", ModFoods.GOLDEN_APPLE_JUICE_ICE_CREAM);
    public static final Item MELON_JUICE_ICE_CREAM = food("melon_juice_ice_cream", ModFoods.MELON_JUICE_ICE_CREAM);
    public static final Item LYCHEE_JUICE_ICE_CREAM = food("lychee_juice_ice_cream", ModFoods.LYCHEE_JUICE_ICE_CREAM);
    public static final Item PEACH_JUICE_ICE_CREAM = food("peach_juice_ice_cream", ModFoods.PEACH_JUICE_ICE_CREAM);
    public static final Item ORANGE_JUICE_ICE_CREAM = food("orange_juice_ice_cream", ModFoods.ORANGE_JUICE_ICE_CREAM);
    public static final Item MANGO_JUICE_ICE_CREAM = food("mango_juice_ice_cream", ModFoods.MANGO_JUICE_ICE_CREAM);
    public static final Item LEMON_JUICE_ICE_CREAM = food("lemon_juice_ice_cream", ModFoods.LEMON_JUICE_ICE_CREAM);
    public static final Item PAPAYA_JUICE_ICE_CREAM = food("papaya_juice_ice_cream", ModFoods.PAPAYA_JUICE_ICE_CREAM);
    public static final Item STRAWBERRY_JUICE_ICE_CREAM = food("strawberry_juice_ice_cream", ModFoods.STRAWBERRY_JUICE_ICE_CREAM);
    public static final Item COCONUT_JUICE_ICE_CREAM = food("coconut_juice_ice_cream", ModFoods.COCONUT_JUICE_ICE_CREAM);
    public static final Item BANANA_JUICE_ICE_CREAM = food("banana_juice_ice_cream", ModFoods.BANANA_JUICE_ICE_CREAM);
    /**------------------------------  Staple Food  ------------------------------*/
    public static final Item MUSHROOMS_CHICKEN_SOUP = food("mushrooms_chicken_soup", ModFoods.MUSHROOMS_CHICKEN_SOUP);
    public static final Item EGG_SOUP = food("egg_soup", ModFoods.EGG_SOUP);
    public static final Item NOODLES = food("noodles", ModFoods.NOODLES);
    public static final Item CROSSING_BRIDGE_NOODLES = food("crossing_bridge_noodles", ModFoods.CROSSING_BRIDGE_NOODLES);
    public static final Item PRESERVED_EGG_PORRIDGE = food("preserved_egg_porridge", ModFoods.PRESERVED_EGG_PORRIDGE);
    public static final Item LA_BA_PORRIDGE = food("la_ba_porridge", ModFoods.LA_BA_PORRIDGE);
    public static final Item TOMATO_EGG_RICE = food("tomato_egg_rice", ModFoods.TOMATO_EGG_RICE);
    public static final Item POTATO_PEPPER_EGGPLANT = food("potato_pepper_eggplant", ModFoods.POTATO_PEPPER_EGGPLANT);
    public static final Item YUXIANG_SHREDDED_PORK = food("yuxiang_shredded_pork", ModFoods.YUXIANG_SHREDDED_PORK);
    public static final Item KUNG_PAO_CHICKEN = food("kung_pao_chicken", ModFoods.KUNG_PAO_CHICKEN);
    public static final Item COOKED_POTATO_WIRE_RICE = food("cooked_potato_wire_rice", ModFoods.COOKED_POTATO_WIRE_RICE);
    public static final Item STEAMED_FISH_HEAD = food("steamed_fish_head", ModFoods.STEAMED_FISH_HEAD);
    public static final Item PEPPERY_DOUFU_RICE = food("pepper_doufu_rice", ModFoods.PEPPERY_DOUFU_RICE);
    public static final Item RED_BRAISED_PORK_BELLY_RICE = food("red_braised_pork_belly_rice", ModFoods.RED_BRAISED_PORK_BELLY_RICE);
    public static final Item TWICE_COOKED_PORK_SLICES = food("twice_cooked_pork_slices", ModFoods.TWICE_COOKED_PORK_SLICES);
    public static final Item ORLEAN_CHICKEN_RICE = food("orley_chicken_rice", ModFoods.ORLEAN_CHICKEN_RICE);
    public static final Item SPICY_CHICKEN = food("spicy_chicken", ModFoods.SPICY_CHICKEN);
    public static final Item STEAMED_CHICKEN_CHILI_SAUCE = food("steamed_chicken_chili_sauce", ModFoods.STEAMED_CHICKEN_CHILI_SAUCE);
    public static final Item WHITE_SLICED_CHICKEN = food("white_sliced_chicken", ModFoods.WHITE_SLICED_CHICKEN);
    public static final Item CHICKEN_SCALLION_OIL = food("chicken_scallion_oil", ModFoods.CHICKEN_SCALLION_OIL);
    public static final Item BOILED_FISH_SICHUAN_PICKLES = food("boiled_fish_sichuan_pickles", ModFoods.BOILED_FISH_SICHUAN_PICKLES);
    public static final Item SPICY_FISH = food("spicy_fish", ModFoods.SPICY_FISH);
    public static final Item STEAMED_FISH = food("steamed_fish", ModFoods.STEAMED_FISH);
    public static final Item COLA_CHICKEN_RICE = food("cola_chicken_rice", ModFoods.COLA_CHICKEN_RICE);
    public static final Item CURRY_CHICKEN_RICE = food("curry_chicken_rice", ModFoods.CURRY_CHICKEN_RICE);
    public static final Item SICHUAN_BOILED_BEEF = food("sichuan_boiled_beef", ModFoods.SICHUAN_BOILED_BEEF);
    public static final Item PASTA = food("pasta", ModFoods.PASTA);
    public static final Item PASTA_PORK = food("pasta_pork", ModFoods.PASTA_PORK);
    public static final Item PASTA_BEEF = food("pasta_beef", ModFoods.PASTA_BEEF);
    public static final Item PASTA_CHICKEN = food("pasta_chicken", ModFoods.PASTA_CHICKEN);
    public static final Item JAPAN_DOUFU = food("japan_doufu", ModFoods.JAPAN_DOUFU);
    public static final Item STEAMED_VERMICELLI_ROLL = food("steamed_vermicelli_roll", ModFoods.STEAMED_VERMICELLI_ROLL);
    /**------------------------------  Ingredients    ------------------------------*/
    public static final Item WATER_ITEM = register("water_item");
    public static final Item MILLED_RICE = register("milled_rice");
    public static final Item FLOUR = register("flour");
    public static final Item SALT = register("salt");
    public static final Item DUMPLING_WRAPPERS = register("dumpling_wrappers");
    public static final Item WATERCRESS = register("watercress");
    public static final Item CHOCOLATE_DUST = register("chocolate_dust");
    public static final Item DUMPLING_MEAT = register("dumpling_meat");
    public static final Item PEANUT_OIL = register("peanut_oil");
    public static final Item COOKED_RICE = food("cooked_rice", ModFoods.COOKED_RICE);
    public static final Item DRUMSTICK = food("drumstick", ModFoods.DRUMSTICK);
    public static final Item CHICKEN_WING = food("chicken_wing", ModFoods.CHICKEN_WING);
    public static final Item BIG_CHICKEN = register("big_chicken");
    public static final Item MEDIUM_CHICKEN = register("medium_chicken");
    public static final Item SMALL_CHICKEN = register("small_chicken");
    public static final Item POTATO_CHIPS = register("potato_chips");
    public static final Item POTATO_WIRE = register("potato_wire");
    public static final Item SOY_SAUCE = register("soy_sauce");
    public static final Item VINEGAR = register("vinegar");
    public static final Item RED_BEAN_PASTE = register("red_bean_paste");
    public static final Item STARCHES = register("starches");
    public static final Item STICKY_RICE_FLOUR = register("sticky_rice_flour");
    public static final Item STICKY_RICE_DOUGH = register("sticky_rice_dough");
    public static final Item PEANUT_FILLING = register("peanut_filling");
    public static final Item TOFU_STRIP = register("tofu_strip");
    public static final Item CARROT_STRIP = register("carrot_strip");
    public static final Item WHITE_RADDISH_STRIP = register("white_raddish_strip");
    public static final Item NOODLE = register("noodle");
    public static final Item BLOCK_CURRY = food("block_curry", ModFoods.BLOCK_CURRY);
    public static final Item SMOKED_MATERIAL = register("smoothed_material");
    public static final Item CHARRED_FOOD = register("charred_food");
    /**------------------------------  Snacks   ------------------------------*/
    public static final Item SQUID_MEAT = food("squid_meat", ModFoods.SQUID_MEAT);
    public static final Item COOKED_SQUID_MEAT = food("cooked_squid_meat", ModFoods.COOKED_SQUID_MEAT);
    public static final Item SQUID_SLICES = food("squid_slices", ModFoods.SQUID_SLICES);
    public static final Item POACHED_EGG = food("poached_egg", ModFoods.POACHED_EGG);
    public static final Item PANCAKES = food("pancakes", ModFoods.PANCAKES);
    public static final Item DUMPLING = food("dumpling", ModFoods.DUMPLING);
    public static final Item FIRED_DUMPLING = food("fired_dumpling", ModFoods.FIRED_DUMPLING);
    public static final Item TOFU = food("tofu", ModFoods.TOFU);
    public static final Item DRIED_TOFU = food("dried_tofu", ModFoods.DRIED_TOFU);
    public static final Item FRIED_POTATO_CHIPS = food("fried_potato_chips", ModFoods.FIRED_POTATO_CHIPS);
    public static final Item PORRIDGE = food("porridge", ModFoods.PORRIDGE);
    public static final Item STICKY_RICE_DUMPLING = food("sticky_rice_dumpling", ModFoods.STICKY_RICE_DUMPLING);
    public static final Item MOONCAKE = food("mooncake", ModFoods.MOONCAKE);
    public static final Item TANGYUAN = food("tangyuan", ModFoods.TANGYUAN);
    public static final Item DOUGH_TWIST = food("dough_twist", ModFoods.DOUGH_TWIST);
    public static final Item SPRING_ROLLS = food("spring_rolls", ModFoods.SPRING_ROLLS);
    public static final Item CHIPS = food("chips", ModFoods.CHIPS);
    public static final Item SAUSAGE = food("sausage", ModFoods.SAUSAGE);
    public static final Item CHINESE_SAUSAGE = food("chinese_sausage", ModFoods.CHINESE_SAUSAGE);
    public static final Item CHINESE_BEAN = food("chinese_bean", ModFoods.CHINESE_BACON);
    public static final Item FRIED_SAUSAGE = food("fried_sausage", ModFoods.FRIED_SAUSAGE);
    public static final Item PIZZA = food("pizza", ModFoods.PIZZA);
    public static final Item HAMBURGER = food("hamburger", ModFoods.HAMBURGER);
    public static final Item BREAD_STICK = food("bread_stick", ModFoods.BREAD_STICK);
    public static final Item CHILI_TOFU_STRIP = food("chili_tofu_strip", ModFoods.CHILI_TOFU_STRIP);
    public static final Item FRIED_CHICKEN = food("fried_chicken", ModFoods.FRIED_CHICKEN);
    public static final Item ORIGINAL_RECIPE = food("original_recipe", ModFoods.ORIGINAL_RECIPE);
    public static final Item FRENCH_FRIES = food("french_fries", ModFoods.FRENCH_FRIES);
    public static final Item POPCORN_CHICKEN = food("popcorn_chicken", ModFoods.POPCORN_CHICKEN);
    public static final Item ORLEAN_WING = food("orlean_wing", ModFoods.ORLEANS_WING);
    public static final Item FRIED_TOFU = food("fried_tofu", ModFoods.FRIED_TOFU);
    public static final Item FRIED_SPRING_ROLLS = food("fried_spring_rolls", ModFoods.FRIED_SPRING_ROLLS);
    public static final Item FRIED_DOUGH_TWIST = food("fried_dough_twist", ModFoods.FRIED_DOUGH_TWIST);
    public static final Item FRIED_DRUM_STICK = food("fried_drum_stick", ModFoods.FRIED_DRUMSTICK);
    public static final Item COOKED_SWEET_POTATO = food("cooked_sweet_potato", ModFoods.COOKED_SWEET_POTATO);
    public static final Item COOKED_CORN = food("cooked_corn", ModFoods.COOKED_CORN);
    public static final Item POPCORN = food("popcorn", ModFoods.POPCORN);
    /**------------------------------  Snacks   ------------------------------*/
    public static final Item CHOCOLATE = food("chocolate", ModFoods.CHOCOLATE);
    public static final Item NEW_YEAR_CAKE = food("new_year_cake", ModFoods.NewYearCake);
    public static final Item WALNUT_SHORT_BREAD = food("walnut_short_bread", ModFoods.WALNUT_SHORTBREAD);
    public static final Item GREEN_RICE_BALLS = food("green_rice_balls", ModFoods.GREEN_RICE_BALLS);
    public static final Item STICKY_RICE_CAKE = food("sticky_rice_cake", ModFoods.STICKY_RICE_CAKE);
    public static final Item CHEESE = food("cheese", ModFoods.CHEESE);
    public static final Item STEAMED_BUNS = food("steamed_buns", ModFoods.STEAMED_BUNS);
    public static final Item SAUERKRAUT_CAKE = food("sauerkraut_cake", ModFoods.SAUERKRAUT_CAKE);
    public static final Item FRIED_NEW_YEAR_CAKE = food("fried_new_year_cake", ModFoods.FRIED_NEW_YEAR_CAKE);
    public static final Item FRIED_BREAD = food("fried_beard", ModFoods.FRIED_BREAD);
    public static final Item HOT_DOG = food("hot_dog", ModFoods.HOT_DOG);
    public static final Item SMILEY_COOKIE = food("smiley_cookie", ModFoods.SMILEY_COOKIE);
    public static final Item BISCUIT = food("biscuit", ModFoods.BISCUIT);
    // 其他
    public static final Item RAW_MUTTON = register("raw_mutton");
    public static final Item COOKED_MUTTON = register("cooked_mutton");
    public static final Item CHINESE_BOWL = register("chinese_bowl");
    public static final Item PIXIAN_WATERCRESS = register("pixian_watercress");
    public static final Item DARK_BLUE_STONE = register("dark_blue_stone", Item::new, new Item.Properties()
            .component(DataComponents.RARITY, Rarity.RARE));
    public static final Item DARK_GREEN_STONE = register("dark_green_stone", Item::new, new Item.Properties()
            .component(DataComponents.RARITY, Rarity.RARE));
    public static final Item ULTIMATE_ETERNAL_STONE = register("dark_eternal_stone", Item::new, new Item.Properties()
            .component(DataComponents.RARITY, Rarity.EPIC));
    public static final Item DARK_GREEN_MULTIFUNCTION_TOOLS = register("dark_green_multifunction_tools", DarkGreenMultifunctionTools::new, MultifunctionTool.applyToolProperties(new Item.Properties())
            .component(DataComponents.RARITY, Rarity.RARE));
    public static final Item DARK_BLUE_MULTIFUNCTION_TOOLS = register("dark_blue_multifunction_tools", DarkBlueMultifunctionTools::new, MultifunctionTool.applyToolProperties(new Item.Properties())
            .component(DataComponents.RARITY, Rarity.EPIC));
    // 复杂
    // 果汁
    public static final Item JUICE = register("juice", JuiceItem::new, new Item.Properties()
            .component(ModDataComponents.JUICE, new JuiceContents(Juices.WATER))
            .component(DataComponents.FOOD, ModFoods.JUICE)
            .component(DataComponents.CONSUMABLE, Consumables.DEFAULT_DRINK)
            .usingConvertsTo(JUICE_BOTTLE));
    // 果酱
    public static final Item COCONUT_JAM = food("coconut_jam", ModFoods.JAM);
    public static final Item GOLDEN_GRAPES_JAM = food("golden_grapes_jam", ModFoods.JAM);
    public static final Item GRAPES_JAM = food("grapes_jam", ModFoods.JAM);
    public static final Item LEMON_JAM = food("lemon_jam", ModFoods.JAM);
    public static final Item ORANGE_JAM = food("orange_jam", ModFoods.JAM);
    public static final Item PEACH_JAM = food("peach_jam", ModFoods.JAM);
    public static final Item PEAR_JAM = food("pear_jam", ModFoods.JAM);
    public static final Item STRAWBERRY_JAM = food("strawberry_jam", ModFoods.JAM);
    // 饼干
    public static final Item COCONUT_BISCUIT = food("coconut_biscuit", ModFoods.BISCUIT);
    public static final Item GOLDEN_GRAPES_BISCUIT = food("golden_grapes_biscuit", ModFoods.BISCUIT);
    public static final Item GRAPES_BISCUIT = food("grapes_biscuit", ModFoods.BISCUIT);
    public static final Item LEMON_BISCUIT = food("lemon_biscuit", ModFoods.BISCUIT);
    public static final Item ORANGE_BISCUIT = food("orange_biscuit", ModFoods.BISCUIT);
    public static final Item PEACH_BISCUIT = food("peach_biscuit", ModFoods.BISCUIT);
    public static final Item PEAR_BISCUIT = food("pear_biscuit", ModFoods.BISCUIT);
    public static final Item STRAWBERRY_BISCUIT = food("strawberry_biscuit", ModFoods.BISCUIT);
    // 手册
    public static final Item BLOCK_LOVERS = register("block_lovers", Item::new, hasBookTooltip());
    public static final Item LOVELY_PLANT = register("lovely_plant", Item::new, hasBookTooltip());
    public static final Item DELICIOUS_FOOD_I = register("delicious_food_one", Item::new, hasBookTooltip());
    public static final Item DELICIOUS_FOOD_II = register("delicious_food_two", Item::new, hasBookTooltip());
    public static final Item KFC_FOOD = register("kfc_food", Item::new, hasBookTooltip());
    public static final Item CHINA_TRADITIONAL_FOOD = register("china_traditional_food", Item::new, hasBookTooltip());
    public static final Item FRIED_FOOD = register("fried_food", Item::new, hasBookTooltip());
    public static final Item ALCOHOLIC_RICH = register("alcoholic_rich", Item::new, hasBookTooltip());
    public static final Item SWEET_DRINKS_I = register("sweet_drinks_one", Item::new, hasBookTooltip());
    public static final Item SWEET_DRINKS_II = register("sweet_drinks_two", Item::new, hasBookTooltip());
    public static final Item SALIVATING_STAPLE_FOOD_I = register("salivating_staple_food_one", Item::new, hasBookTooltip());
    public static final Item SALIVATING_STAPLE_FOOD_II = register("salivating_staple_food_two", Item::new, hasBookTooltip());
    public static final Item SALIVATING_STAPLE_FOOD_III = register("salivating_staple_food_three", Item::new, hasBookTooltip());
    public static final Item SYMBOL_RICH = register("symbol_rich", Item::new, hasBookTooltip());

    public static final Item PEANUT_OIL_BUCKET = register("peanut_oil_bucket", properties -> new BucketItem(ModFluids.COOKING_OIL_STILL, properties), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));

    private static Item drink(String name, FoodProperties food) {
        return register(name, new Item.Properties().food(food).craftRemainder(Items.GLASS_BOTTLE));
    }

    private static Item food(String name, FoodProperties food) {
        return register(name, new Item.Properties().food(food));
    }

    private static Item register(String name, Item.Properties properties) {
        return register(name, Item::new, properties);
    }

    private static Item register(String name) {
        return register(name, Item::new, new Item.Properties());
    }

    private static Function<Item.Properties, Item> cropBlock(Block block) {
        return properties -> new BlockItem(block, properties.useItemDescriptionPrefix());
    }

    private static Item register(String name, Function<Item.Properties, Item> factory, Item.Properties properties) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, FoodCraft.of(name));
        return Registry.register(BuiltInRegistries.ITEM, key, factory.apply(properties.setId(key)));
    }

    public static Item.Properties hasBookTooltip() {
        return new Item.Properties().component(DataComponents.LORE, new ItemLore(List.of(Component.translatable("tooltip.foodcraft.handbook.desc"))));
    }

    public static void init() {

    }
}

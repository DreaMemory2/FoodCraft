package com.crystal.foodcraft.item.juice;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.register.ModRegistries;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;

/**
 * @see net.minecraft.world.item.alchemy.Potions
 */
public class Juices {
    public static final Holder<Juice> WATER = register("water", new Juice("water", -13083194));
    public static final Holder<Juice> CARROT = register("carrot", new Juice("carrot", 16025391));
    public static final Holder<Juice> TEA =  register("tea", new Juice("tea", 10003077));
    public static final Holder<Juice> GRAPE = register("grape", new Juice("grape", 12327094));
    public static final Holder<Juice> APPLE = register("apple", new Juice("apple", 16392281));
    public static final Holder<Juice> VEGETABLE = register("vegetable", new Juice("vegetable", 6994969));
    public static final Holder<Juice> MELON = register("melon", new Juice("melon", 15222969));
    public static final Holder<Juice> GOLDEN_GRAPE = register("golden_grape", new Juice("golden_grape", 14735938));
    public static final Holder<Juice> GOLDEN_APPLE = register("golden_apple", new Juice("golden_apple", 15197312));
    public static final Holder<Juice> COKE = register("coke", new Juice("coke", 5316616));
    public static final Holder<Juice> SPRITE = register("sprite", new Juice("sprite", 15592941));
    public static final Holder<Juice> MILK_TEA = register("milk_tea", new Juice("milk_tea", 12752947));
    public static final Holder<Juice> COFFEE = register("coffee", new Juice("coffee", 5260845));
    public static final Holder<Juice> CHOCOLATES_MILK = register("chocolate_milk", new Juice("chocolate_milk", 9791556));
    public static final Holder<Juice> CHOCOLATES_WATER = register("chocolate_water", new Juice("chocolate_water", 11440764));
    public static final Holder<Juice> SOY_MILK = register("soy_milk", new Juice("soy_milk", 16448242));
    public static final Holder<Juice> WHITE_RADISH = register("white_radish", new Juice("white_radish", 16776179));
    public static final Holder<Juice> TOMATO = register("tomato", new Juice("tomato", 14757965));
    public static final Holder<Juice> CORN = register("corn", new Juice("corn", 15459450));
    public static final Holder<Juice> CUCUMBER = register("cucumber", new Juice("cucumber", 11649675));
    public static final Holder<Juice> PEAR = register("pear", new Juice("pear", 15063867));
    public static final Holder<Juice> LYCHEE = register("lychee", new Juice("lychee", 16182736));
    public static final Holder<Juice> PEACH = register("peach", new Juice("peach", 16174116));
    public static final Holder<Juice> ORANGE = register("orange", new Juice("orange", 16166436));
    public static final Holder<Juice> LOQUAT = register("loquat", new Juice("loquat", 16632683));
    public static final Holder<Juice> MANGO = register("mango", new Juice("mango", 16767366));
    public static final Holder<Juice> LEMON = register("lemon", new Juice("lemon", 16577427));
    public static final Holder<Juice> GRAPEFRUIT = register("grapefruit", new Juice("grapefruit", 15524738));
    public static final Holder<Juice> PERSIMMON = register("persimmon", new Juice("persimmon", 15436848));
    public static final Holder<Juice> PAPAYA = register("papaya", new Juice("papaya", 15829541));
    public static final Holder<Juice> HAWTHORN = register("hawthorn", new Juice("hawthorn", 15366926));
    public static final Holder<Juice> POMEGRANATE = register("pomegranate", new Juice("pomegranate", 16018480));
    public static final Holder<Juice> CHINESE_DATE = register("chinese_date", new Juice("chinese_date", 11893859));
    public static final Holder<Juice> STRAWBERRY = register("strawberry", new Juice("strawberry", 15671149));
    public static final Holder<Juice> COCONUT = register("coconut", new Juice("coconut", 16577750));
    public static final Holder<Juice> CHERRY = register("cherry", new Juice("cherry", 16608525));
    public static final Holder<Juice> BANANA = register("banana", new Juice("banana", 16247658));
    public static final Holder<Juice> COCONUT_MILK = register("coconut_milk", new Juice("coconut_milk", 16711156));

    private static Holder<Juice> register(String name, Juice juice) {
        return Registry.registerForHolder(ModRegistries.JUICE, FoodCraft.of(name), juice);
    }
}

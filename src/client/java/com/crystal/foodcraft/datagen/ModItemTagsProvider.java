package com.crystal.foodcraft.datagen;

import com.crystal.foodcraft.block.ModBlocks;
import com.crystal.foodcraft.item.ModItems;
import com.crystal.foodcraft.tag.CommonItemTags;
import com.crystal.foodcraft.tag.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {

    public ModItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    public void addTags(@NotNull HolderLookup.Provider registries) {
        this.valueLookupBuilder(ModItemTags.KNIVES)
                .add(ModItems.KITCHEN_KNIFE)
                .add(ModItems.GOLDEN_KITCHEN_KNIFE)
                .add(ModItems.DIAMOND_KITCHEN_KNIFE)
                .add(ModItems.EMERALD_KITCHEN_KNIFE);
        this.valueLookupBuilder(CommonItemTags.MUSHROOM)
                .add(Items.BROWN_MUSHROOM)
                .add(Items.RED_MUSHROOM);
        this.valueLookupBuilder(CommonItemTags.SALT)
                .add(ModItems.SALT);
        this.valueLookupBuilder(CommonItemTags.EGG)
                .add(Items.EGG);
        this.valueLookupBuilder(CommonItemTags.FISH)
                .add(Items.COD)
                .add(Items.SALMON)
                .add(Items.TROPICAL_FISH)
                .add(Items.PUFFERFISH);
        this.valueLookupBuilder(CommonItemTags.COOKED_FISH)
                .add(Items.COOKED_COD)
                .add(Items.COOKED_SALMON);
        this.valueLookupBuilder(ModItemTags.TANGYUAN_STUFFING)
                .add(ModItems.RED_BEAN_PASTE)
                .add(ModItems.PEANUT_FILLING);
        this.valueLookupBuilder(ModItemTags.EMERALD_TOOL_MATERIALS)
                .add(Items.EMERALD);
        this.valueLookupBuilder(CommonItemTags.BROWN)
                .add(Items.BROWN_DYE)
                .add(Items.COCOA_BEANS);
        this.valueLookupBuilder(ModItemTags.MACHINE_INGREDIENT)
                .add(Items.WHEAT)
                .add(ModItems.BEANS)
                .add(ModItems.PEANUT)
                .add(ModItems.RICE);
        this.valueLookupBuilder(ModItemTags.CIRCUIT_INGREDIENT)
                .add(Items.WHEAT)
                .add(ModItems.BEANS)
                .add(ModItems.PEANUT)
                .add(ModItems.RICE);
        this.valueLookupBuilder(ModItemTags.BLOCK_LOVERS)
                .add(ModBlocks.CHOCOLATE.asItem())
                .add(ModBlocks.WATERCRESS_BLOCK.asItem())
                .add(ModBlocks.CARROT_BLOCK.asItem())
                .add(ModBlocks.BEAN_BLOCK.asItem())
                .add(ModBlocks.PEANUT_BLOCK.asItem())
                .add(ModBlocks.STICKY_RICE_BLOCK.asItem())
                .add(ModBlocks.POTATO_BLOCK.asItem())
                .add(ModBlocks.SALT.asItem())
                .add(ModBlocks.SUGAR.asItem());
        this.valueLookupBuilder(ModItemTags.LOVELY_PLANT)
                .add(ModItems.RICE)
                .add(ModItems.PEANUT)
                .add(ModItems.BEANS)
                .add(ModItems.VEGETABLE)
                .add(ModItems.CHILI)
                .add(ModItems.TOMATO)
                .add(ModItems.EGGPLANT)
                .add(ModItems.GRAPES)
                .add(Items.SUGAR);
        this.valueLookupBuilder(ModItemTags.DELICIOUS_FOOD_I)
                .add(ModItems.POACHED_EGG)
                .add(ModItems.PANCAKES)
                .add(ModItems.DRIED_TOFU)
                .add(ModItems.FRIED_POTATO_CHIPS)
                .add(ModItems.SAUSAGE)
                .add(ModItems.WALNUT_SHORT_BREAD)
                .add(ModItems.GREEN_RICE_BALLS)
                .add(ModItems.NEW_YEAR_CAKE)
                .add(ModItems.SPRING_ROLLS);
        this.valueLookupBuilder(ModItemTags.DELICIOUS_FOOD_II)
                .add(ModItems.HOT_DOG)
                .add(ModItems.CHEESE)
                .add(ModItems.PASTA)
                .add(ModItems.STEAMED_BUNS)
                .add(ModItems.SAUERKRAUT_CAKE)
                .add(ModItems.CHILI_TOFU_STRIP)
                .add(ModItems.DOUGH_TWIST)
                .add(ModItems.CHINESE_SAUSAGE)
                .add(ModItems.CHINESE_BEAN);
        this.valueLookupBuilder(ModItemTags.KFC_FOOD)
                .add(ModItems.HAMBURGER)
                .add(ModItems.FRENCH_FRIES)
                .add(ModItems.CHICKEN_WING)
                .add(ModItems.POPCORN_CHICKEN)
                .add(ModItems.FRIED_CHICKEN)
                .add(ModItems.FRIED_CHICKEN)
                .add(ModItems.DOUGH_TWIST);
        this.valueLookupBuilder(ModItemTags.CHINA_TRADITIONAL_FOOD)
                .add(ModItems.DUMPLING)
                .add(ModItems.STICKY_RICE_DUMPLING)
                .add(ModItems.MOONCAKE)
                .add(ModItems.TANGYUAN)
                .add(ModItems.NEW_YEAR_CAKE)
                .add(ModItems.SPRING_ROLLS)
                .add(ModItems.STICKY_RICE_CAKE)
                .add(ModItems.WALNUT_SHORT_BREAD)
                .add(ModItems.RICE);
        this.valueLookupBuilder(ModItemTags.FRIED_FOOD)
                .add(ModItems.FRIED_POTATO_CHIPS)
                .add(ModItems.NEW_YEAR_CAKE)
                .add(ModItems.SAUSAGE)
                .add(ModItems.FRIED_BREAD)
                .add(ModItems.FRIED_DOUGH_TWIST)
                .add(ModItems.FRIED_DRUM_STICK)
                .add(ModItems.POPCORN_CHICKEN)
                .add(ModItems.FRIED_CHICKEN)
                .add(ModItems.ORIGINAL_RECIPE);
        this.valueLookupBuilder(ModItemTags.ALCOHOLIC_RICH)
                .add(ModItems.RED_WINE)
                .add(ModItems.SPIRIT)
                .add(ModItems.GRAPE_WINE)
                .add(ModItems.GOLDEN_GRAPES)
                .add(Items.GOLDEN_APPLE)
                .add(ModItems.CIDER)
                .add(ModItems.GOLDEN_GRAPE_WINE)
                .add(ModItems.GOLDEN_CIDER);
        this.valueLookupBuilder(ModItemTags.SWEET_DRINKS_I)
                .add(Items.APPLE);
        this.valueLookupBuilder(ModItemTags.SWEET_DRINKS_II)
                .add(ModItems.APPLE_JUICE_ICE_CREAM)
                .add(ModItems.GRAPE_JUICE_ICE_CREAM)
                .add(ModItems.MELON_JUICE_ICE_CREAM)
                .add(ModItems.CHOCOLATES_MILK_ICE_CREAM);
        this.valueLookupBuilder(ModItemTags.SALIVATING_STAPLE_FOOD_I)
                .add(ModItems.TOMATO_EGG_RICE)
                .add(ModItems.POTATO_PEPPER_EGGPLANT)
                .add(ModItems.YUXIANG_SHREDDED_PORK)
                .add(ModItems.KUNG_PAO_CHICKEN)
                .add(ModItems.COOKED_POTATO_WIRE_RICE)
                .add(ModItems.STEAMED_FISH_HEAD)
                .add(ModItems.PEPPERY_DOUFU_RICE)
                .add(ModItems.RED_BRAISED_PORK_BELLY_RICE)
                .add(ModItems.TWICE_COOKED_PORK_SLICES);
        this.valueLookupBuilder(ModItemTags.SALIVATING_STAPLE_FOOD_II)
                .add(ModItems.ORLEAN_CHICKEN_RICE)
                .add(ModItems.SPICY_CHICKEN)
                .add(ModItems.STEAMED_CHICKEN_CHILI_SAUCE)
                .add(ModItems.WHITE_SLICED_CHICKEN)
                .add(ModItems.CHICKEN_SCALLION_OIL)
                .add(ModItems.BOILED_FISH_SICHUAN_PICKLES)
                .add(ModItems.SPICY_FISH)
                .add(ModItems.STEAMED_FISH)
                .add(ModItems.COLA_CHICKEN_RICE);
        this.valueLookupBuilder(ModItemTags.SALIVATING_STAPLE_FOOD_III)
                .add(ModItems.CURRY_CHICKEN_RICE)
                .add(ModItems.SICHUAN_BOILED_BEEF)
                .add(ModItems.PASTA)
                .add(ModItems.JAPAN_DOUFU)
                .add(ModItems.VEGETABLE)
                .add(ModItems.PASTA_PORK)
                .add(ModItems.STEAMED_VERMICELLI_ROLL)
                .add(ModItems.PASTA_CHICKEN)
                .add(ModItems.PASTA_BEEF);
        this.valueLookupBuilder(ModItemTags.SYMBOL_RICH)
                .add(ModItems.GOLDEN_CIDER)
                .add(Items.GOLDEN_APPLE)
                .add(ModItems.GOLDEN_GRAPES)
                .add(ModItems.GOLDEN_APPLE_JUICE_ICE_CREAM)
                .add(ModItems.GOLDEN_GRAPE_JUICE_ICE_CREAM);
    }
}

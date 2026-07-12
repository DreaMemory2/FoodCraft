package com.crystal.foodcraft.datagen.tags;

import com.crystal.foodcraft.item.juice.Juice;
import com.crystal.foodcraft.item.juice.Juices;
import com.crystal.foodcraft.register.ModRegistryKeys;
import com.crystal.foodcraft.tag.ModItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.HolderTagProvider;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class JuiceTagsProvider extends HolderTagProvider<Juice> {

    public JuiceTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, ModRegistryKeys.JUICE, lookupProvider);
    }

    @Override
    protected void addTags(@NotNull HolderLookup.Provider registries) {
        this.tag(ModItemTags.JUICE).addAll(List.of(
                Juices.WATER,
                Juices.CARROT,
                Juices.TEA,
                Juices.GRAPE,
                Juices.APPLE,
                Juices.VEGETABLE,
                Juices.MELON,
                Juices.GOLDEN_GRAPE,
                Juices.GOLDEN_APPLE,
                Juices.COKE,
                Juices.SPRITE,
                Juices.MILK_TEA,
                Juices.COFFEE,
                Juices.CHOCOLATES_MILK,
                Juices.CHOCOLATES_WATER,
                Juices.SOY_MILK,
                Juices.WHITE_RADISH,
                Juices.TOMATO,
                Juices.CORN,
                Juices.CUCUMBER,
                Juices.PEAR,
                Juices.LYCHEE,
                Juices.PEACH,
                Juices.ORANGE,
                Juices.LOQUAT,
                Juices.MANGO,
                Juices.LEMON,
                Juices.GRAPEFRUIT,
                Juices.PERSIMMON,
                Juices.PAPAYA,
                Juices.HAWTHORN,
                Juices.POMEGRANATE,
                Juices.CHINESE_DATE,
                Juices.STRAWBERRY,
                Juices.COCONUT,
                Juices.CHERRY,
                Juices.BANANA,
                Juices.COCONUT_MILK
        ));
    }
}

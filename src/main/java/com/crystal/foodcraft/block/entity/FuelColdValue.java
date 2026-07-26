package com.crystal.foodcraft.block.entity;

import it.unimi.dsi.fastutil.objects.Object2IntLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntSortedMap;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

/**
 * @see net.minecraft.world.level.block.entity.FuelValues FuelValues
 */
public class FuelColdValue {
    private final Object2IntSortedMap<Item> values;

    private FuelColdValue(Object2IntSortedMap<Item> values) {
        this.values = values;
    }

    public boolean isFuel(ItemStack itemStack) {
        return this.values.containsKey(itemStack.getItem());
    }

    public int getColdTime(ItemStack item) {
        return values.getInt(item.getItem());
    }

    public static FuelColdValue coldTime() {
        Object2IntSortedMap<Item> values = new Object2IntLinkedOpenHashMap<>();
        values.put(Items.SNOW, 200);
        values.put(Items.SNOW_BLOCK, 200 * 8);
        values.put(Items.ICE, 200 * 8 * 9);
        values.put(Items.PACKED_ICE, 200 * 8 * 9 * 8);
        values.put(Items.BLUE_ICE, 200 * 8 * 9 * 8 * 8);
        return new FuelColdValue(values);
    }
}

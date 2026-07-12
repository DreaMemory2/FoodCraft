package com.crystal.foodcraft.recipe;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.recipe.crafting.WithAnyJuice;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import org.jetbrains.annotations.NotNull;

public class ModSlotDisplayTypes {
    public static final SlotDisplay.Type<@NotNull WithAnyJuice> WITH_ANY_JUICE_TYPE = Registry.register(
            BuiltInRegistries.SLOT_DISPLAY,
            FoodCraft.of("with_any_juice"),
            WithAnyJuice.TYPE
    );

    public static void init() {

    }
}

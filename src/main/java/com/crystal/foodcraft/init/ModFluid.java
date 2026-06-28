package com.crystal.foodcraft.init;

import com.crystal.foodcraft.FoodCraft;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.WaterFluid;

public class ModFluid {
    public static final FlowingFluid COOKING_OIL_FLOWING = register("flowing_cooking_oil", new WaterFluid.Flowing());
    public static final FlowingFluid COOKING_OIL_STILL = register("cooking_oil", new WaterFluid.Source());

    private static FlowingFluid register(String name, FlowingFluid fluid) {
        return Registry.register(BuiltInRegistries.FLUID, FoodCraft.of(name), fluid);
    }

    public static void init() {

    }
}

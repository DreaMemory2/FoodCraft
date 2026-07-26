package com.crystal.foodcraft.register;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.fluid.CookingOilFluid;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.WaterFluid;

public class ModFluids {
    public static final FlowingFluid COOKING_OIL_FLOWING = register("flowing_cooking_oil", new CookingOilFluid.Flowing());
    public static final FlowingFluid COOKING_OIL_STILL = register("cooking_oil", new CookingOilFluid.Source());
    public static final FlowingFluid MILK_FLOWING = register("milk_flowing", new WaterFluid.Flowing());
    public static final FlowingFluid MILK_STILL = register("milk", new WaterFluid.Source());

    private static FlowingFluid register(String name, FlowingFluid fluid) {
        return Registry.register(BuiltInRegistries.FLUID, FoodCraft.of(name), fluid);
    }

    public static void init() {

    }
}

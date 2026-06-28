package com.crystal.foodcraft.world.placement;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModPlacementFeatures {

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        ModTreePlacements.bootstrap(context);
    }
}

package com.crystal.foodcraft;

import com.crystal.foodcraft.datagen.*;
import com.crystal.foodcraft.datagen.tags.JuiceTagsProvider;
import com.crystal.foodcraft.world.features.ModConfiguredFeatures;
import com.crystal.foodcraft.world.placement.ModPlacementFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class FoodCraftDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		FabricDataGenerator.Pack pack = generator.createPack();
		pack.addProvider(ModModelDataGeneration::new);
		pack.addProvider(ChineseDataGeneration::new);
		pack.addProvider(EnglishDataGeneration::new);
		pack.addProvider(ModWorldgenGeneration::new);
		pack.addProvider(ModItemTagsProvider::new);
		pack.addProvider(ModBlockTagsProvider::new);
		pack.addProvider(ModRecipeGeneration::new);

		pack.addProvider(JuiceTagsProvider::new);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder builder) {
		builder.add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
		builder.add(Registries.PLACED_FEATURE, ModPlacementFeatures::bootstrap);
	}
}

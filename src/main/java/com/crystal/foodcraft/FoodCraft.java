package com.crystal.foodcraft;

import com.crystal.foodcraft.block.ModBlocks;
import com.crystal.foodcraft.block.entity.BrewBarrelBlockEntity;
import com.crystal.foodcraft.block.entity.FryingMachineBlockEntity;
import com.crystal.foodcraft.block.entity.ModBlockEntities;
import com.crystal.foodcraft.block.entity.PressureCookerBlockEntity;
import com.crystal.foodcraft.item.ModItemGroup;
import com.crystal.foodcraft.item.ModItems;
import com.crystal.foodcraft.recipe.ModRecipeTypes;
import com.crystal.foodcraft.register.ModDataComponents;
import com.crystal.foodcraft.register.ModFluids;
import com.crystal.foodcraft.world.features.ModFeature;
import com.crystal.foodcraft.world.features.placer.FruitPlacerType;
import com.crystal.foodcraft.world.features.placer.IFoliagePlacerType;
import com.crystal.foodcraft.world.features.placer.ITrunkPlacerType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.recipe.v1.sync.RecipeSynchronization;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FoodCraft implements ModInitializer {
	public static final String MOD_ID = "foodcraft";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModDataComponents.init();
		ModItems.init();
		ModBlocks.init();
		ModBlockEntities.init();
		ModItemGroup.init();
		ModFluids.init();
		IFoliagePlacerType.init();
		ITrunkPlacerType.init();
		FruitPlacerType.init();
		ModFeature.init();

		// Synchronize recipe serializers for JEI/RRV/other recipe viewers
		RecipeSynchronization.synchronizeRecipeSerializer(ModRecipeTypes.CHOPPING_RECIPE_SERIALIZER);
		RecipeSynchronization.synchronizeRecipeSerializer(ModRecipeTypes.MILLING_RECIPE_SERIALIZER);
		RecipeSynchronization.synchronizeRecipeSerializer(ModRecipeTypes.POT_RECIPE_SERIALIZER);
		RecipeSynchronization.synchronizeRecipeSerializer(ModRecipeTypes.PAN_RECIPE_SERIALIZER);
		RecipeSynchronization.synchronizeRecipeSerializer(ModRecipeTypes.FRYING_RECIPE_SERIALIZER);
		RecipeSynchronization.synchronizeRecipeSerializer(ModRecipeTypes.BREWING_RECIPE_SERIALIZER);
		RecipeSynchronization.synchronizeRecipeSerializer(ModRecipeTypes.PRESSURE_COOKING_SERIALIZER);

		ItemStorage.SIDED.registerForBlockEntity(FryingMachineBlockEntity::getInventoryProvider, ModBlockEntities.FRYING_MACHINE);
		ItemStorage.SIDED.registerForBlockEntity(BrewBarrelBlockEntity::getInventoryProvider, ModBlockEntities.BREW_BARREL);
		ItemStorage.SIDED.registerForBlockEntity(PressureCookerBlockEntity::getInventoryProvider, ModBlockEntities.PRESSURE_COOKER);
		FluidStorage.SIDED.registerForBlockEntity(FryingMachineBlockEntity::getFluidStorage, ModBlockEntities.FRYING_MACHINE);
		FluidStorage.SIDED.registerForBlockEntity(BrewBarrelBlockEntity::getFluidStorage, ModBlockEntities.BREW_BARREL);
		FluidStorage.SIDED.registerForBlockEntity(PressureCookerBlockEntity::getFluidStorage, ModBlockEntities.PRESSURE_COOKER);
	}

	public static Identifier of(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
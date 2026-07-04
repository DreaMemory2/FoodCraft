package com.crystal.foodcraft;

import com.crystal.foodcraft.block.ModBlocks;
import com.crystal.foodcraft.block.entity.FryingMachineBlockEntity;
import com.crystal.foodcraft.block.entity.ModBlockEntities;
import com.crystal.foodcraft.register.ModDataComponents;
import com.crystal.foodcraft.register.ModFluids;
import com.crystal.foodcraft.item.ModItemGroup;
import com.crystal.foodcraft.item.ModItems;
import com.crystal.foodcraft.recipe.ModRecipeTypes;
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

		RecipeSynchronization.synchronizeRecipeSerializer(ModRecipeTypes.CHOPPING_RECIPE_SERIALIZER);

		ItemStorage.SIDED.registerForBlockEntity(FryingMachineBlockEntity::getInventoryProvider, ModBlockEntities.FRYING_MACHINE);
		FluidStorage.SIDED.registerForBlockEntity(FryingMachineBlockEntity::getFluidStorage, ModBlockEntities.FRYING_MACHINE);
	}

	public static Identifier of(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
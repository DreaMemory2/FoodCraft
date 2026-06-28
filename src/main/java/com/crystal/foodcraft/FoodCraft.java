package com.crystal.foodcraft;

import com.crystal.foodcraft.block.ModBlocks;
import com.crystal.foodcraft.block.entity.ModBlockEntities;
import com.crystal.foodcraft.init.ModFluid;
import com.crystal.foodcraft.item.ModItemGroup;
import com.crystal.foodcraft.item.ModItems;
import com.crystal.foodcraft.recipe.ModRecipeTypes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.recipe.v1.sync.RecipeSynchronization;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.minecraft.resources.Identifier;

public class FoodCraft implements ModInitializer {
	public static final String MOD_ID = "foodcraft";

	@Override
	public void onInitialize() {
		ModItems.init();
		ModBlocks.init();
		ModBlockEntities.init();
		ModItemGroup.init();
		ModFluid.init();

		RecipeSynchronization.synchronizeRecipeSerializer(ModRecipeTypes.CHOPPING_RECIPE_SERIALIZER);
		FluidStorage.SIDED.registerForBlockEntity((blockEntity, _) -> blockEntity.fluidTank, ModBlockEntities.FRYING_MACHINE);
	}

	public static Identifier of(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
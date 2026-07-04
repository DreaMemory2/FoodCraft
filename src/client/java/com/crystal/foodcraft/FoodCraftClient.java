package com.crystal.foodcraft;

import com.crystal.foodcraft.block.ModBlocks;
import com.crystal.foodcraft.register.ModFluids;
import com.crystal.foodcraft.item.juice.JuiceColor;
import com.crystal.foodcraft.rendering.api.ChunkSectionLayerMap;
import com.crystal.foodcraft.screen.*;
import com.crystal.foodcraft.screenhandler.ModMenuTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderingRegistry;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.client.color.item.ItemTintSources;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;

public class FoodCraftClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ChunkSectionLayerMap.putBlock(ModBlocks.POT, ChunkSectionLayer.CUTOUT);
		// 树苗图层渲染设置
		ChunkSectionLayerMap.putBlocks(ChunkSectionLayer.CUTOUT,
				ModBlocks.PEAR_SAPLING,
				ModBlocks.LYCHEE_SAPLING,
				ModBlocks.PEACH_SAPLING,
				ModBlocks.ORANGE_SAPLING,
				ModBlocks.LOQUAT_SAPLING,
				ModBlocks.MANGO_SAPLING,
				ModBlocks.LEMON_SAPLING,
				ModBlocks.GRAPEFRUIT_SAPLING,
				ModBlocks.PERSIMMON_SAPLING,
				ModBlocks.PAPAYA_SAPLING,
				ModBlocks.HAWTHORN_SAPLING,
				ModBlocks.LONGYAN_SAPLING,
				ModBlocks.POMEGRANATE_SAPLING,
				ModBlocks.CHINESE_DATE_SAPLING,
				ModBlocks.CHERRY_SAPLING,
				ModBlocks.BANANA_SAPLING,
				ModBlocks.COCONUT_SAPLING
		);

		ItemTintSources.ID_MAPPER.put(FoodCraft.of("juice"), JuiceColor.MAP_CODEC);

		// 机器页面
		MenuScreens.register(ModMenuTypes.CHOPPING_BOARD, ChoppingBoardScreen::new);
		MenuScreens.register(ModMenuTypes.POT, PotScreen::new);
		MenuScreens.register(ModMenuTypes.PRESSURE_COOKER, PressureCookerScreen::new);
		MenuScreens.register(ModMenuTypes.BREW_BARREL, BrewBarrelScreen::new);
		MenuScreens.register(ModMenuTypes.PAN, PanScreen::new);
		MenuScreens.register(ModMenuTypes.BEVERAGE_MAKING_MACHINE, BeverageMakingMachineScreen::new);
		MenuScreens.register(ModMenuTypes.MILL, MillScreen::new);
		MenuScreens.register(ModMenuTypes.FRYING_MACHINE, FryingMachineScreen::new);
		MenuScreens.register(ModMenuTypes.STOVE, StoveScreen::new);
		// 液体渲染
		FluidRenderingRegistry.register(
				ModFluids.COOKING_OIL_STILL,
				ModFluids.COOKING_OIL_FLOWING,
				new FluidModel.Unbaked(
						new Material(FoodCraft.of("block/cooking_oil_still")),
						new Material(FoodCraft.of("block/cooking_oil_flow")),
						new Material(Identifier.withDefaultNamespace("block/water_overlay")),
						BlockTintSources.constant(-1)
				)
		);
	}
}
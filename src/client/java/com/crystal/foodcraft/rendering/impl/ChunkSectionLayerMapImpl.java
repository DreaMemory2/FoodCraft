package com.crystal.foodcraft.rendering.impl;

import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;

public final class ChunkSectionLayerMapImpl {
    private static final Map<Block, ChunkSectionLayer> BLOCK_RENDER_LAYER_MAP = new HashMap<>();
    private static final Map<Fluid, ChunkSectionLayer> FLUID_RENDER_LAYER_MAP = new HashMap<>();

    // These consumers initially add to the maps above, and then are later set (when setup is called) to insert straight into the target map.
    private static BiConsumer<Block, ChunkSectionLayer> blockHandler = BLOCK_RENDER_LAYER_MAP::put;
    private static BiConsumer<Fluid, ChunkSectionLayer> fluidHandler = FLUID_RENDER_LAYER_MAP::put;

    public static void putBlock(Block block, ChunkSectionLayer layer) {
        Objects.requireNonNull(block, "block must not be null");
        Objects.requireNonNull(layer, "render layer must not be null");

        blockHandler.accept(block, layer);
    }

    public static void putFluid(Fluid fluid, ChunkSectionLayer layer) {
        Objects.requireNonNull(fluid, "fluid must not be null");
        Objects.requireNonNull(layer, "render layer must not be null");

        fluidHandler.accept(fluid, layer);
    }

    public static void setup(BiConsumer<Block, ChunkSectionLayer> vanillaBlockHandler, BiConsumer<Fluid, ChunkSectionLayer> vanillaFluidHandler) {
        // Add all the preexisting render layers
        BLOCK_RENDER_LAYER_MAP.forEach(vanillaBlockHandler);
        FLUID_RENDER_LAYER_MAP.forEach(vanillaFluidHandler);

        // Set the handlers to directly accept later additions
        blockHandler = vanillaBlockHandler;
        fluidHandler = vanillaFluidHandler;
    }

    private ChunkSectionLayerMapImpl() {
    }

}

package com.crystal.foodcraft.rendering.api;

import com.crystal.foodcraft.rendering.impl.ChunkSectionLayerMapImpl;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

/**
 * Use to associate blocks or fluids with a chunk section layer other than default (solid).
 *
 * <p>{@link ChunkSectionLayer} control how sprite pixels for fluids and blocks are blended with the scene. Consult the
 * vanilla {@link ChunkSectionLayer} implementation for examples.
 *
 * <p>The Fabric Renderer API can be used to control this at a per-quad level.
 */
public final class ChunkSectionLayerMap {
    /**
     * Map (or re-map) a block to a section layer. Re-mapping is not recommended but if done, last one in wins. Must be
     * called from client thread prior to level load/rendering. Best practice will be to call from mod's client
     * initializer.
     *
     * @param block the block to be mapped
     * @param layer the section layer
     */
    public static void putBlock(Block block, ChunkSectionLayer layer) {
        ChunkSectionLayerMapImpl.putBlock(block, layer);
    }

    /**
     * Map (or re-map) multiple blocks to a section layer. Re-mapping is not recommended but if done, last one in wins.
     * Must be called from client thread prior to level load/rendering. Best practice will be to call from mod's client
     * initializer.
     *
     * @param layer the section layer
     * @param blocks the blocks to be mapped
     */
    public static void putBlocks(ChunkSectionLayer layer, Block... blocks) {
        for (Block block : blocks) {
            putBlock(block, layer);
        }
    }

    /**
     * Map (or re-map) a fluid to a section layer. Re-mapping is not recommended but if done, last one in wins. Must be
     * called from client thread prior to level load/rendering. Best practice will be to call from mod's client
     * initializer.
     *
     * @param fluid the fluid to be mapped
     * @param layer the section layer
     */
    public static void putFluid(Fluid fluid, ChunkSectionLayer layer) {
        ChunkSectionLayerMapImpl.putFluid(fluid, layer);
    }

    /**
     * Map (or re-map) multiple fluids to a section layer. Re-mapping is not recommended but if done, last one in wins.
     * Must be called from client thread prior to level load/rendering. Best practice will be to call from mod's client
     * initializer.
     *
     * @param layer the section layer
     * @param fluids the fluids to be mapped
     */
    public static void putFluids(ChunkSectionLayer layer, Fluid... fluids) {
        for (Fluid fluid : fluids) {
            putFluid(fluid, layer);
        }
    }

    private ChunkSectionLayerMap() {
    }

}

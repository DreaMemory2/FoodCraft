package com.crystal.foodcraft.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.level.material.Fluid;

public final class RenderHelper {

    /**
     * Resolves the still texture sprite of the given fluid using the data-driven fluid models introduced in
     * NeoForge 26.1. Client-side only. This replaces the removed Architectury {@code FluidStackHooks#getStillTexture}.
     */
    public static TextureAtlasSprite getFluidSprite(Fluid fluid) {
        return Minecraft.getInstance().getModelManager()
                .getFluidStateModelSet()
                .get(fluid.defaultFluidState())
                .stillMaterial()
                .sprite();
    }
}

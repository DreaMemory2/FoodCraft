package com.crystal.foodcraft.model;

import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;

public class FCTexturedModel {
    public static final TexturedModel.Provider ORIENTABLE_TOP_AND_DOWN = TexturedModel.createDefault(FCTextureMapping::orientableCubeTopAndDown, ModelTemplates.CUBE_ORIENTABLE_TOP_BOTTOM);
    public static final TexturedModel.Provider CAKE = TexturedModel.createDefault(FCTextureMapping::cake, FCModelTemplates.CAKE);
    public static final TexturedModel.Provider CAKE_SLICE1 = TexturedModel.createDefault(FCTextureMapping::cakeSlice, FCModelTemplates.CAKE_SLICE1);
    public static final TexturedModel.Provider CAKE_SLICE2 = TexturedModel.createDefault(FCTextureMapping::cakeSlice, FCModelTemplates.CAKE_SLICE2);
    public static final TexturedModel.Provider CAKE_SLICE3 = TexturedModel.createDefault(FCTextureMapping::cakeSlice, FCModelTemplates.CAKE_SLICE3);
    public static final TexturedModel.Provider CAKE_SLICE4 = TexturedModel.createDefault(FCTextureMapping::cakeSlice, FCModelTemplates.CAKE_SLICE4);
    public static final TexturedModel.Provider CAKE_SLICE5 = TexturedModel.createDefault(FCTextureMapping::cakeSlice, FCModelTemplates.CAKE_SLICE5);
    public static final TexturedModel.Provider CAKE_SLICE6 = TexturedModel.createDefault(FCTextureMapping::cakeSlice, FCModelTemplates.CAKE_SLICE6);
}

package com.crystal.foodcraft.model;

import com.crystal.foodcraft.FoodCraft;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.model.Model;
import net.minecraft.resources.Identifier;

import java.util.Optional;

public class FCModelTemplates {
    public static final ModelTemplate CAKE = create("cake",
            TextureSlot.PARTICLE,
            TextureSlot.TOP,
            TextureSlot.BOTTOM,
            TextureSlot.SIDE
    );
    public static final ModelTemplate CAKE_SLICE1 = createCakeSlices("cake_slice1");
    public static final ModelTemplate CAKE_SLICE2 = createCakeSlices("cake_slice2");
    public static final ModelTemplate CAKE_SLICE3 = createCakeSlices("cake_slice3");
    public static final ModelTemplate CAKE_SLICE4 = createCakeSlices("cake_slice4");
    public static final ModelTemplate CAKE_SLICE5 = createCakeSlices("cake_slice5");
    public static final ModelTemplate CAKE_SLICE6 = createCakeSlices("cake_slice6");

    public static ModelTemplate createCakeSlices(String name) {
        return create(name,
                TextureSlot.PARTICLE,
                TextureSlot.TOP,
                TextureSlot.BOTTOM,
                TextureSlot.SIDE,
                TextureSlot.INSIDE
        );
    }

    private static ModelTemplate create(String id, final TextureSlot... slots) {
        return new ModelTemplate(Optional.of(FoodCraft.of("block/" + id)), Optional.empty(), slots);
    }
}

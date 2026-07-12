package com.crystal.foodcraft.tag;

import com.crystal.foodcraft.FoodCraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModBlockTags {
    public static final TagKey<Block> LEAVES = bind("leaves");
    public static final TagKey<Block> INGREDIENT_BLOCK = bind("ingredient_block");
    public static final TagKey<Block> INCORRECT_FOR_EMERALD_TOOL = bind("incorrect_for_emerald_tool");

    private static TagKey<Block> bind(String name) {
        return TagKey.create(Registries.BLOCK, FoodCraft.of(name));
    }
}

package com.crystal.foodcraft.item;

import com.crystal.foodcraft.tag.ModBlockTags;
import com.crystal.foodcraft.tag.ModItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.block.Block;

/**
 * @see ToolMaterial
 */
public record IToolMaterial(TagKey<Block> incorrectBlocksForDrops, int durability, float speed, float attackDamageBonus, int enchantmentValue, TagKey<Item> repairItems) {
    public static final ToolMaterial EMERALD = new ToolMaterial(ModBlockTags.INCORRECT_FOR_EMERALD_TOOL, 2031, 10.0F, 6.0F, 12, ModItemTags.EMERALD_TOOL_MATERIALS);
}

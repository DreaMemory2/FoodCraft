package com.crystal.foodcraft.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

public class KitchenKnifeItem extends Item {

    public KitchenKnifeItem(Properties properties, ToolMaterial material, float attackDamage, float attackSpeed) {
        super(properties.sword(material, attackDamage, attackSpeed));
    }
}

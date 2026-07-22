package com.crystal.foodcraft.item;

import com.crystal.foodcraft.tag.ModBlockTags;
import com.crystal.foodcraft.tag.ModItemTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.component.Weapon;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

public abstract class MultifunctionTool extends Item {

    public MultifunctionTool(Properties properties) {
        super(properties);
    }

    private static Item.Properties applyCommonProperties(Item.Properties properties) {
        return properties
                .durability(2031)
                .repairable(ModItemTags.EMERALD_TOOL_MATERIALS)
                .enchantable(12);
    }

    public static Item.Properties applyToolProperties(Item.Properties properties) {
        HolderGetter<Block> lookup = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK);
        return applyCommonProperties(properties)
                .component(DataComponents.TOOL,
                        new Tool(List.of(
                                Tool.Rule.deniesDrops(lookup.getOrThrow(ModBlockTags.INCORRECT_FOR_EMERALD_TOOL)),
                                Tool.Rule.minesAndDrops(lookup.getOrThrow(BlockTags.MINEABLE_WITH_PICKAXE), 10.0F),
                                Tool.Rule.minesAndDrops(lookup.getOrThrow(BlockTags.MINEABLE_WITH_AXE), 10.0F),
                                Tool.Rule.minesAndDrops(lookup.getOrThrow(BlockTags.MINEABLE_WITH_HOE), 10.0F),
                                Tool.Rule.minesAndDrops(lookup.getOrThrow(BlockTags.MINEABLE_WITH_SHOVEL), 10.0F),
                                Tool.Rule.minesAndDrops(HolderSet.direct(Blocks.COBWEB.builtInRegistryHolder()), 15.0F),
                                Tool.Rule.overrideSpeed(lookup.getOrThrow(BlockTags.SWORD_INSTANTLY_MINES), Float.MAX_VALUE),
                                Tool.Rule.overrideSpeed(lookup.getOrThrow(BlockTags.SWORD_EFFICIENT), 1.5F)
                        ), 1.0F, 1, true))
                .attributes(createToolAttributes())
                .component(DataComponents.WEAPON, new Weapon(1));
    }

    private static ItemAttributeModifiers createToolAttributes() {
        return ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, 12.0F, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_SPEED,
                        new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, -2.4F, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND)
                .build();
    }

    /**
     * <p>去皮，脱蜡，除锈</p>
     * @see net.minecraft.world.item.AxeItem
     */
    public InteractionResult stripping() {
        return InteractionResult.PASS;
    }

    /**
     * <p>耕地，去根</p>
     * @see net.minecraft.world.item.HoeItem HoeItem
     */
    public InteractionResult hoeing() {
        return InteractionResult.PASS;
    }

    /**
     * @see net.minecraft.world.item.ShovelItem ShovelItem
     */
    public InteractionResult weeding()  {
        return InteractionResult.PASS;
    }
}

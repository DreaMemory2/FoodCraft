package com.crystal.foodcraft.datagen;

import com.crystal.foodcraft.block.ModBlocks;
import com.crystal.foodcraft.tag.ForeignModTags;
import com.crystal.foodcraft.tag.ModBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {

    public ModBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(@NotNull HolderLookup.Provider registries) {
        this.valueLookupBuilder(ForeignModTags.HEAT_SOURCES)
                .add(ModBlocks.STOVE);
        this.valueLookupBuilder(ModBlockTags.INGREDIENT_BLOCK)
                .add(ModBlocks.SUGAR)
                .add(ModBlocks.MILLED_RICE)
                .add(ModBlocks.PEANUT_BLOCK)
                .add(ModBlocks.CARROT_BLOCK)
                .add(ModBlocks.POTATO_BLOCK)
                .add(ModBlocks.BEAN_BLOCK)
                .add(ModBlocks.STICKY_RICE_BLOCK)
                .add(ModBlocks.CHOCOLATE);
        this.valueLookupBuilder(ModBlockTags.INCORRECT_FOR_EMERALD_TOOL);
        this.valueLookupBuilder(ModBlockTags.LEAVES)
                .add(ModBlocks.LEAVES)
                .add(ModBlocks.PEAR_LEAVES)
                .add(ModBlocks.LYCHEE_LEAVES)
                .add(ModBlocks.PEACH_LEAVES)
                .add(ModBlocks.ORANGE_LEAVES)
                .add(ModBlocks.LOQUAT_LEAVES)
                .add(ModBlocks.MANGO_LEAVES)
                .add(ModBlocks.LEMON_LEAVES)
                .add(ModBlocks.GRAPEFRUIT_LEAVES)
                .add(ModBlocks.PERSIMMON_LEAVES)
                .add(ModBlocks.PAPAYA_LEAVES)
                .add(ModBlocks.HAWTHORN_LEAVES)
                .add(ModBlocks.LONGYAN_LEAVES)
                .add(ModBlocks.POMEGRANATE_LEAVES)
                .add(ModBlocks.CHINESE_DATE_LEAVES)
                .add(ModBlocks.CHERRY_LEAVES);
    }
}

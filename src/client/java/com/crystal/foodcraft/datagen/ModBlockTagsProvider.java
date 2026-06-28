package com.crystal.foodcraft.datagen;

import com.crystal.foodcraft.block.ModBlocks;
import com.crystal.foodcraft.tag.ForeignModTags;
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
    }
}

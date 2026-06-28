package com.crystal.foodcraft.datagen;

import com.crystal.foodcraft.item.ModItems;
import com.crystal.foodcraft.tag.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {

    public ModItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    public void addTags(@NotNull HolderLookup.Provider registries) {
        this.valueLookupBuilder(ModItemTags.KNIVES)
                .add(ModItems.KITCHEN_KNIFE)
                .add(ModItems.GOLDEN_KITCHEN_KNIFE)
                .add(ModItems.DIAMOND_KITCHEN_KNIFE)
                .add(ModItems.EMERALD_KITCHEN_KNIFE);
    }
}

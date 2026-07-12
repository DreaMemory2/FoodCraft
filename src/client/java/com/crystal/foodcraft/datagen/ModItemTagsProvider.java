package com.crystal.foodcraft.datagen;

import com.crystal.foodcraft.item.ModItems;
import com.crystal.foodcraft.tag.CommonItemTags;
import com.crystal.foodcraft.tag.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;
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
        this.valueLookupBuilder(CommonItemTags.MUSHROOM)
                .add(Items.BROWN_MUSHROOM)
                .add(Items.RED_MUSHROOM);
        this.valueLookupBuilder(CommonItemTags.SALT)
                .add(ModItems.SALT);
        this.valueLookupBuilder(CommonItemTags.EGG)
                .add(Items.EGG);
        this.valueLookupBuilder(CommonItemTags.FISH)
                .add(Items.COD)
                .add(Items.SALMON)
                .add(Items.TROPICAL_FISH)
                .add(Items.PUFFERFISH);
        this.valueLookupBuilder(CommonItemTags.COOKED_FISH)
                .add(Items.COOKED_COD)
                .add(Items.COOKED_SALMON);
        this.valueLookupBuilder(ModItemTags.TANGYUAN_STUFFING)
                .add(ModItems.RED_BEAN_PASTE)
                .add(ModItems.PEANUT_FILLING);
        this.valueLookupBuilder(ModItemTags.EMERALD_TOOL_MATERIALS)
                .add(Items.EMERALD);
        this.valueLookupBuilder(CommonItemTags.BROWN)
                .add(Items.BROWN_DYE)
                .add(Items.COCOA_BEANS);
        this.valueLookupBuilder(ModItemTags.MACHINE_INGREDIENT)
                .add(Items.WHEAT)
                .add(ModItems.BEANS)
                .add(ModItems.PEANUT)
                .add(ModItems.RICE);
        this.valueLookupBuilder(ModItemTags.CIRCUIT_INGREDIENT)
                .add(Items.WHEAT)
                .add(ModItems.BEANS)
                .add(ModItems.PEANUT)
                .add(ModItems.RICE);
    }
}

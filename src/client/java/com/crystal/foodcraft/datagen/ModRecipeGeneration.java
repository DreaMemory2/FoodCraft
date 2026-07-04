package com.crystal.foodcraft.datagen;

import com.crystal.foodcraft.datagen.provider.ModRecipeProvider;
import com.crystal.foodcraft.item.ModItems;
import com.crystal.foodcraft.register.ModFluids;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeGeneration extends FabricRecipeProvider {

    public ModRecipeGeneration(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @NotNull
    @Override
    protected RecipeProvider createRecipeProvider(@NotNull HolderLookup.Provider registryLookup, @NotNull RecipeOutput output) {
        return new ModRecipeProvider(registryLookup, output) {
            @Override
            public void buildRecipes() {
                this.chopping(ModItems.POTATO_CHIPS, 3)
                        .ingredient(Items.POTATO, 1)
                        .unlockedBy("has_potato", has(Items.POTATO))
                        .save(output, "chopping/potato_chips");
                this.milling(ModItems.FLOUR)
                        .ingredient(Items.WHEAT)
                        .unlockedBy("has_wheat", has(Items.WHEAT))
                        .save(output, "milling/flour");
                this.potting(ModItems.SICHUAN_BOILED_BEEF, 350, 450)
                        .staple(ModItems.CHILI)
                        .staple(ModItems.VEGETABLE)
                        .staple(Items.COOKED_BEEF)
                        .ingredient(ModItems.PEANUT_OIL)
                        .ingredient(ModItems.SALT)
                        .ingredient(ModItems.STARCHES)
                        .unlockedBy("has_cooked_beef", has(Items.COOKED_BEEF))
                        .save(output, "potting/boiled_beef");
                this.panning(ModItems.FRIED_DOUGH_TWIST, 300, 380)
                        .ingredient(ModItems.DOUGH_TWIST)
                        .unlockedBy("has_dough_shred", has(ModItems.DOUGH_TWIST))
                        .save(output, "panning/dough_shred");
                this.frying(ModItems.FRENCH_FRIES)
                        .fluidState(ModFluids.COOKING_OIL_STILL)
                        .ingredient(ModItems.POTATO_WIRE)
                        .unlockedBy("has_potato_wire", has(ModItems.POTATO_WIRE))
                        .save(output, "frying/potato_wire");
            }
        };
    }

    @NotNull
    @Override
    public String getName() {
        return "Foodcraft Recipes";
    }
}

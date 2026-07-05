package com.crystal.foodcraft.datagen;

import com.crystal.foodcraft.datagen.provider.ModRecipeProvider;
import com.crystal.foodcraft.item.ModItems;
import com.crystal.foodcraft.register.ModFluids;
import com.crystal.foodcraft.tag.CommonItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeGeneration extends FabricRecipeProvider {

    public ModRecipeGeneration(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @NotNull
    @Override
    protected RecipeProvider createRecipeProvider(@NotNull HolderLookup.Provider registryLookup, @NotNull RecipeOutput output) {
        return new Provider(registryLookup, output);
    }

    private static class Provider extends ModRecipeProvider {

        protected Provider(HolderLookup.Provider registryLookup, RecipeOutput output) {
            super(registryLookup, output);
        }

        @Override
        public void buildRecipes() {
            chopping();
            millRecipe();
            panning();
            potting();
            frying();
            brewing();
            pressureCooking();
            crafting();
        }

        public void chopping() {
            // 饺子馅
            // 土豆片
            this.chopping(ModItems.POTATO_CHIPS, 3)
                    .ingredient(Items.POTATO, 1)
                    .unlockedBy("has_potato", has(Items.POTATO))
                    .save(output, "chopping/potato_chips");
        }

        /**
         * <p>碾磨机所有配方</p>
         */
        public void millRecipe() {
            // 巧克力粉
            this.milling(ModItems.CHOCOLATE_DUST)
                    .ingredient(Items.COCOA_BEANS)
                    .unlockedBy("has_cocoa", has(Items.COCOA_BEANS))
                    .save(output, "milling/chocolate_dust");
            // 面粉
            this.milling(ModItems.FLOUR)
                    .ingredient(Items.WHEAT)
                    .unlockedBy("has_wheat", has(Items.WHEAT))
                    .save(output, "milling/flour");
            // 米粉
            this.milling(ModItems.MILLED_RICE)
                    .ingredient(ModItems.RICE)
                    .unlockedBy("has_rice", has(ModItems.RICE))
                    .save(output, "milling/milled_rice");
            // 花生油
            this.milling(ModItems.PEANUT_OIL)
                    .ingredient(ModItems.PEANUT)
                    .unlockedBy("has_peanut", has(ModItems.PEANUT))
                    .save(output, "milling/peanut_oil");
            // 红豆沙
            this.milling(ModItems.RED_BEAN_PASTE)
                    .ingredient(ModItems.RED_BEAN)
                    .unlockedBy("has_red_bean", has(ModItems.RED_BEAN))
                    .save(output, "milling/red_bean_paste");
            // 豆浆
            this.milling(ModItems.SOY_BEAN_MILK)
                    .ingredient(ModItems.BEANS)
                    .unlockedBy("beans", has(ModItems.BEANS))
                    .save(output, "milling/soy_bean_milk");
            // 淀粉
            this.milling(ModItems.STARCHES)
                    .ingredient(Items.POTATO)
                    .unlockedBy("has_potato", has(Items.POTATO))
                    .save(output, "milling/starches");
            // 檽米粉
            this.milling(ModItems.STICKY_RICE_FLOUR)
                    .ingredient(ModItems.STICKY_RICE)
                    .unlockedBy("has_sticky_rice", has(ModItems.STICKY_RICE))
                    .save(output, "milling/sticky_rice_flour");
        }

        /**
         * <p>烹饪锅所有配方</p>
         */
        public void potting() {
            // 水煮肉片
            this.potting(ModItems.SICHUAN_BOILED_BEEF, 350, 450)
                    .staple(ModItems.CHILI)
                    .staple(ModItems.VEGETABLE)
                    .staple(Items.COOKED_BEEF)
                    .ingredient(ModItems.PEANUT_OIL)
                    .ingredient(ModItems.SALT)
                    .ingredient(ModItems.STARCHES)
                    .unlockedBy("has_cooked_beef", has(Items.COOKED_BEEF))
                    .save(output, "potting/boiled_beef");
        }

        /**
         * <p>平板锅所有配方</p>
         */
        public void panning() {
            // 烙饼
            this.panning(ModItems.PANCAKES, 250, 350)
                    .ingredient(ModItems.FLOUR)
                    .unlockedBy("has_flour", has(ModItems.FLOUR))
                    .save(output, "panning/pancakes");
            // 荷包蛋
            this.panning(ModItems.POACHED_EGG, 250, 400)
                    .ingredient(Items.EGG)
                    .unlockedBy("has_egg", has(Items.EGG))
                    .save(output, "panning/poached_egg");
            // 煎饺
            this.panning(ModItems.FIRED_DUMPLING, 250, 350)
                    .ingredient(ModItems.DUMPLING)
                    .unlockedBy("has_dumpling", has(ModItems.DUMPLING))
                    .save(output, "panning/fired_dumpling");
            // 炸麻花
            this.panning(ModItems.FRIED_DOUGH_TWIST, 300, 380)
                    .ingredient(ModItems.DOUGH_TWIST)
                    .unlockedBy("has_dough_shred", has(ModItems.DOUGH_TWIST))
                    .save(output, "panning/fried_dough_shred");
            // 炸薯片
            this.panning(ModItems.FRIED_POTATO_CHIPS, 225, 325)
                    .ingredient(ModItems.POTATO_CHIPS)
                    .unlockedBy("has_potato_chips", has(ModItems.POTATO_CHIPS))
                    .save(output, "panning/fried_potato_chips");
            // 爆米花
            this.panning(ModItems.POPCORN, 250, 330)
                    .ingredient(ModItems.CORN)
                    .unlockedBy("has_corn", has(ModItems.CORN))
                    .save(output, "panning/popcorn");
        }

        /**
         * <p>油炸机所有配方</p>
         */
        public void frying() {
            // 薯条
            this.frying(ModItems.FRENCH_FRIES)
                    .fluidState(ModFluids.COOKING_OIL_STILL)
                    .ingredient(ModItems.POTATO_WIRE)
                    .unlockedBy("has_potato_wire", has(ModItems.POTATO_WIRE))
                    .save(output, "frying/potato_wire");
        }

        /**
         * <p>酿桶所有配方</p>
         */
        public void brewing() {
            // 葡萄酒
            this.brewing(ModItems.GRAPE_WINE, 3)
                    .fluidState(Fluids.WATER)
                    .ingredient(ModItems.GRAPES)
                    .ingredient(ModItems.GRAPES)
                    .ingredient(ModItems.GRAPES)
                    .unlockedBy("has_grapes", has(ModItems.GRAPES))
                    .save(output, "brewing/grape_wine");
            // 苹果酒
            this.brewing(ModItems.CIDER, 3)
                    .fluidState(Fluids.WATER)
                    .ingredient(Items.APPLE)
                    .ingredient(Items.APPLE)
                    .ingredient(Items.APPLE)
                    .unlockedBy("has_apple", has(Items.APPLE))
                    .save(output, "brewing/apple_wine");
            // 金苹果酒
            this.brewing(ModItems.GOLDEN_CIDER, 3)
                    .fluidState(Fluids.WATER)
                    .ingredient(Items.GOLDEN_APPLE)
                    .ingredient(Items.GOLDEN_APPLE)
                    .ingredient(Items.GOLDEN_APPLE)
                    .unlockedBy("has_golden_apple", has(Items.GOLDEN_APPLE))
                    .save(output, "brewing/golden_apple_wine");
            // 金金葡萄酒
            this.brewing(ModItems.GOLDEN_GRAPE_WINE, 3)
                    .fluidState(Fluids.WATER)
                    .ingredient(ModItems.GOLDEN_GRAPES)
                    .ingredient(ModItems.GOLDEN_GRAPES)
                    .ingredient(ModItems.GOLDEN_GRAPES)
                    .unlockedBy("has_golden_grapes", has(ModItems.GOLDEN_GRAPES))
                    .save(output, "brewing/golden_grape_wine");
            // 柠檬酒
            this.brewing(ModItems.LEMON_WINE, 3)
                    .fluidState(Fluids.WATER)
                    .ingredient(ModItems.LEMON)
                    .ingredient(ModItems.LEMON)
                    .ingredient(ModItems.LEMON)
                    .unlockedBy("has_lemon", has(ModItems.LEMON))
                    .save(output, "brewing/lemon_wine");
            // 荔枝酒
            this.brewing(ModItems.LYCHEE_WINE, 3)
                    .fluidState(Fluids.WATER)
                    .ingredient(ModItems.LYCHEE)
                    .ingredient(ModItems.LYCHEE)
                    .ingredient(ModItems.LYCHEE)
                    .unlockedBy("has_lychee", has(ModItems.LYCHEE))
                    .save(output, "brewing/lychee_wine");
            // 芒果酒
            this.brewing(ModItems.MANGO_WINE, 3)
                    .fluidState(Fluids.WATER)
                    .ingredient(ModItems.MANGO)
                    .ingredient(ModItems.MANGO)
                    .ingredient(ModItems.MANGO)
                    .unlockedBy("has_mango", has(ModItems.MANGO))
                    .save(output, "brewing/mango_wine");
            // 桃子酒
            this.brewing(ModItems.PEACH_WINE, 3)
                    .fluidState(Fluids.WATER)
                    .ingredient(ModItems.PEACH)
                    .ingredient(ModItems.PEACH)
                    .ingredient(ModItems.PEACH)
                    .unlockedBy("has_peach", has(ModItems.PEACH))
                    .save(output, "brewing/peach_wine");
            // 石榴酒
            this.brewing(ModItems.POMEGRANATE_WINE, 3)
                    .fluidState(Fluids.WATER)
                    .ingredient(ModItems.POMEGRANATE)
                    .ingredient(ModItems.POMEGRANATE)
                    .ingredient(ModItems.POMEGRANATE)
                    .unlockedBy("has_pomegranate", has(ModItems.POMEGRANATE))
                    .save(output, "brewing/pomegranate_wine");
            // 酱油
            this.brewing(ModItems.SOY_SAUCE, 16)
                    .fluidState(Fluids.WATER)
                    .ingredient(ModItems.BEANS)
                    .ingredient(ModItems.FLOUR)
                    .ingredient(CommonItemTags.SALT)
                    .unlockedBy("has_beans", has(ModItems.BEANS))
                    .save(output, "brewing/soy_sauce");
            // 白酒
            this.brewing(ModItems.SPIRIT, 3)
                    .fluidState(Fluids.WATER)
                    .ingredient(ModItems.RICE)
                    .ingredient(ModItems.RICE)
                    .ingredient(ModItems.RICE)
                    .unlockedBy("has_rice", has(ModItems.RICE))
                    .save(output, "brewing/rice");
            // 陈醋
            this.brewing(ModItems.VINEGAR, 16)
                    .fluidState(Fluids.WATER)
                    .ingredient(ModItems.BEANS)
                    .ingredient(ModItems.BEANS)
                    .ingredient(Items.SUGAR)
                    .unlockedBy("has_beans", has(ModItems.BEANS))
                    .save(output, "brewing/vinegar");
        }

        /**
         * <p>高压锅所有配方</p>
         */
        public void pressureCooking() {
            // 米饭
            this.pressureCooking(ModItems.COOKED_RICE)
                    .fluidState(Fluids.WATER)
                    .ingredient(ModItems.RICE)
                    .ingredient(ModItems.RICE)
                    .ingredient(ModItems.RICE)
                    .unlockedBy("has_rice", has(ModItems.RICE))
                    .save(output, "pressure_cooking/rice");
            // 鸡蛋羹
            this.pressureCooking(ModItems.EGG_SOUP)
                    .fluidState(Fluids.WATER)
                    .ingredient(Items.EGG)
                    .ingredient(ModItems.SOY_SAUCE)
                    .ingredient(ModItems.SCALLION)
                    .unlockedBy("has_egg", has(Items.EGG))
                    .save(output, "pressure_cooking/egg_soup");
            // 蘑菇炖鸡汤
            this.pressureCooking(ModItems.MUSHROOMS_CHICKEN_SOUP)
                    .fluidState(Fluids.WATER)
                    .ingredient(CommonItemTags.MUSHROOM)
                    .ingredient(CommonItemTags.SALT)
                    .ingredient(ModItems.DRUMSTICK)
                    .unlockedBy("has_mushroom", has(CommonItemTags.MUSHROOM))
                    .unlockedBy("has_drumstick", has(ModItems.DRUMSTICK))
                    .save(output, "pressure_cooking/mushroom_chicken_soup");
            // 皮蛋廋肉粥
            this.pressureCooking(ModItems.PRESERVED_EGG_PORRIDGE)
                    .fluidState(Fluids.WATER)
                    .ingredient(ModItems.COOKED_RICE)
                    .ingredient(CommonItemTags.EGG)
                    .ingredient(Items.COOKED_PORKCHOP)
                    .unlockedBy("has_egg", has(CommonItemTags.EGG))
                    .unlockedBy("has_cooked_porkchop", has(Items.COOKED_PORKCHOP))
                    .save(output, "pressure_cooking/preserved_egg_porridge");
            // 粥
            this.pressureCooking(ModItems.PORRIDGE, 3)
                    .fluidState(Fluids.WATER)
                    .ingredient(ModItems.COOKED_RICE)
                    .ingredient(ModItems.COOKED_RICE)
                    .ingredient(ModItems.COOKED_RICE)
                    .unlockedBy("has_cooked_rice", has(ModItems.COOKED_RICE))
                    .save(output, "pressure_cooking/porridge");
            // 清蒸鱼
            this.pressureCooking(ModItems.STEAMED_FISH)
                    .fluidState(Fluids.WATER)
                    .ingredient(CommonItemTags.COOKED_FISH)
                    .ingredient(ModItems.SCALLION)
                    .ingredient(ModItems.SCALLION)
                    .unlockedBy("has_cooked_fish", has(CommonItemTags.COOKED_FISH))
                    .save(output, "pressure_cooking/steamed_fish");
        }

        /**
         * <p>工作台配方</p>
         */
        public void crafting() {
            // 巧克力
            this.shapeless(RecipeCategory.FOOD, ModItems.CHOCOLATE)
                    .requires(ModItems.CHOCOLATE_DUST)
                    .requires(Items.SUGAR)
                    .unlockedBy("has_chocolate_dust", has(ModItems.CHOCOLATE_DUST))
                    .save(output, "chocolate");
            // 面皮
            // 饺子
        }
    }

    @NotNull
    @Override
    public String getName() {
        return "Foodcraft Recipes";
    }
}

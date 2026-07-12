package com.crystal.foodcraft.datagen;

import com.crystal.foodcraft.block.ModBlocks;
import com.crystal.foodcraft.datagen.provider.ModRecipeProvider;
import com.crystal.foodcraft.item.ModItems;
import com.crystal.foodcraft.item.juice.JuiceContents;
import com.crystal.foodcraft.item.juice.Juices;
import com.crystal.foodcraft.register.ModDataComponents;
import com.crystal.foodcraft.register.ModFluids;
import com.crystal.foodcraft.tag.CommonItemTags;
import com.crystal.foodcraft.tag.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

/**
 * @see net.minecraft.data.recipes.packs.VanillaRecipeProvider VanillaRecipeProvider
 */
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
            smelting();
        }

        public void chopping() {
            // 土豆片
            this.chopping(ModItems.POTATO_CHIPS, 3)
                    .ingredient(Items.POTATO, 1)
                    .unlockedBy("has_potato", has(Items.POTATO))
                    .save(output, "chopping/potato_chips");
            // 胡萝卜丝
            this.chopping(ModItems.CARROT_STRIP, 2)
                    .ingredient(Items.CARROT, 1)
                    .unlockedBy("has_carrot", has(Items.CARROT))
                    .save(output, "choping/carrot_strip");
            // 大块鸡肉
            this.chopping(ModItems.BIG_CHICKEN, 3)
                    .ingredient(Items.CHICKEN, 1)
                    .ingredient(Items.CHICKEN, 1)
                    .unlockedBy("has_chicken", has(Items.CHICKEN))
                    .save(output, "chopping/big_chicken");
            // 中块鸡肉
            this.chopping(ModItems.MEDIUM_CHICKEN, 2)
                    .ingredient(ModItems.BIG_CHICKEN, 1)
                    .unlockedBy("has_big_chicken", has(ModItems.BIG_CHICKEN))
                    .save(output, "chopping/medium_chicken");
            // 小块鸡肉
            this.chopping(ModItems.SMALL_CHICKEN, 2)
                    .ingredient(ModItems.MEDIUM_CHICKEN, 1)
                    .unlockedBy("has_medium_chicken", has(ModItems.MEDIUM_CHICKEN))
                    .save(output, "chopping/small_chicken");
            // 鸡翅膀
            this.chopping(ModItems.CHICKEN_WING, 2)
                    .ingredient(Items.COOKED_CHICKEN, 1)
                    .unlockedBy("has_cooked_chicken", has(Items.COOKED_CHICKEN))
                    .save(output, "chopping/chicken_wing");
            // 面条，面丝
            this.chopping(ModItems.NOODLE, 3)
                    .ingredient(ModItems.DUMPLING_WRAPPERS, 1)
                    .unlockedBy("has_dumpling_wrappers", has(ModItems.DUMPLING_WRAPPERS))
                    .save(output, "chopping/dough_shredded");
            // 饺子馅
            this.chopping(ModItems.DUMPLING_MEAT, 2)
                    .ingredient(ModItems.VEGETABLE, 1)
                    .ingredient(Items.COOKED_PORKCHOP, 1)
                    .unlockedBy("has_cooked_porkchop", has(Items.COOKED_PORKCHOP))
                    .save(output, "chopping/dumping_meat");
            // 土豆丝
            this.chopping(ModItems.POTATO_WIRE, 2)
                    .ingredient(ModItems.POTATO_CHIPS, 1)
                    .unlockedBy("has_potato_chips", has(ModItems.POTATO_CHIPS))
                    .save(output, "chopping/potato_wire");
            // 鱿鱼丝
            this.chopping(ModItems.SQUID_SLICES, 2)
                    .ingredient(ModItems.SQUID_MEAT, 1)
                    .unlockedBy("has_squid_meat", has(ModItems.SQUID_MEAT))
                    .save(output, "chopping/squid_slices");
            // 豆腐丝
            this.chopping(ModItems.TOFU_STRIP, 3)
                    .ingredient(ModItems.TOFU, 1)
                    .unlockedBy("has_tofu", has(ModItems.TOFU))
                    .save(output, "chopping/tofu_strip");
            // 白萝卜丝
            this.chopping(ModItems.WHITE_RADDISH_STRIP, 3)
                    .ingredient(ModItems.WHITE_RADDISH, 1)
                    .unlockedBy("has_white_raddish", has(ModItems.WHITE_RADDISH))
                    .save(output, "chopping/white_raddish_strip");
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
            // 酸菜鱼
            this.potting(ModItems.BOILED_FISH_SICHUAN_PICKLES, 450, 500)
                    .staple(ModItems.GREEN_PEPPER)
                    .staple(ModItems.SCALLION)
                    .staple(CommonItemTags.COOKED_FISH)
                    .staple(ModItems.VEGETABLE)
                    .ingredient(ModItems.PEANUT_OIL)
                    .ingredient(CommonItemTags.SALT)
                    .ingredient(ModItems.SOY_SAUCE)
                    .ingredient(ModItems.STARCHES)
                    .unlockedBy("has_cooked_fish", has(CommonItemTags.COOKED_FISH))
                    .unlockedBy("has_cabbage", has(ModItems.VEGETABLE))
                    .save(output, "potting/boiled_fish_sichuan_pickles");
            // 口水鸡
            this.potting(ModItems.STEAMED_CHICKEN_CHILI_SAUCE, 450, 500)
                    .staple(ModItems.GREEN_PEPPER)
                    .staple(ModItems.SCALLION)
                    .staple(Items.COOKED_CHICKEN)
                    .ingredient(ModItems.PEANUT_OIL)
                    .ingredient(CommonItemTags.SALT)
                    .ingredient(Items.SUGAR)
                    .ingredient(ModItems.PIXIAN_WATERCRESS)
                    .unlockedBy("has_cooked_chicken", has(Items.COOKED_CHICKEN))
                    .save(output, "potting/steamed_chicken_chili_sauce");
            // 可乐鸡翅
            this.potting(ModItems.COLA_CHICKEN_RICE, 450, 500)
                    .staple(new ItemStackTemplate(ModItems.JUICE, DataComponentPatch.builder()
                            .set(ModDataComponents.JUICE, new JuiceContents(Juices.COKE)).build()).item().value())
                    /*.staple(ModItems.JUICE ,ModDataComponents.JUICE, new JuiceContents(Juices.COKE))*/
                    .staple(ModItems.COOKED_RICE)
                    .staple(ModItems.CHICKEN_WING)
                    .ingredient(ModItems.PEANUT_OIL)
                    .ingredient(CommonItemTags.SALT)
                    .ingredient(ModItems.SOY_SAUCE)
                    .unlockedBy("has_chicken_wing", has(ModItems.CHICKEN_WING))
                    .unlockedBy("has_cooked_rice", has(ModItems.COOKED_RICE))
                    .save(output, "potting/cola_chicken_rice");
            // 咖喱鸡肉
            this.potting(ModItems.CURRY_CHICKEN_RICE, 400, 500)
                    .staple(ModItems.COOKED_RICE)
                    .staple(ModItems.BLOCK_CURRY)
                    .staple(Items.COOKED_CHICKEN)
                    .ingredient(ModItems.PEANUT_OIL)
                    .ingredient(CommonItemTags.SALT)
                    .ingredient(ModItems.STARCHES)
                    .unlockedBy("has_cooked_chicken", has(Items.COOKED_CHICKEN))
                    .unlockedBy("has_cooked_rice", has(ModItems.COOKED_RICE))
                    .save(output, "potting/curry_chicken_rice");
            this.potting(ModItems.POTATO_PEPPER_EGGPLANT, 450, 500)
                    .staple(ModItems.COOKED_RICE)
                    .staple(ModItems.GREEN_PEPPER)
                    .staple(Items.POTATO)
                    .staple(ModItems.EGGPLANT)
                    .ingredient(ModItems.PEANUT_OIL)
                    .ingredient(CommonItemTags.SALT)
                    .ingredient(Items.SUGAR)
                    .ingredient(ModItems.SOY_SAUCE)
                    .unlockedBy("has_green_pepper", has(ModItems.GREEN_PEPPER))
                    .unlockedBy("has_eggplant", has(ModItems.EGGPLANT))
                    .unlockedBy("has_potato", has(Items.POTATO))
                    .unlockedBy("has_cooked_rice", has(ModItems.COOKED_RICE))
                    .save(output, "potting/potato_pepper_eggplant");
            // 剁椒鱼头
            this.potting(ModItems.STEAMED_FISH_HEAD, 350, 450)
                    .staple(ModItems.COOKED_RICE)
                    .staple(ModItems.GREEN_PEPPER)
                    .staple(CommonItemTags.COOKED_FISH)
                    .ingredient(ModItems.PEANUT_OIL)
                    .ingredient(CommonItemTags.SALT)
                    .ingredient(ModItems.SOY_SAUCE)
                    .unlockedBy("has_cooked_rice", has(ModItems.COOKED_RICE))
                    .unlockedBy("has_cooked_fish", has(CommonItemTags.COOKED_FISH))
                    .unlockedBy("has_green_pepper", has(ModItems.GREEN_PEPPER))
                    .save(output, "potting/steamed_fish_head");
            // 炒土豆丝
            this.potting(ModItems.COOKED_POTATO_WIRE_RICE, 450, 500)
                    .staple(ModItems.COOKED_RICE)
                    .staple(ModItems.POTATO_WIRE)
                    .staple(ModItems.SCALLION)
                    .ingredient(ModItems.PEANUT_OIL)
                    .ingredient(CommonItemTags.SALT)
                    .ingredient(ModItems.SOY_SAUCE)
                    .unlockedBy("has_cooked_rice", has(ModItems.COOKED_RICE))
                    .unlockedBy("has_potato_wire", has(ModItems.POTATO_WIRE))
                    .save(output, "potting/steamed_potato_wire");
            // 日本豆腐
            this.potting(ModItems.JAPAN_DOUFU, 350, 450)
                    .staple(ModItems.COOKED_RICE)
                    .staple(ModItems.TOFU)
                    .staple(ModItems.SCALLION)
                    .staple(ModItems.GREEN_PEPPER)
                    .ingredient(ModItems.PEANUT_OIL)
                    .ingredient(CommonItemTags.SALT)
                    .ingredient(ModItems.SOY_SAUCE)
                    .unlockedBy("has_cooked_rice", has(ModItems.COOKED_RICE))
                    .unlockedBy("has_tofu", has(ModItems.TOFU))
                    .save(output, "potting/japan_doufu");
            // 宫保鸡丁
            this.potting(ModItems.KUNG_PAO_CHICKEN, 400, 500)
                    .staple(ModItems.COOKED_RICE)
                    .staple(ModItems.PEANUT)
                    .staple(ModItems.GREEN_PEPPER)
                    .staple(Items.COOKED_CHICKEN)
                    .ingredient(ModItems.PEANUT_OIL)
                    .ingredient(CommonItemTags.SALT)
                    .ingredient(Items.SUGAR)
                    .ingredient(ModItems.VINEGAR)
                    .unlockedBy("has_cooked_rice", has(ModItems.COOKED_RICE))
                    .unlockedBy("has_cooked_chicken", has(Items.COOKED_CHICKEN))
                    .save(output, "potting/kung_pao_chicken");
            // 麻婆豆腐
            this.potting(ModItems.PEPPERY_DOUFU_RICE, 350, 450)
                    .staple(ModItems.COOKED_RICE)
                    .staple(ModItems.TOFU)
                    .staple(ModItems.PIXIAN_WATERCRESS)
                    .ingredient(ModItems.PEANUT_OIL)
                    .ingredient(CommonItemTags.SALT)
                    .ingredient(ModItems.STARCHES)
                    .unlockedBy("has_cooked_rice", has(ModItems.COOKED_RICE))
                    .unlockedBy("has_tofu", has(ModItems.TOFU))
                    .save(output, "potting/peppery_doufu_rice");
            // 辣子鸡
            this.potting(ModItems.SPICY_CHICKEN, 350, 450)
                    .staple(Items.COOKED_CHICKEN)
                    .staple(ModItems.PEANUT)
                    .staple(ModItems.CHILI)
                    .ingredient(ModItems.PEANUT_OIL)
                    .ingredient(CommonItemTags.SALT)
                    .ingredient(Items.SUGAR)
                    .ingredient(ModItems.SOY_SAUCE)
                    .unlockedBy("has_cooked_chicken", has(Items.COOKED_CHICKEN))
                    .save(output, "potting/spicy_chicken");
            // 红烧肉
            this.potting(ModItems.RED_BRAISED_PORK_BELLY_RICE, 450, 500)
                    .staple(ModItems.COOKED_RICE)
                    .staple(Items.COOKED_PORKCHOP)
                    .ingredient(ModItems.PEANUT_OIL)
                    .ingredient(CommonItemTags.SALT)
                    .ingredient(Items.SUGAR)
                    .ingredient(ModItems.VINEGAR)
                    .ingredient(ModItems.SOY_SAUCE)
                    .unlockedBy("has_cooked_rice", has(ModItems.COOKED_RICE))
                    .unlockedBy("has_cooked_porkchop", has(Items.COOKED_PORKCHOP))
                    .save(output, "potting/red_braised_pork_belly_rice");
            // 葱油鸡
            this.potting(ModItems.CHICKEN_SCALLION_OIL, 400, 500)
                    .staple(ModItems.CHILI)
                    .staple(ModItems.SCALLION)
                    .staple(Items.COOKED_CHICKEN)
                    .ingredient(ModItems.PEANUT_OIL)
                    .ingredient(CommonItemTags.SALT)
                    .ingredient(ModItems.SOY_SAUCE)
                    .unlockedBy("has_scallion", has(ModItems.SCALLION))
                    .unlockedBy("has_cooked_chicken", has(Items.COOKED_CHICKEN))
                    .save(output, "potting/chicken_scallion_oil");
            // 鱼香肉丝
            this.potting(ModItems.YUXIANG_SHREDDED_PORK, 350, 450)
                    .staple(ModItems.COOKED_RICE)
                    .staple(Items.COOKED_PORKCHOP)
                    .staple(Items.CARROT)
                    .staple(ModItems.GREEN_PEPPER)
                    .ingredient(ModItems.PEANUT_OIL)
                    .ingredient(CommonItemTags.SALT)
                    .ingredient(Items.SUGAR)
                    .ingredient(ModItems.VINEGAR)
                    .unlockedBy("has_cooked_rice", has(ModItems.COOKED_RICE))
                    .unlockedBy("has_cooked_porkchop", has(Items.COOKED_PORKCHOP))
                    .save(output, "potting/yuxiang_shredded_pork");
            // 白切鸡
            this.potting(ModItems.WHITE_SLICED_CHICKEN, 450, 500)
                    .staple(Items.COOKED_CHICKEN)
                    .staple(ModItems.SCALLION)
                    .ingredient(Items.SUGAR)
                    .unlockedBy("has_cooked_chicken", has(Items.COOKED_CHICKEN))
                    .save(output, "potting/white_sliced_chicken");
            // 麻辣鱼肉
            this.potting(ModItems.SPICY_FISH, 350, 450)
                    .staple(ModItems.CHILI)
                    .staple(CommonItemTags.COOKED_FISH)
                    .ingredient(ModItems.PEANUT_OIL)
                    .ingredient(CommonItemTags.SALT)
                    .ingredient(ModItems.SOY_SAUCE)
                    .unlockedBy("has_cooked_fish", has(CommonItemTags.COOKED_FISH))
                    .unlockedBy("has_chili", has(ModItems.CHILI))
                    .save(output, "potting/spicy_fish");
            // 汤圆
            this.potting(ModItems.TANGYUAN, 400, 500)
                    .staple(ModItemTags.TANGYUAN_STUFFING)
                    .ingredient(ModItems.STICKY_RICE_FLOUR)
                    .unlockedBy("has_tangyuan_stuffing", has(ModItemTags.TANGYUAN_STUFFING))
                    .save(output, "potting/tangyuan_stuffing");
            // 西红柿炒鸡蛋
            this.potting(ModItems.TOMATO_EGG_RICE, 400, 500)
                    .staple(ModItems.COOKED_RICE)
                    .staple(ModItems.TOMATO)
                    .staple(CommonItemTags.EGG)
                    .ingredient(ModItems.PEANUT_OIL)
                    .ingredient(CommonItemTags.SALT)
                    .ingredient(Items.SUGAR)
                    .unlockedBy("has_tomato", has(ModItems.TOMATO))
                    .unlockedBy("has_egg", has(CommonItemTags.EGG))
                    .save(output, "potting/tomato_egg_rice");
            // 回锅肉
            this.potting(ModItems.TWICE_COOKED_PORK_SLICES, 350, 450)
                    .staple(ModItems.COOKED_RICE)
                    .staple(ModItems.CHILI)
                    .staple(Items.COOKED_PORKCHOP)
                    .ingredient(ModItems.PEANUT_OIL)
                    .ingredient(CommonItemTags.SALT)
                    .ingredient(ModItems.PIXIAN_WATERCRESS)
                    .unlockedBy("has_porkchop", has(Items.COOKED_PORKCHOP))
                    .unlockedBy("has_cooked_rice", has(ModItems.COOKED_RICE))
                    .save(output, "potting/twice_cooked_pork_slices");
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
            // 炸面包
            this.frying(ModItems.FRIED_BREAD)
                    .fluidState(ModFluids.COOKING_OIL_STILL)
                    .ingredient(Items.BREAD)
                    .unlockedBy("has_bread", has(Items.BREAD))
                    .save(output, "frying/bread");
            // 炸鸡肉
            this.frying(ModItems.FRIED_CHICKEN)
                    .fluidState(ModFluids.COOKING_OIL_STILL)
                    .ingredient(ModItems.MEDIUM_CHICKEN)
                    .unlockedBy("has_medium_chicken", has(ModItems.MEDIUM_CHICKEN))
                    .save(output, "frying/medium_chicken");
            // 炸鸡腿
            this.frying(ModItems.FRIED_DRUM_STICK)
                    .fluidState(ModFluids.COOKING_OIL_STILL)
                    .ingredient(ModItems.DRUMSTICK)
                    .unlockedBy("has_drumstick", has(ModItems.DRUMSTICK))
                    .save(output, "frying/fried_drumstick");
            // 炸春卷
            this.frying(ModItems.FRIED_SPRING_ROLLS)
                    .fluidState(ModFluids.COOKING_OIL_STILL)
                    .ingredient(ModItems.SPRING_ROLLS)
                    .unlockedBy("has_spring_rolls", has(ModItems.SPRING_ROLLS))
                    .save(output, "frying/fried_spring_rolls");
            // 炸麻花
            this.frying(ModItems.FRIED_DOUGH_TWIST)
                    .fluidState(ModFluids.COOKING_OIL_STILL)
                    .ingredient(ModItems.DOUGH_TWIST)
                    .unlockedBy("has_dough_twist", has(ModItems.DOUGH_TWIST))
                    .save(output, "frying/fried_dough_twist");
            // 炸年糕
            this.frying(ModItems.FRIED_NEW_YEAR_CAKE)
                    .fluidState(ModFluids.COOKING_OIL_STILL)
                    .ingredient(ModItems.NEW_YEAR_CAKE)
                    .unlockedBy("has_new_year_cake", has(ModItems.NEW_YEAR_CAKE))
                    .save(output, "frying/fried_new_year_cake");
            // 炸香肠
            this.frying(ModItems.FRIED_SAUSAGE)
                    .fluidState(ModFluids.COOKING_OIL_STILL)
                    .ingredient(ModItems.SAUSAGE)
                    .unlockedBy("has_sausage", has(ModItems.SAUSAGE))
                    .save(output, "frying/fried_sausage");
            // 炸豆腐
            this.frying(ModItems.FRIED_TOFU)
                    .fluidState(ModFluids.COOKING_OIL_STILL)
                    .ingredient(ModItems.TOFU)
                    .unlockedBy("has_tofu", has(ModItems.TOFU))
                    .save(output, "frying/fried_tofu");
            // 原味鸡块
            this.frying(ModItems.FRIED_CHICKEN)
                    .fluidState(ModFluids.COOKING_OIL_STILL)
                    .ingredient(ModItems.BIG_CHICKEN)
                    .unlockedBy("has_big_chicken", has(ModItems.BIG_CHICKEN))
                    .save(output, "frying/fried_chicken");
            // 奥尔良鸡翅
            this.frying(ModItems.ORLEAN_WING)
                    .fluidState(ModFluids.COOKING_OIL_STILL)
                    .ingredient(ModItems.CHICKEN_WING)
                    .unlockedBy("has_chicken_wing", has(ModItems.CHICKEN_WING))
                    .save(output, "frying/orlean_wing");
            // 鸡米花
            this.frying(ModItems.POPCORN_CHICKEN)
                    .fluidState(ModFluids.COOKING_OIL_STILL)
                    .ingredient(ModItems.SMALL_CHICKEN)
                    .unlockedBy("has_small_chicken", has(ModItems.SMALL_CHICKEN))
                    .save(output, "frying/small_chicken");
            // 油条
            this.frying(ModItems.BREAD_STICK)
                    .fluidState(ModFluids.COOKING_OIL_STILL)
                    .ingredient(ModItems.FLOUR)
                    .unlockedBy("has_flour", has(ModItems.FLOUR))
                    .save(output, "frying/flour");
            // 炸土豆片
            this.frying(ModItems.FRIED_POTATO_CHIPS)
                    .fluidState(ModFluids.COOKING_OIL_STILL)
                    .ingredient(ModItems.POTATO_CHIPS)
                    .unlockedBy("has_potato_chips", has(ModItems.POTATO_CHIPS))
                    .save(output, "frying/fried_potato_chips");
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
            // 艾滋
            this.shapeless(RecipeCategory.FOOD, ModItems.GREEN_RICE_BALLS)
                    .requires(ModItems.STICKY_RICE_CAKE)
                    .requires(ModItemTags.LEAVES)
                    .unlockedBy("has_leaves", has(ModItemTags.LEAVES))
                    .save(output, "green_rice_balls");
            // 苹果冰淇淋
            this.customCrafting(ModItems.APPLE_JUICE_ICE_CREAM, ModItems.JUICE, ModItems.VANILLA_ICE_CREAM)
                    .unlockedBy("has_juices", has(ModItems.JUICE))
                    .save(output, "apple_juice_ice_cream");
            // 腊肉
            this.shapeless(RecipeCategory.FOOD, ModItems.CHINESE_BEAN)
                    .requires(ModItems.SMOKED_MATERIAL)
                    .requires(Items.COOKED_PORKCHOP)
                    .unlockedBy("has_cooked_porkchop", has(Items.COOKED_PORKCHOP))
                    .save(output, "chinese_bean");
            // 香蕉冰淇淋
            // 香蕉树
            this.shaped(RecipeCategory.MISC, ModBlocks.BANANA_SAPLING)
                    .pattern(" B ")
                    .pattern("BSB")
                    .pattern(" B ")
                    .define('B', ModItems.BANANA)
                    .define('S', ItemTags.SAPLINGS)
                    .unlockedBy("has_sapling", has(ItemTags.SAPLINGS))
                    .save(output, "banana_sapling");
            // 牛肉意面
            this.shapeless(RecipeCategory.FOOD, ModItems.PASTA_BEEF)
                    .requires(Items.COOKED_BEEF)
                    .requires(ModItems.PASTA)
                    .unlockedBy("has_cooked_beef", has(Items.COOKED_BEEF))
                    .unlockedBy("has_pasta", has(ModItems.PASTA))
                    .save(output, "pasta_beef");
            // 饮料制作机
            this.shaped(RecipeCategory.MISC, ModBlocks.BEVERAGE_MAKING_MACHINE)
                    .pattern("ABA")
                    .pattern("DOD")
                    .pattern(" C ")
                    .define('A', Items.REDSTONE)
                    .define('B', Items.ICE)
                    .define('C', ModItems.MACHINE_CORE)
                    .define('D', CommonItemTags.BROWN)
                    .define('O', ModBlocks.RICE_MACHINE_SHELL)
                    .unlockedBy("has_ice", has(Items.ICE))
                    .unlockedBy("has_machine_core", has(ModItems.MACHINE_CORE))
                    .unlockedBy("has_rice_machine_shell", has(ModBlocks.RICE_MACHINE_SHELL))
                    .save(output, "beverage_making_machine");
            // 腊肠
            this.shapeless(RecipeCategory.FOOD, ModItems.CHINESE_SAUSAGE)
                    .requires(ModItems.SMOKED_MATERIAL)
                    .requires(ModItems.FRIED_SAUSAGE)
                    .unlockedBy("has_smoked_material", has(ModItems.SMOKED_MATERIAL))
                    .save(output, "chinese_sausage");
            // 酿桶
            this.shaped(RecipeCategory.MISC, ModBlocks.BREW_BARREL)
                    .pattern("ABA")
                    .pattern("COC")
                    .pattern("ABA")
                    .define('A', ItemTags.PLANKS)
                    .define('B', ModItems.MACHINE_CORE)
                    .define('C', CommonItemTags.BROWN)
                    .define('O', ModBlocks.RICE_MACHINE_SHELL)
                    .unlockedBy("has_planks", has(ItemTags.PLANKS))
                    .unlockedBy("has_machine_core", has(ModItems.MACHINE_CORE))
                    .unlockedBy("has_rice_machine_shell", has(ModBlocks.RICE_MACHINE_SHELL))
                    .save(output, "brew_barrel");
            // 胡萝卜冰淇淋
            // 肠粉
            this.shaped(RecipeCategory.FOOD, ModItems.STEAMED_VERMICELLI_ROLL)
                    .pattern("AAA")
                    .pattern("BBB")
                    .pattern("CDE")
                    .define('A', ModItems.MILLED_RICE)
                    .define('B', Items.WATER_BUCKET)
                    .define('C', ModItems.SCALLION)
                    .define('D', ItemTags.MEAT)
                    .define('E', ModItems.SAUSAGE)
                    .unlockedBy("A", has(ModItems.MILLED_RICE))
                    .unlockedBy("B", has(Items.WATER_BUCKET))
                    .save(output, "steamed_vermicelli_roll");
            // 樱桃树苗
            this.shaped(RecipeCategory.MISC, ModBlocks.CHERRY_SAPLING)
                    .pattern(" C ")
                    .pattern("CSC")
                    .pattern(" C ")
                    .define('C', ModItems.CHERRY)
                    .define('S', ItemTags.SAPLINGS)
                    .unlockedBy("has_sapling", has(ItemTags.SAPLINGS))
                    .save(output, "cherry_sapling");
            // 鸡肉意面
            this.shapeless(RecipeCategory.FOOD, ModItems.PASTA_CHICKEN)
                    .requires(ModItems.PASTA)
                    .requires(Items.COOKED_CHICKEN)
                    .unlockedBy("has_pasta", has(ModItems.PASTA))
                    .save(output, "pasta_chicken");
            // 辣条
            this.shapeless(RecipeCategory.FOOD, ModItems.CHILI_TOFU_STRIP)
                    .requires(ModItems.TOFU_STRIP)
                    .requires(ModItems.CHILI)
                    .unlockedBy("has_tofu_strip", has(ModItems.TOFU_STRIP))
                    .save(output, "chili_tofu_strip");
            // 巧克力
            this.shapeless(RecipeCategory.FOOD, ModItems.CHOCOLATE)
                    .requires(ModItems.CHOCOLATE_DUST)
                    .requires(Items.SUGAR)
                    .unlockedBy("has_chocolate_dust", has(ModItems.CHOCOLATE_DUST))
                    .save(output, "chocolate");
            // 菜板
            this.shaped(RecipeCategory.MISC, ModBlocks.CHOPPING_BOARD)
                    .pattern("AAA")
                    .pattern("A A")
                    .pattern("AOA")
                    .define('A', ItemTags.PLANKS)
                    .define('O', ModItems.MACHINE_CORE)
                    .unlockedBy("has_planks", has(ItemTags.PLANKS))
                    .unlockedBy("has_machine_core", has(ModItems.MACHINE_CORE))
                    .save(output, "chopping_board");
            // 春卷
            this.shapeless(RecipeCategory.FOOD, ModItems.SPRING_ROLLS)
                    .requires(ModItems.TOFU_STRIP)
                    .requires(ModItems.WHITE_RADDISH_STRIP)
                    .requires(ItemTags.MEAT)
                    .requires(ModItems.VEGETABLE)
                    .unlockedBy("has_tofu_strip", has(ModItems.TOFU_STRIP))
                    .save(output, "spring_rolls");
            // 糍粑
            this.shaped(RecipeCategory.FOOD, ModItems.STICKY_RICE_CAKE)
                    .pattern("AAA")
                    .pattern("ABA")
                    .pattern("AAA")
                    .define('A', ModItems.STICKY_RICE_FLOUR)
                    .define('B', Items.WATER_BUCKET)
                    .unlockedBy("has_sticky_rice_flour", has(ModItems.STICKY_RICE_FLOUR))
                    .save(output, "sticky_rice_cake");
            // 机器核心、电路板
            this.shaped(RecipeCategory.MISC, ModItems.MACHINE_CORE)
                    .pattern("AAA")
                    .pattern("CBC")
                    .pattern("AAA")
                    .define('A', Items.REDSTONE)
                    .define('C', Items.IRON_INGOT)
                    .define('B', ModItemTags.CIRCUIT_INGREDIENT)
                    .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                    .save(output, "machine_core");
            // 椰子冰淇淋
            // 椰子树苗
            this.shaped(RecipeCategory.MISC, ModBlocks.COCONUT_SAPLING)
                    .pattern(" A ")
                    .pattern("AXA")
                    .pattern(" A ")
                    .define('A', ModItems.COCONUT)
                    .define('X', ItemTags.SAPLINGS)
                    .unlockedBy("has_sapling", has(ItemTags.SAPLINGS))
                    .save(output, "coconut_sapling");
            // 曲奇，饼干
            this.shaped(RecipeCategory.FOOD, ModItems.BISCUIT, 3)
                    .pattern("AAA")
                    .pattern("BBB")
                    .pattern("AAA")
                    .define('A', ModItems.FLOUR)
                    .define('B', CommonItemTags.EGG)
                    .unlockedBy("has_flour", has(ModItems.FLOUR))
                    .unlockedBy("has_egg", has(CommonItemTags.EGG))
                    .save(output, "flour");
            // 过桥米线
            this.shaped(RecipeCategory.FOOD, ModItems.CROSSING_BRIDGE_NOODLES)
                    .pattern("ABA")
                    .pattern("ABA")
                    .pattern("ABA")
                    .define('A', Items.WATER_BUCKET)
                    .define('B', ModItems.MILLED_RICE)
                    .unlockedBy("has_milled_rice", has(ModItems.MILLED_RICE))
                    .save(output, "crossing_bridge_noodles");
            // 红砖树苗
            this.shaped(RecipeCategory.MISC, ModBlocks.CHINESE_DATE_SAPLING)
                    .pattern(" A ")
                    .pattern("ABA")
                    .pattern(" A ")
                    .define('A', ModItems.CHINESE_DATES)
                    .define('B', ItemTags.SAPLINGS)
                    .unlockedBy("has_sapling", has(ItemTags.SAPLINGS))
                    .save(output, "date_sapling");
            // 豆腐干
            this.shapeless(RecipeCategory.FOOD, ModItems.DRIED_TOFU)
                    .requires(ModItems.SOY_SAUCE)
                    .requires(ModItems.TOFU)
                    .unlockedBy("has_tofu", has(ModItems.TOFU))
                    .save(output, "dried_tofu");
            // 钻石菜刀
            this.shaped(RecipeCategory.TOOLS, ModItems.DIAMOND_KITCHEN_KNIFE)
                    .pattern("AAB")
                    .pattern("AA ")
                    .define('A', Items.DIAMOND)
                    .define('B', ItemTags.LOGS)
                    .unlockedBy("logs", has(ItemTags.LOGS))
                    .save(output, "diamond_kitchen_knife");
            // 面团，饺子皮
            this.shaped(RecipeCategory.FOOD, ModItems.DUMPLING_WRAPPERS, 2)
                    .pattern("ABA")
                    .pattern("ABA")
                    .pattern("ABA")
                    .define('A', ModItems.FLOUR)
                    .define('B', Items.WATER_BUCKET)
                    .unlockedBy("has_flour", has(ModItems.FLOUR))
                    .save(output, "dough");
            // 饺子
            this.shapeless(RecipeCategory.FOOD, ModItems.DUMPLING)
                    .requires(ModItems.DUMPLING_MEAT)
                    .requires(ModItems.DUMPLING_WRAPPERS)
                    .unlockedBy("has_dumping_wrappers", has(ModItems.DUMPLING_WRAPPERS))
                    .unlockedBy("has_dumping_meat", has(ModItems.DUMPLING_MEAT));
            // 绿宝石菜刀
            this.shaped(RecipeCategory.TOOLS, ModItems.EMERALD_KITCHEN_KNIFE)
                    .pattern("AAB")
                    .pattern("AA ")
                    .define('A', Items.EMERALD)
                    .define('B', ItemTags.LOGS)
                    .unlockedBy("has_logs", has(ItemTags.LOGS))
                    .save(output, "emerald_kitchen_knife");
            // 油炸机
            this.shaped(RecipeCategory.FOOD, ModBlocks.FRYING_MACHINE)
                    .pattern("AAA")
                    .pattern("BOB")
                    .pattern("CCC")
                    .define('A', ModItems.PEANUT_OIL)
                    .define('B', Blocks.GLASS)
                    .define('C', ModItems.MACHINE_CORE)
                    .define('O', ModBlocks.RICE_MACHINE_SHELL)
                    .unlockedBy("has_peanut_oil", has(ModItems.PEANUT_OIL))
                    .unlockedBy("has_machine_core", has(ModItems.MACHINE_CORE))
                    .unlockedBy("has_rice_machine_shell", has(ModBlocks.RICE_MACHINE_SHELL))
                    .save(output, "frying_machine");
            // 果汁杯子
            this.shaped(RecipeCategory.MISC, ModItems.JUICE_BOTTLE)
                    .pattern("A A")
                    .pattern("A A")
                    .pattern(" A ")
                    .define('A', Blocks.GLASS)
                    .unlockedBy("has_glass", has(Blocks.GLASS))
                    .save(output, "juice_bottle");
            // 金制菜刀
            this.shaped(RecipeCategory.MISC, ModItems.GOLDEN_KITCHEN_KNIFE)
                    .pattern("AAX")
                    .pattern("AA ")
                    .define('A', Items.GOLD_INGOT)
                    .define('X', ItemTags.LOGS)
                    .unlockedBy("has_logs", has(ItemTags.LOGS))
                    .save(output, "golden_kitchen_knife");
            // 金葡萄
            this.shaped(RecipeCategory.FOOD, ModItems.GOLDEN_GRAPES)
                    .pattern("AAA")
                    .pattern("ABA")
                    .pattern("AAA")
                    .define('A', Items.GOLD_INGOT)
                    .define('B', ModItems.GRAPES)
                    .unlockedBy("has_grapes", has(ModItems.GRAPES))
                    .unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT))
                    .save(output, "golden_grapes");
            // 柚子树苗
            this.shaped(RecipeCategory.MISC, ModBlocks.GRAPEFRUIT_SAPLING)
                    .pattern(" A ")
                    .pattern("ABA")
                    .pattern(" A ")
                    .define('A', ModItems.GRAPEFRUIT)
                    .define('B', ItemTags.SAPLINGS)
                    .unlockedBy("has_sapling", has(ItemTags.SAPLINGS))
                    .save(output, "grapefruit_sapling");
            // 汉堡包
            this.shapeless(RecipeCategory.FOOD, ModItems.HAMBURGER)
                    .requires(ModItems.VEGETABLE)
                    .requires(Items.BREAD)
                    .requires(Items.BREAD)
                    .requires(ItemTags.MEAT)
                    .unlockedBy("has_meat", has(ItemTags.MEAT))
                    .unlockedBy("has_bread", has(Items.BREAD))
                    .save(output, "hamburger");
            // 山楂树苗
            this.shaped(RecipeCategory.MISC, ModBlocks.HAWTHORN_SAPLING)
                    .pattern(" A ")
                    .pattern("ABA")
                    .pattern(" A ")
                    .define('A', ModItems.HAWTHORN)
                    .define('B', ItemTags.SAPLINGS)
                    .unlockedBy("has_sapling", has(ItemTags.SAPLINGS))
                    .save(output, "hawthorn_sapling");
            // 热狗
            this.shapeless(RecipeCategory.FOOD, ModItems.HOT_DOG)
                    .requires(ModItems.VEGETABLE)
                    .requires(ModItems.SAUSAGE)
                    .requires(Items.BREAD)
                    .unlockedBy("has_sausage", has(ModItems.SAUSAGE))
                    .save(output, "hot_dog");
            // 铁制菜刀
            this.shaped(RecipeCategory.MISC, ModItems.KITCHEN_KNIFE)
                    .pattern("AAX")
                    .pattern("AA ")
                    .define('A', Items.IRON_INGOT)
                    .define('X', ItemTags.LOGS)
                    .unlockedBy("has_logs", has(ItemTags.LOGS))
                    .save(output, "iron_kitchen_knife");
            // 铁盘
            // 金克拉
            this.shaped(RecipeCategory.MISC, ModItems.GOLDEN_BONE_MEAL)
                    .pattern("AAA")
                    .pattern("ABA")
                    .pattern("AAA")
                    .define('A', Items.GOLD_INGOT)
                    .define('B', Items.BONE_MEAL)
                    .unlockedBy("has_bone_meal", has(Items.BONE_MEAL))
                    .save(output, "golden_bone_meal");
            // 腊八粥
            this.shapeless(RecipeCategory.FOOD, ModItems.LA_BA_PORRIDGE)
                    .requires(Items.WHEAT)
                    .requires(ModItems.MILLED_RICE)
                    .requires(ModItems.PEANUT)
                    .requires(ModItems.BEANS)
                    .requires(ModItems.CHINESE_BEAN)
                    .requires(ModItems.CHINESE_SAUSAGE)
                    .requires(ModItems.STICKY_RICE)
                    .requires(Items.CARROT)
                    .unlockedBy("has_milled_rice", has(ModItems.MILLED_RICE))
                    .save(output, "la_ba_porridge");
            // 柠檬冰淇淋
            // 柠檬树
            this.shaped(RecipeCategory.MISC, ModBlocks.LEMON_SAPLING)
                    .pattern(" A ")
                    .pattern("ABA")
                    .pattern(" A ")
                    .define('A', ModItems.LEMON)
                    .define('B', ItemTags.SAPLINGS)
                    .unlockedBy("has_sapling", has(ItemTags.SAPLINGS))
                    .save(output, "lemon_sapling");
            // 荔枝冰淇淋
            // 荔枝树
            this.shaped(RecipeCategory.MISC, ModBlocks.LYCHEE_SAPLING)
                    .pattern(" A ")
                    .pattern("ABA")
                    .pattern(" A ")
                    .define('A', ModItems.LYCHEE)
                    .define('B', ItemTags.SAPLINGS)
                    .unlockedBy("has_sapling", has(ItemTags.SAPLINGS))
                    .save(output, "lychee_sapling");
            // 龙眼树苗
            this.shaped(RecipeCategory.MISC, ModBlocks.LONGYAN_SAPLING)
                    .pattern(" A ")
                    .pattern("ABA")
                    .pattern(" A ")
                    .define('A', ModItems.LONGYAN)
                    .define('B', ItemTags.SAPLINGS)
                    .unlockedBy("has_sapling", has(ItemTags.SAPLINGS))
                    .save(output, "longyan_sapling");
            // 枇杷树苗
            this.shaped(RecipeCategory.MISC, ModBlocks.LOQUAT_SAPLING)
                    .pattern(" A ")
                    .pattern("ABA")
                    .pattern(" A ")
                    .define('A', ModItems.LOQUAT)
                    .define('B', ItemTags.SAPLINGS)
                    .unlockedBy("has_sapling", has(ItemTags.SAPLINGS))
                    .save(output, "loquat_sapling");
            // 芒果冰淇淋
            // 芒果树苗
            this.shaped(RecipeCategory.MISC, ModBlocks.MANGO_SAPLING)
                    .pattern(" A ")
                    .pattern("ABA")
                    .pattern(" A ")
                    .define('A', ModItems.MANGO)
                    .define('B', ItemTags.SAPLINGS)
                    .unlockedBy("has_sapling", has(ItemTags.SAPLINGS))
                    .save(output, "mango_sapling");
            // 西瓜冰淇淋
            // 碾磨机
            this.shaped(RecipeCategory.FOOD, ModBlocks.MILL)
                    .pattern("AAA")
                    .pattern("BOB")
                    .pattern("DCD")
                    .define('A', Items.WHEAT)
                    .define('B', Blocks.GLASS)
                    .define('C', ModItems.MACHINE_CORE)
                    .define('D', Items.MILK_BUCKET)
                    .define('O', ModBlocks.RICE_MACHINE_SHELL)
                    .unlockedBy("has_machine_core", has(ModItems.MACHINE_CORE))
                    .unlockedBy("has_rice_machine_shell", has(ModBlocks.RICE_MACHINE_SHELL))
                    .save(output, "mill");
            // 月饼
            this.shaped(RecipeCategory.FOOD, ModItems.MOONCAKE)
                    .pattern("AAA")
                    .pattern("XBX")
                    .pattern("AAA")
                    .define('A', ModItems.FLOUR)
                    .define('B', ModItemTags.TANGYUAN_STUFFING)
                    .define('X', Items.YELLOW_DYE)
                    .unlockedBy("has_flour", has(ModItems.FLOUR))
                    .save(output, "mooncake");
            // 稻米机器外壳
            this.shaped(RecipeCategory.MISC, ModBlocks.RICE_MACHINE_SHELL)
                    .pattern("AAA")
                    .pattern("ABA")
                    .pattern("AAA")
                    .define('A', Items.IRON_INGOT)
                    .define('B', ModItemTags.MACHINE_INGREDIENT)
                    .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                    .save(output, "rice_machine_shell");
            // 年糕
            this.shaped(RecipeCategory.FOOD, ModItems.NEW_YEAR_CAKE)
                    .pattern("AAA")
                    .pattern("ABA")
                    .pattern("AAA")
                    .define('A', ModItems.DUMPLING_WRAPPERS)
                    .define('B', ModItems.MILLED_RICE)
                    .unlockedBy("has_dumpling_wrappers", has(ModItems.DUMPLING_WRAPPERS))
                    .save(output, "new_year_cake");
            // 过桥米线
            // 橘子汁冰淇淋
            // 橘子树
            this.shaped(RecipeCategory.MISC, ModBlocks.ORANGE_SAPLING)
                    .pattern(" A ")
                    .pattern("ABA")
                    .pattern(" A ")
                    .define('A', ModItems.ORANGE)
                    .define('B', ItemTags.SAPLINGS)
                    .unlockedBy("has_sapling", has(ItemTags.SAPLINGS))
                    .save(output, "orange_sapling");
            // 奥尔良鸡翅
            this.shapeless(RecipeCategory.FOOD, ModItems.ORLEAN_CHICKEN_RICE)
                    .requires(ModItems.ORLEAN_WING)
                    .requires(ModItems.COOKED_RICE)
                    .unlockedBy("has_orlean_wing", has(ModItems.ORLEAN_WING))
                    .unlockedBy("has_cooked_rice", has(ModItems.COOKED_RICE))
                    .save(output, "orlean_chicken_rice");
            // 平底锅
            this.shaped(RecipeCategory.MISC, ModBlocks.PAN)
                    .pattern("AAA")
                    .pattern("DBD")
                    .pattern(" C ")
                    .define('A', Items.IRON_INGOT)
                    .define('B', ModBlocks.RICE_MACHINE_SHELL)
                    .define('C', ModItems.MACHINE_CORE)
                    .define('D', Blocks.GLASS)
                    .unlockedBy("has_rice_machine_shell", has(ModBlocks.RICE_MACHINE_SHELL))
                    .unlockedBy("has_machine_core", has(ModItems.MACHINE_CORE))
                    .save(output, "pan");
            // 木瓜冰淇淋
            // 木瓜树苗
            this.shaped(RecipeCategory.MISC, ModBlocks.PAPAYA_SAPLING)
                    .pattern(" A ")
                    .pattern("ABA")
                    .pattern(" A ")
                    .define('A', ModItems.PAPAYA)
                    .define('B', ItemTags.SAPLINGS)
                    .unlockedBy("has_sapling", has(ItemTags.SAPLINGS))
                    .save(output, "papaya_sapling");
            // 意粉
            this.shaped(RecipeCategory.FOOD, ModItems.PASTA)
                    .pattern("ABA")
                    .pattern("ABA")
                    .pattern("   ")
                    .define('A', ModItems.FLOUR)
                    .define('B', Items.WATER_BUCKET)
                    .unlockedBy("has_flour", has(ModItems.FLOUR))
                    .save(output, "pasta");
            // 梨子汁冰淇淋
            // 梨子树苗
            this.shaped(RecipeCategory.MISC, ModBlocks.PEAR_SAPLING)
                    .pattern(" A ")
                    .pattern("ABA")
                    .pattern(" A ")
                    .define('A', ModItems.PEAR)
                    .define('B', ItemTags.SAPLINGS)
                    .unlockedBy("has_sapling", has(ItemTags.SAPLINGS))
                    .save(output, "pear_sapling");
            // 柿子汁冰淇淋
            // 柿子树苗
            this.shaped(RecipeCategory.MISC, ModBlocks.PERSIMMON_SAPLING)
                    .pattern(" A ")
                    .pattern("ABA")
                    .pattern(" A ")
                    .define('A', ModItems.PERSIMMON)
                    .define('B', ItemTags.SAPLINGS)
                    .unlockedBy("has_sapling", has(ItemTags.SAPLINGS))
                    .save(output, "persimmon_sapling");
            // 披萨
            this.shapeless(RecipeCategory.FOOD, ModItems.PIZZA)
                    .requires(ModItems.DUMPLING_WRAPPERS)
                    .requires(Items.CARROT)
                    .requires(ModItems.CHEESE)
                    .requires(CommonItemTags.MUSHROOM)
                    .unlockedBy("has_carrot", has(Items.CARROT))
                    .save(output, "pizza");
            // 石榴树苗
            this.shaped(RecipeCategory.MISC, ModBlocks.POMEGRANATE_SAPLING)
                    .pattern(" A ")
                    .pattern("ABA")
                    .pattern(" A ")
                    .define('A', ModItems.POMEGRANATE)
                    .define('B', ItemTags.SAPLINGS)
                    .unlockedBy("has_sapling", has(ItemTags.SAPLINGS))
                    .save(output, "pomegranate_sapling");
            // 猪肉培根披萨
            // 锅
            this.shaped(RecipeCategory.MISC, ModBlocks.POT)
                    .pattern("AAA")
                    .pattern("DBD")
                    .pattern(" C ")
                    .define('A', Items.IRON_INGOT)
                    .define('B', ModBlocks.RICE_MACHINE_SHELL)
                    .define('C', ModItems.MACHINE_CORE)
                    .define('D', Items.WHITE_DYE)
                    .unlockedBy("has_rice_machine_shell", has(ModBlocks.RICE_MACHINE_SHELL))
                    .unlockedBy("has_machine_core", has(ModItems.MACHINE_CORE))
                    .save(output, "pot");
            // 高压锅
            this.shaped(RecipeCategory.MISC, ModBlocks.PRESSURE_COOKER)
                    .pattern("AAA")
                    .pattern("DBD")
                    .pattern("ECE")
                    .define('A', Items.IRON_INGOT)
                    .define('B', ModBlocks.RICE_MACHINE_SHELL)
                    .define('C', ModItems.MACHINE_CORE)
                    .define('D', Items.WHITE_DYE)
                    .define('E', Items.BLACK_DYE)
                    .unlockedBy("has_rice_machine_shell", has(ModBlocks.RICE_MACHINE_SHELL))
                    .unlockedBy("has_machine_core", has(ModItems.MACHINE_CORE))
                    .save(output, "pressure_cooker");
            // 纯净水
            this.shapeless(RecipeCategory.MISC, ModItems.WATERCRESS)
                    .requires(Items.WATER_BUCKET)
                    .requires(ItemTags.WOOL)
                    .unlockedBy("has_water_bucket", has(Items.WATER_BUCKET))
                    .unlockedBy("has_wool", has(ItemTags.WOOL))
                    .save(output, "watercress");
            // 酸菜饼
            this.shaped(RecipeCategory.FOOD, ModItems.SAUERKRAUT_CAKE)
                    .pattern("AAA")
                    .pattern("ABA")
                    .pattern("AAA")
                    .define('A', ModItems.FLOUR)
                    .define('B', ModItems.VEGETABLE)
                    .unlockedBy("has_flour", has(ModItems.FLOUR))
                    .unlockedBy("has_vegetable", has(ModItems.VEGETABLE))
                    .save(output, "sauerkraut_cake");
            // 笑脸饼干
            this.shaped(RecipeCategory.FOOD, ModItems.SMILEY_COOKIE)
                    .pattern("AAA")
                    .pattern("ABA")
                    .pattern("AAA")
                    .define('A', ModItems.FLOUR)
                    .define('B', CommonItemTags.EGG)
                    .unlockedBy("has_flour", has(ModItems.FLOUR))
                    .unlockedBy("has_eggs", has(CommonItemTags.EGG))
                    .save(output, "smiley_cookie");
            // 馒头
            this.shaped(RecipeCategory.FOOD, ModItems.STEAMED_BUNS)
                    .pattern("AAA")
                    .pattern("ABA")
                    .pattern("AAA")
                    .define('A', ModItems.FLOUR)
                    .define('B', Items.WATER_BUCKET)
                    .unlockedBy("has_flour", has(ModItems.FLOUR))
                    .unlockedBy("has_water_bucket", has(Items.WATER_BUCKET))
                    .save(output, "steamed_buns");
            // 檽米团
            this.shapeless(RecipeCategory.FOOD, ModItems.STICKY_RICE_DOUGH)
                    .requires(ModItems.STICKY_RICE_FLOUR)
                    .requires(ModItems.STICKY_RICE_FLOUR)
                    .requires(ModItems.STICKY_RICE)
                    .requires(Items.WATER_BUCKET)
                    .unlockedBy("has_sticky_rice_flour", has(ModItems.STICKY_RICE_FLOUR))
                    .unlockedBy("has_sticky_rice", has(ModItems.STICKY_RICE))
                    .save(output, "sticky_rice_dough");
            // 石制菜刀
            // 炉灶
            this.shaped(RecipeCategory.MISC, ModBlocks.STOVE)
                    .pattern("AFA")
                    .pattern("DBD")
                    .pattern("ECE")
                    .define('A', Items.FLINT_AND_STEEL)
                    .define('B', ModBlocks.RICE_MACHINE_SHELL)
                    .define('C', ModItems.MACHINE_CORE)
                    .define('D', Blocks.BRICKS)
                    .define('E', Items.REDSTONE)
                    .define('F', Blocks.GLASS)
                    .unlockedBy("has_bricks", has(Blocks.BRICKS))
                    .unlockedBy("has_rice_machine_shell", has(ModBlocks.RICE_MACHINE_SHELL))
                    .unlockedBy("has_machine_core", has(ModItems.MACHINE_CORE))
                    .save(output, "stove");
            // 草莓冰淇淋
            // 甜水
            // 豆腐
            this.shapeless(RecipeCategory.FOOD, ModItems.TOFU)
                    .requires(Items.MILK_BUCKET)
                    .requires(CommonItemTags.SALT)
                    .unlockedBy("has_milk_bucket", has(Items.MILK_BUCKET))
                    .save(output, "tofu");
            // 扳手
            // 粽子
            this.shapeless(RecipeCategory.FOOD, ModItems.STICKY_RICE_DUMPLING)
                    .requires(ModItems.BAMBOO_LEAVES)
                    .requires(Items.STRING)
                    .requires(ModItems.STICKY_RICE)
                    .requires(ItemTags.MEAT)
                    .unlockedBy("has_sticky_rice", has(ModItems.STICKY_RICE))
                    .unlockedBy("has_zongye", has(ModItems.BAMBOO_LEAVES))
                    .save(output, "sticky_rice_dumpling");
        }

        public void smelting() {
            // 烤玉米
            this.smeltingResultFromBase(ModItems.COOKED_CORN, ModItems.CORN);
            // 盐
            this.smeltingResultFromBase(ModItems.SALT, Items.WATER_BUCKET);
            // 烤鱿鱼
            this.smeltingResultFromBase(ModItems.COOKED_SQUID_MEAT, ModItems.SQUID_MEAT);
            // 烤红薯
            this.smeltingResultFromBase(ModItems.COOKED_SWEET_POTATO, ModItems.SWEET_POTATO);
        }
    }

    @NotNull
    @Override
    public String getName() {
        return "Foodcraft Recipes";
    }
}

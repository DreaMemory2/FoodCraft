package com.crystal.foodcraft.datagen;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.block.ModBlocks;
import com.crystal.foodcraft.item.juice.JuiceColor;
import com.crystal.foodcraft.item.ModItems;
import com.crystal.foodcraft.model.FCTexturedModel;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import org.jetbrains.annotations.NotNull;

/**
 * <p>在正式版本中，添加{@link Material}类</p>
 * @since 26.1-snapshot-6
 * @version 26.1
 */
public class ModModelDataGeneration extends FabricModelProvider {
    /**
     * <p>注意{@code VariantMutator}类的包名从{@code net.minecraft.client.renderer.block.model}改为{@code net.minecraft.client.renderer.block.dispatch}</p>
     */
    private static final PropertyDispatch<VariantMutator> ROTATION_HORIZONTAL_FACING = PropertyDispatch
            .modify(BlockStateProperties.HORIZONTAL_FACING)
            .select(Direction.EAST, BlockModelGenerators.Y_ROT_90)
            .select(Direction.SOUTH, BlockModelGenerators.Y_ROT_180)
            .select(Direction.WEST, BlockModelGenerators.Y_ROT_270)
            .select(Direction.NORTH, BlockModelGenerators.NOP);

    public ModModelDataGeneration(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(@NotNull BlockModelGenerators model) {
        // 机器
        model.createTrivialCube(ModBlocks.RICE_MACHINE_SHELL);
        createRiceMachine(model, ModBlocks.MILL, FCTexturedModel.ORIENTABLE_TOP_AND_DOWN);
        createRiceMachine(model, ModBlocks.FRYING_MACHINE, FCTexturedModel.ORIENTABLE_TOP_AND_DOWN);
        createRiceMachine(model, ModBlocks.BEVERAGE_MAKING_MACHINE, FCTexturedModel.ORIENTABLE_TOP_AND_DOWN);
        createStove(model);
        createBrewBarrel(model);
        // 液体
        // 树叶
        createLeaves(model, ModBlocks.LEAVES);
        createLeaves(model, ModBlocks.PEAR_LEAVES);
        createLeaves(model, ModBlocks.LYCHEE_LEAVES);
        createLeaves(model, ModBlocks.PEACH_LEAVES);
        createLeaves(model, ModBlocks.ORANGE_LEAVES);
        createLeaves(model, ModBlocks.LOQUAT_LEAVES);
        createLeaves(model, ModBlocks.MANGO_LEAVES);
        createLeaves(model, ModBlocks.LEMON_LEAVES);
        createLeaves(model, ModBlocks.GRAPEFRUIT_LEAVES);
        createLeaves(model, ModBlocks.PERSIMMON_LEAVES);
        createLeaves(model, ModBlocks.PAPAYA_LEAVES);
        createLeaves(model, ModBlocks.HAWTHORN_LEAVES);
        createLeaves(model, ModBlocks.LONGYAN_LEAVES);
        createLeaves(model, ModBlocks.POMEGRANATE_LEAVES);
        createLeaves(model, ModBlocks.CHINESE_DATE_LEAVES);
        createLeaves(model, ModBlocks.CHERRY_LEAVES);
        // CAKE
        createCakeBlock(model, ModBlocks.STRAWBERRY_CAKE);
        createCakeBlock(model, ModBlocks.GRAPES_CAKE);
        createCakeBlock(model, ModBlocks.GOLDEN_GRAPES_CAKE);
        createCakeBlock(model, ModBlocks.PEAR_CAKE);
        createCakeBlock(model, ModBlocks.PEACH_CAKE);
        createCakeBlock(model, ModBlocks.ORANGE_CAKE);
        createCakeBlock(model, ModBlocks.LEMON_CAKE);
        createCakeBlock(model, ModBlocks.COCONUT_CAKE);

        model.createTrivialCube(ModBlocks.MILLED_RICE);
        model.createTrivialCube(ModBlocks.SALT);
        model.createTrivialCube(ModBlocks.COCONUT);
        model.createTrivialCube(ModBlocks.BANANA);
        model.createTrivialCube(ModBlocks.SUGAR);
        model.createTrivialCube(ModBlocks.STICKY_RICE_BLOCK);
        model.createTrivialCube(ModBlocks.CHOCOLATE);
        model.createTrivialCube(ModBlocks.CARROT_BLOCK);
        model.createTrivialCube(ModBlocks.POTATO_BLOCK);
        model.createTrivialCube(ModBlocks.WATERCRESS_BLOCK);
        model.createTrivialCube(ModBlocks.PEANUT_BLOCK);
        model.createTrivialCube(ModBlocks.BEAN_BLOCK);
        // 树苗
        createSapling(model, ModBlocks.PEAR_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        createSapling(model, ModBlocks.LYCHEE_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        createSapling(model, ModBlocks.PEACH_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        createSapling(model, ModBlocks.ORANGE_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        createSapling(model, ModBlocks.LOQUAT_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        createSapling(model, ModBlocks.MANGO_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        createSapling(model, ModBlocks.LEMON_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        createSapling(model, ModBlocks.GRAPEFRUIT_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        createSapling(model, ModBlocks.PERSIMMON_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        createSapling(model, ModBlocks.PAPAYA_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        createSapling(model, ModBlocks.HAWTHORN_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        createSapling(model, ModBlocks.LONGYAN_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        createSapling(model, ModBlocks.POMEGRANATE_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        createSapling(model, ModBlocks.CHINESE_DATE_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        createSapling(model, ModBlocks.CHERRY_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        createSapling(model, ModBlocks.BANANA_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        createSapling(model, ModBlocks.COCONUT_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        // 作物
        createCropBlock(model, ModBlocks.WHITE_RADDISH);
        createCropBlock(model, ModBlocks.STRAWBERRY);
        createCropBlock(model, ModBlocks.BEANS);
        createCropBlock(model, ModBlocks.TOMATO);
        createCropBlock(model, ModBlocks.RED_BEAN);
        createCropBlock(model, ModBlocks.SWEET_POTATO);
        createCropBlock(model, ModBlocks.CUCUMBER);
        createCropBlock(model, ModBlocks.PEANUT);
        createCropBlock(model, ModBlocks.CHILI);
        createCropBlock(model, ModBlocks.GREEN_BEANS);
        createCropBlock(model, ModBlocks.STICKY_RICE);
        createCropBlock(model, ModBlocks.GRAPES);
        createCropBlock(model, ModBlocks.EGGPLANT);
        createCropBlock(model, ModBlocks.GREEN_PEPPER);
        createCropBlock(model, ModBlocks.VEGETABLE);
        createCropBlock(model, ModBlocks.RICE);
        createCropBlock(model, ModBlocks.CORN);

        // 液体模型
        model.createNonTemplateModelBlock(ModBlocks.COOKING_OIL);
    }

    @Override
    public void generateItemModels(@NotNull ItemModelGenerators model) {
        // Machines and Tools
        createFlatItemModel(model, ModItems.IRON_SHEET);
        createFlatItemModel(model, ModItems.ITEM_BUCKET);
        createFlatItemModel(model, ModItems.MACHINE_CORE);
        createFlatItemModel(model, ModItems.KITCHEN_KNIFE);
        createFlatItemModel(model, ModItems.GOLDEN_KITCHEN_KNIFE);
        createFlatItemModel(model, ModItems.EMERALD_KITCHEN_KNIFE);
        createFlatItemModel(model, ModItems.DIAMOND_KITCHEN_KNIFE);
        createFlatItemModel(model, ModItems.WRENCH);
        createFlatItemModel(model, ModItems.GOLDEN_BONE_MEAL);
        createFlatItemModel(model, ModItems.ITEM_NULL);
        // Plants
        createFlatItemModel(model, ModItems.SCALLION);
        createFlatItemModel(model, ModItems.LONG_BEAN);
        createFlatItemModel(model, ModItems.BAMBOO_LEAVES);
        createFlatItemModel(model, ModItems.GOLDEN_GRAPES);
        createFlatItemModel(model, ModItems.PEAR);
        createFlatItemModel(model, ModItems.LYCHEE);
        createFlatItemModel(model, ModItems.PEACH);
        createFlatItemModel(model, ModItems.ORANGE);
        createFlatItemModel(model, ModItems.LOQUAT);
        createFlatItemModel(model, ModItems.MANGO);
        createFlatItemModel(model, ModItems.LEMON);
        createFlatItemModel(model, ModItems.GRAPEFRUIT);
        createFlatItemModel(model, ModItems.PERSIMMON);
        createFlatItemModel(model, ModItems.PAPAYA);
        createFlatItemModel(model, ModItems.HAWTHORN);
        createFlatItemModel(model, ModItems.LONGYAN);
        createFlatItemModel(model, ModItems.POMEGRANATE);
        createFlatItemModel(model, ModItems.CHINESE_DATES);
        createFlatItemModel(model, ModItems.COCONUT);
        createFlatItemModel(model, ModItems.CHERRY);
        createFlatItemModel(model, ModItems.BANANA);
        createFlatItemModel(model, ModItems.VEGETABLE);
        createFlatItemModel(model, ModItems.CHILI);
        createFlatItemModel(model, ModItems.GREEN_PEPPER);
        createFlatItemModel(model, ModItems.EGGPLANT);
        createFlatItemModel(model, ModItems.TOMATO);
        createFlatItemModel(model, ModItems.CORN);
        createFlatItemModel(model, ModItems.CUCUMBER);
        createFlatItemModel(model, ModItems.GRAPES);

        // Drinks
        createFlatItemModel(model, ModItems.SOY_BEAN_MILK);
        createFlatItemModel(model, ModItems.RED_WINE);
        createFlatItemModel(model, ModItems.SPIRIT);
        createFlatItemModel(model, ModItems.GRAPE_WINE);
        createFlatItemModel(model, ModItems.CIDER);
        createFlatItemModel(model, ModItems.GOLDEN_GRAPE_WINE);
        createFlatItemModel(model, ModItems.GOLDEN_CIDER);
        createFlatItemModel(model, ModItems.PEACH_WINE);
        createFlatItemModel(model, ModItems.LYCHEE_WINE);
        createFlatItemModel(model, ModItems.PEAR_WINE);
        createFlatItemModel(model, ModItems.MANGO_WINE);
        createFlatItemModel(model, ModItems.LEMON_WINE);
        createFlatItemModel(model, ModItems.POMEGRANATE_WINE);

        createFlatItemModel(model, ModItems.VANILLA_ICE_CREAM);
        createFlatItemModel(model, ModItems.CHOCOLATES_MILK_ICE_CREAM);
        createFlatItemModel(model, ModItems.GRAPE_JUICE_ICE_CREAM);
        createFlatItemModel(model, ModItems.APPLE_JUICE_ICE_CREAM);
        createFlatItemModel(model, ModItems.GOLDEN_GRAPE_JUICE_ICE_CREAM);
        createFlatItemModel(model, ModItems.GOLDEN_APPLE_JUICE_ICE_CREAM);
        createFlatItemModel(model, ModItems.MELON_JUICE_ICE_CREAM);
        createFlatItemModel(model, ModItems.LYCHEE_JUICE_ICE_CREAM);
        createFlatItemModel(model, ModItems.PEACH_JUICE_ICE_CREAM);
        createFlatItemModel(model, ModItems.ORANGE_JUICE_ICE_CREAM);
        createFlatItemModel(model, ModItems.MANGO_JUICE_ICE_CREAM);
        createFlatItemModel(model, ModItems.LEMON_JUICE_ICE_CREAM);
        createFlatItemModel(model, ModItems.PAPAYA_JUICE_ICE_CREAM);
        createFlatItemModel(model, ModItems.STRAWBERRY_JUICE_ICE_CREAM);
        createFlatItemModel(model, ModItems.COCONUT_JUICE_ICE_CREAM);
        createFlatItemModel(model, ModItems.BANANA_JUICE_ICE_CREAM);
        // Staple Food
        createFlatItemModel(model, ModItems.MUSHROOMS_CHICKEN_SOUP);
        createFlatItemModel(model, ModItems.EGG_SOUP);
        createFlatItemModel(model, ModItems.NOODLES);
        createFlatItemModel(model, ModItems.CROSSING_BRIDGE_NOODLES);
        createFlatItemModel(model, ModItems.PRESERVED_EGG_PORRIDGE);
        createFlatItemModel(model, ModItems.LA_BA_PORRIDGE);
        createFlatItemModel(model, ModItems.TOMATO_EGG_RICE);
        createFlatItemModel(model, ModItems.POTATO_PEPPER_EGGPLANT);
        createFlatItemModel(model, ModItems.YUXIANG_SHREDDED_PORK);
        createFlatItemModel(model, ModItems.KUNG_PAO_CHICKEN);
        createFlatItemModel(model, ModItems.COOKED_POTATO_WIRE_RICE);
        createFlatItemModel(model, ModItems.STEAMED_FISH_HEAD);
        createFlatItemModel(model, ModItems.PEPPERY_DOUFU_RICE);
        createFlatItemModel(model, ModItems.RED_BRAISED_PORK_BELLY_RICE);
        createFlatItemModel(model, ModItems.TWICE_COOKED_PORK_SLICES);
        createFlatItemModel(model, ModItems.ORLEAN_CHICKEN_RICE);
        createFlatItemModel(model, ModItems.SPICY_CHICKEN);
        createFlatItemModel(model, ModItems.STEAMED_CHICKEN_CHILI_SAUCE);
        createFlatItemModel(model, ModItems.WHITE_SLICED_CHICKEN);
        createFlatItemModel(model, ModItems.CHICKEN_SCALLION_OIL);
        createFlatItemModel(model, ModItems.BOILED_FISH_SICHUAN_PICKLES);
        createFlatItemModel(model, ModItems.SPICY_FISH);
        createFlatItemModel(model, ModItems.STEAMED_FISH);
        createFlatItemModel(model, ModItems.COLA_CHICKEN_RICE);
        createFlatItemModel(model, ModItems.CURRY_CHICKEN_RICE);
        createFlatItemModel(model, ModItems.SICHUAN_BOILED_BEEF);
        createFlatItemModel(model, ModItems.PASTA);
        createFlatItemModel(model, ModItems.PASTA_PORK);
        createFlatItemModel(model, ModItems.PASTA_BEEF);
        createFlatItemModel(model, ModItems.PASTA_CHICKEN);
        createFlatItemModel(model, ModItems.JAPAN_DOUFU);
        createFlatItemModel(model, ModItems.STEAMED_VERMICELLI_ROLL);
        // Ingredients
        createFlatItemModel(model, ModItems.WATER_ITEM);
        createFlatItemModel(model, ModItems.MILLED_RICE);
        createFlatItemModel(model, ModItems.FLOUR);
        createFlatItemModel(model, ModItems.SALT);
        createFlatItemModel(model, ModItems.DUMPLING_WRAPPERS);
        createFlatItemModel(model, ModItems.WATERCRESS);
        createFlatItemModel(model, ModItems.CHOCOLATE_DUST);
        createFlatItemModel(model, ModItems.DUMPLING_MEAT);
        createFlatItemModel(model, ModItems.PEANUT_OIL);
        createFlatItemModel(model, ModItems.COOKED_RICE);
        createFlatItemModel(model, ModItems.DRUMSTICK);
        createFlatItemModel(model, ModItems.CHICKEN_WING);
        createFlatItemModel(model, ModItems.BIG_CHICKEN);
        createFlatItemModel(model, ModItems.MEDIUM_CHICKEN);
        createFlatItemModel(model, ModItems.SMALL_CHICKEN);
        createFlatItemModel(model, ModItems.POTATO_CHIPS);
        createFlatItemModel(model, ModItems.POTATO_WIRE);
        createFlatItemModel(model, ModItems.SOY_SAUCE);
        createFlatItemModel(model, ModItems.VINEGAR);
        createFlatItemModel(model, ModItems.RED_BEAN_PASTE);
        createFlatItemModel(model, ModItems.STARCHES);
        createFlatItemModel(model, ModItems.STICKY_RICE_DOUGH);
        createFlatItemModel(model, ModItems.PEANUT_FILLING);
        createFlatItemModel(model, ModItems.TOFU_STRIP);
        createFlatItemModel(model, ModItems.CARROT_STRIP);
        createFlatItemModel(model, ModItems.WHITE_RADDISH_STRIP);
        createFlatItemModel(model, ModItems.NOODLE);
        createFlatItemModel(model, ModItems.BLOCK_CURRY);
        createFlatItemModel(model, ModItems.SMOKED_MATERIAL);
        createFlatItemModel(model, ModItems.CHARRED_FOOD);
        // Snacks
        createFlatItemModel(model, ModItems.SQUID_MEAT);
        createFlatItemModel(model, ModItems.COOKED_SQUID_MEAT);
        createFlatItemModel(model, ModItems.SQUID_SLICES);
        createFlatItemModel(model, ModItems.POACHED_EGG);
        createFlatItemModel(model, ModItems.PANCAKES);
        createFlatItemModel(model, ModItems.DUMPLING);
        createFlatItemModel(model, ModItems.FIRED_DUMPLING);
        createFlatItemModel(model, ModItems.TOFU);
        createFlatItemModel(model, ModItems.DRIED_TOFU);
        createFlatItemModel(model, ModItems.FRIED_POTATO_CHIPS);
        createFlatItemModel(model, ModItems.PORRIDGE);
        createFlatItemModel(model, ModItems.STICKY_RICE_DUMPLING);
        createFlatItemModel(model, ModItems.MOONCAKE);
        createFlatItemModel(model, ModItems.TANGYUAN);
        createFlatItemModel(model, ModItems.DOUGH_TWIST);
        createFlatItemModel(model, ModItems.SPRING_ROLLS);
        createFlatItemModel(model, ModItems.CHIPS);
        createFlatItemModel(model, ModItems.SAUSAGE);
        createFlatItemModel(model, ModItems.CHINESE_SAUSAGE);
        createFlatItemModel(model, ModItems.CHINESE_BEAN);
        createFlatItemModel(model, ModItems.FRIED_SAUSAGE);
        createFlatItemModel(model, ModItems.PIZZA);
        createFlatItemModel(model, ModItems.HAMBURGER);
        createFlatItemModel(model, ModItems.BREAD_STICK);
        createFlatItemModel(model, ModItems.CHILI_TOFU_STRIP);
        createFlatItemModel(model, ModItems.FRIED_CHICKEN);
        createFlatItemModel(model, ModItems.ORIGINAL_RECIPE);
        createFlatItemModel(model, ModItems.FRIED_TOFU);
        createFlatItemModel(model, ModItems.FRENCH_FRIES);
        createFlatItemModel(model, ModItems.POPCORN_CHICKEN);
        createFlatItemModel(model, ModItems.ORLEAN_WING);
        createFlatItemModel(model, ModItems.FRIED_SPRING_ROLLS);
        createFlatItemModel(model, ModItems.FRIED_DOUGH_TWIST);
        createFlatItemModel(model, ModItems.FRIED_DRUM_STICK);
        createFlatItemModel(model, ModItems.COOKED_SWEET_POTATO);
        createFlatItemModel(model, ModItems.COOKED_CORN);
        createFlatItemModel(model, ModItems.POPCORN);
        // Snacks
        createFlatItemModel(model, ModItems.CHOCOLATE);
        createFlatItemModel(model, ModItems.NEW_YEAR_CAKE);
        createFlatItemModel(model, ModItems.WALNUT_SHORT_BREAD);
        createFlatItemModel(model, ModItems.GREEN_RICE_BALLS);
        createFlatItemModel(model, ModItems.STICKY_RICE_CAKE);
        createFlatItemModel(model, ModItems.CHEESE);
        createFlatItemModel(model, ModItems.STEAMED_BUNS);
        createFlatItemModel(model, ModItems.SAUERKRAUT_CAKE);
        createFlatItemModel(model, ModItems.FRIED_NEW_YEAR_CAKE);
        createFlatItemModel(model, ModItems.FRIED_BREAD);
        createFlatItemModel(model, ModItems.HOT_DOG);
        createFlatItemModel(model, ModItems.SMILEY_COOKIE);
        createFlatItemModel(model, ModItems.BISCUIT);
        // 果酱
        createFlatItemModel(model, ModItems.COCONUT_JAM);
        createFlatItemModel(model, ModItems.GOLDEN_GRAPES_JAM);
        createFlatItemModel(model, ModItems.GRAPES_JAM);
        createFlatItemModel(model, ModItems.LEMON_JAM);
        createFlatItemModel(model, ModItems.ORANGE_JAM);
        createFlatItemModel(model, ModItems.PEACH_JAM);
        createFlatItemModel(model, ModItems.PEAR_JAM);
        createFlatItemModel(model, ModItems.STRAWBERRY_JAM);
        // 饼干
        createFlatItemModel(model, ModItems.COCONUT_BISCUIT);
        createFlatItemModel(model, ModItems.GOLDEN_GRAPES_BISCUIT);
        createFlatItemModel(model, ModItems.GRAPES_BISCUIT);
        createFlatItemModel(model, ModItems.LEMON_BISCUIT);
        createFlatItemModel(model, ModItems.ORANGE_BISCUIT);
        createFlatItemModel(model, ModItems.PEACH_BISCUIT);
        createFlatItemModel(model, ModItems.PEAR_BISCUIT);
        createFlatItemModel(model, ModItems.STRAWBERRY_BISCUIT);
        // 果汁
        generateJuice(model, ModItems.JUICE);
        createFlatItemModel(model, ModItems.JUICE_BOTTLE);
        // 其他
        createFlatItemModel(model, ModItems.RAW_MUTTON);
        createFlatItemModel(model, ModItems.COOKED_MUTTON);
        createFlatItemModel(model, ModItems.CHINESE_BOWL);
        createFlatItemModel(model, ModItems.PIXIAN_WATERCRESS);
        createFlatItemModel(model, ModItems.DARK_BLUE_STONE);
        createFlatItemModel(model, ModItems.DARK_GREEN_STONE);
        createFlatItemModel(model, ModItems.ULTIMATE_ETERNAL_STONE);
        model.generateFlatItem(ModItems.DARK_GREEN_MULTIFUNCTION_TOOLS, ModelTemplates.FLAT_HANDHELD_ITEM);
        model.generateFlatItem(ModItems.DARK_BLUE_MULTIFUNCTION_TOOLS, ModelTemplates.FLAT_HANDHELD_ITEM);

        createFlatItemModel(model, ModItems.PEANUT_OIL_BUCKET);
    }

    /**
     * <p>果酱颜色图层及模型</p>
     * @see ItemModelGenerators#generatePotion(Item item)
     */
    public final void generateJuice(ItemModelGenerators model, Item item) {
        Identifier layeredItem = model.generateLayeredItem(item,
                new Material(FoodCraft.of("item/juice_overlay")),
                new Material(ModelLocationUtils.getModelLocation(item)));
        model.itemModelOutput.accept(item, ItemModelUtils.tintedModel(layeredItem, new JuiceColor()));
    }

    /**
     * <p>创建物品模型及材质</p>
     */
    public final void createFlatItemModel(ItemModelGenerators model, Item item) {
        model.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
    }

    /**
     * <p>创建稻米机械模型</p>
     * {@link TextureMapping#getBlockTexture(Block String)}之前返回值为Identifier类，现在返回值Material类
     * @since 26.1-snapshot-6
     */
    public final void createRiceMachine(BlockModelGenerators model, Block machine, TexturedModel.Provider provider) {
        Material frontTexture = TextureMapping.getBlockTexture(machine, "_front_on");
        Material topTexture = TextureMapping.getBlockTexture(machine, "_top_on");
        MultiVariant normalModel = BlockModelGenerators.plainVariant(provider.create(machine, model.modelOutput));
        MultiVariant litModel = BlockModelGenerators.plainVariant(provider.get(machine)
                .updateTextures(mapping -> mapping.put(TextureSlot.FRONT, frontTexture))
                .updateTextures(mapping -> mapping.put(TextureSlot.TOP, topTexture))
                .createWithSuffix(machine, "_on", model.modelOutput));
        model.blockStateOutput.accept(MultiVariantGenerator.dispatch(machine)
                .with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.LIT, litModel, normalModel))
                .with(ROTATION_HORIZONTAL_FACING));
    }

    public final void createStove(BlockModelGenerators model) {
        Block block = ModBlocks.STOVE;
        Material downTexture = TextureMapping.getBlockTexture(block, "_down");
        Material topTexture = TextureMapping.getBlockTexture(block, "_top");
        Material sideTexture = TextureMapping.getBlockTexture(block, "_side");
        MultiVariant stoveModel = BlockModelGenerators.plainVariant(TexturedModel.CUBE_TOP_BOTTOM
                        .updateTexture(mapping -> mapping.put(TextureSlot.TOP, topTexture))
                        .updateTexture(mapping -> mapping.put(TextureSlot.BOTTOM, downTexture))
                        .updateTexture(mapping -> mapping.put(TextureSlot.SIDE, sideTexture))
                        .create(block, model.modelOutput));
        model.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, stoveModel));
    }

    public final void createBrewBarrel(BlockModelGenerators model) {
        Block block = ModBlocks.BREW_BARREL;
        Material endTexture = TextureMapping.getBlockTexture(block, "_top");
        Material sideTexture = TextureMapping.getBlockTexture(block, "_side");
        MultiVariant brewBarrelModel = BlockModelGenerators.plainVariant(TexturedModel.COLUMN
                .updateTexture(mapping -> mapping.put(TextureSlot.END, endTexture))
                .updateTexture(mapping -> mapping.put(TextureSlot.SIDE, sideTexture))
                .create(block, model.modelOutput));
        model.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, brewBarrelModel));
    }

    /**
     * <p>创建树叶模型</p>
     */
    public final void createLeaves(BlockModelGenerators model, Block block) {
        Identifier blockModel = TexturedModel.LEAVES.create(block, model.modelOutput);
        model.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, BlockModelGenerators.plainVariant(blockModel)));
    }

    /**
     * <p>创建蛋糕模型</p>
     */
    private void createCakeBlock(BlockModelGenerators model, Block block) {
        model.registerSimpleFlatItemModel(block.asItem());
        Material topTexture = TextureMapping.getBlockTexture(block, "_top");
        Material sideTexture = TextureMapping.getBlockTexture(block, "_side");
        model.blockStateOutput.accept(MultiVariantGenerator.dispatch(block).with(PropertyDispatch.initial(BlockStateProperties.BITES)
                .select(0, BlockModelGenerators.plainVariant(FCTexturedModel.CAKE
                        .updateTexture(mapping -> mapping.put(TextureSlot.TOP, topTexture))
                        .updateTexture(mapping -> mapping.put(TextureSlot.SIDE, sideTexture))
                        .create(block, model.modelOutput)))
                .select(1, BlockModelGenerators.plainVariant(FCTexturedModel.CAKE_SLICE1
                        .updateTexture(mapping -> mapping.put(TextureSlot.TOP, topTexture))
                        .updateTexture(mapping -> mapping.put(TextureSlot.SIDE, sideTexture))
                        .createWithSuffix(block, "_slice1", model.modelOutput)))
                .select(2, BlockModelGenerators.plainVariant(FCTexturedModel.CAKE_SLICE2
                        .updateTexture(mapping -> mapping.put(TextureSlot.TOP, topTexture))
                        .updateTexture(mapping -> mapping.put(TextureSlot.SIDE, sideTexture))
                        .createWithSuffix(block, "_slice2", model.modelOutput)))
                .select(3, BlockModelGenerators.plainVariant(FCTexturedModel.CAKE_SLICE3
                        .updateTexture(mapping -> mapping.put(TextureSlot.TOP, topTexture))
                        .updateTexture(mapping -> mapping.put(TextureSlot.SIDE, sideTexture))
                        .createWithSuffix(block, "_slice3", model.modelOutput)))
                .select(4, BlockModelGenerators.plainVariant(FCTexturedModel.CAKE_SLICE4
                        .updateTexture(mapping -> mapping.put(TextureSlot.TOP, topTexture))
                        .updateTexture(mapping -> mapping.put(TextureSlot.SIDE, sideTexture))
                        .createWithSuffix(block, "_slice4", model.modelOutput)))
                .select(5, BlockModelGenerators.plainVariant(FCTexturedModel.CAKE_SLICE5
                        .updateTexture(mapping -> mapping.put(TextureSlot.TOP, topTexture))
                        .updateTexture(mapping -> mapping.put(TextureSlot.SIDE, sideTexture))
                        .createWithSuffix(block, "_slice5", model.modelOutput)))
                .select(6, BlockModelGenerators.plainVariant(FCTexturedModel.CAKE_SLICE6
                        .updateTexture(mapping -> mapping.put(TextureSlot.TOP, topTexture))
                        .updateTexture(mapping -> mapping.put(TextureSlot.SIDE, sideTexture))
                        .createWithSuffix(block, "_slice6", model.modelOutput)))
        ));
    }

    /**
     * <p>创建树苗模型</p>
     */
    public final void createSapling(BlockModelGenerators model, Block standAlone, BlockModelGenerators.PlantType plantType) {
        model.registerSimpleItemModel(standAlone.asItem(), plantType.createItemModel(model, standAlone));
        model.createCrossBlock(standAlone, plantType);
    }

    /**
     * <p>创建作物模型</p>
     * @see BlockModelGenerators#createCropBlock(Block, Property, int...)
     */
    public final void createCropBlock(BlockModelGenerators model, Block block) {
        model.createCropBlock(block, BlockStateProperties.AGE_4, 0, 1, 2, 3, 4);
    }

    public final void createChoppingBroad(BlockModelGenerators model) {
        MultiVariant plainVariant = BlockModelGenerators.plainVariant(TexturedModel.ORIENTABLE_ONLY_TOP.create(ModBlocks.CHOPPING_BOARD, model.modelOutput));
        model.blockStateOutput.accept(MultiVariantGenerator.dispatch(ModBlocks.CHOPPING_BOARD, plainVariant));
    }
}

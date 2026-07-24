package com.crystal.foodcraft.block;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.block.crop.*;
import com.crystal.foodcraft.block.machine.*;
import com.crystal.foodcraft.register.ModFluids;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Function;

public class ModBlocks {
    public static final Block RICE_MACHINE_SHELL = register("rice_machine_shell", Properties.ofFullCopy(Blocks.IRON_BLOCK));
    public static final Block MILL = register("mill", MillBlock::new, Properties.ofFullCopy(Blocks.IRON_BLOCK));
    public static final Block FRYING_MACHINE = register("frying_machine", FryingMachineBlock::new, Properties.ofFullCopy(Blocks.IRON_BLOCK));
    public static final Block BEVERAGE_MAKING_MACHINE = register("beverage_making_machine", BeverageMakingMachine::new, Properties.ofFullCopy(Blocks.IRON_BLOCK));
    public static final Block BREW_BARREL = register("brew_barrel", BrewBarrel::new, Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final Block STOVE = register("stove", StoveBlock::new, Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final Block CHOPPING_BOARD = register("chopping_board", ChoppingBoard::new, Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final Block PAN = register("pan", PanBlock::new, Properties.ofFullCopy(Blocks.IRON_BLOCK).noOcclusion());
    public static final Block POT = register("pot", PotBlock::new, Properties.ofFullCopy(Blocks.IRON_BLOCK).noOcclusion());
    public static final Block PRESSURE_COOKER = register("pressure_cooker", PressureCookerBlock::new, Properties.ofFullCopy(Blocks.IRON_BLOCK).noOcclusion());
    public static final Block MILLED_RICE = register("milled_rice_block", Properties.ofFullCopy(Blocks.HAY_BLOCK));
    public static final Block SALT = register("salt_block", Properties.ofFullCopy(Blocks.HAY_BLOCK));
    public static final Block COCONUT = register("coconut_block", Properties.ofFullCopy(Blocks.HAY_BLOCK));
    public static final Block BANANA = register("banana_block", Properties.ofFullCopy(Blocks.HAY_BLOCK));
    public static final Block SUGAR = register("sugar_block", Properties.ofFullCopy(Blocks.HAY_BLOCK));
    public static final Block STICKY_RICE_BLOCK = register("sticky_rice_block", Properties.ofFullCopy(Blocks.HAY_BLOCK));
    public static final Block CHOCOLATE = register("chocolate_block", Properties.ofFullCopy(Blocks.HAY_BLOCK));
    public static final Block CARROT_BLOCK = register("carrot_block", Properties.ofFullCopy(Blocks.HAY_BLOCK));
    public static final Block POTATO_BLOCK = register("potato_block", Properties.ofFullCopy(Blocks.HAY_BLOCK));
    public static final Block WATERCRESS_BLOCK = register("watercress_block", Properties.ofFullCopy(Blocks.HAY_BLOCK));
    public static final Block PEANUT_BLOCK = register("peanut_block", Properties.ofFullCopy(Blocks.HAY_BLOCK));
    public static final Block BEAN_BLOCK = register("bean_block", Properties.ofFullCopy(Blocks.HAY_BLOCK));
    // 蛋糕
    public static final Block STRAWBERRY_CAKE = register("strawberry_cake", FCCakeBlock::new, Properties.ofFullCopy(Blocks.CAKE));
    public static final Block GRAPES_CAKE = register("grapes_cake", FCCakeBlock::new, Properties.ofFullCopy(Blocks.CAKE));
    public static final Block GOLDEN_GRAPES_CAKE = register("golden_grapes_cake", FCCakeBlock::new, Properties.ofFullCopy(Blocks.CAKE));
    public static final Block PEAR_CAKE = register("pear_cake", FCCakeBlock::new, Properties.ofFullCopy(Blocks.CAKE));
    public static final Block PEACH_CAKE = register("peach_cake", FCCakeBlock::new, Properties.ofFullCopy(Blocks.CAKE));
    public static final Block ORANGE_CAKE = register("orange_cake", FCCakeBlock::new, Properties.ofFullCopy(Blocks.CAKE));
    public static final Block LEMON_CAKE = register("lemon_cake", FCCakeBlock::new, Properties.ofFullCopy(Blocks.CAKE));
    public static final Block COCONUT_CAKE = register("coconut_cake", FCCakeBlock::new, Properties.ofFullCopy(Blocks.CAKE));
    /* 树叶 */
    public static final Block LEAVES = register("leaves", properties -> new TintedParticleLeavesBlock(0, properties), leavesProperties());
    public static final Block PEAR_LEAVES = register("pear_leaves", properties -> new TintedParticleLeavesBlock(0, properties), leavesProperties());
    public static final Block LYCHEE_LEAVES = register("lychee_leaves", properties -> new TintedParticleLeavesBlock(0, properties), leavesProperties());
    public static final Block PEACH_LEAVES = register("peach_leaves", properties -> new TintedParticleLeavesBlock(0, properties), leavesProperties());
    public static final Block ORANGE_LEAVES = register("orange_leaves", properties -> new TintedParticleLeavesBlock(0, properties), leavesProperties());
    public static final Block LOQUAT_LEAVES = register("loquat_leaves", properties -> new TintedParticleLeavesBlock(0, properties), leavesProperties());
    public static final Block MANGO_LEAVES = register("mango_leaves", properties -> new TintedParticleLeavesBlock(0, properties), leavesProperties());
    public static final Block LEMON_LEAVES = register("lemon_leaves", properties -> new TintedParticleLeavesBlock(0, properties), leavesProperties());
    public static final Block GRAPEFRUIT_LEAVES = register("grapefruit_leaves", properties -> new TintedParticleLeavesBlock(0, properties), leavesProperties());
    public static final Block PERSIMMON_LEAVES = register("persimmon_leaves", properties -> new TintedParticleLeavesBlock(0, properties), leavesProperties());
    public static final Block PAPAYA_LEAVES = register("papaya_leaves", properties -> new TintedParticleLeavesBlock(0, properties), leavesProperties());
    public static final Block HAWTHORN_LEAVES = register("hawthorn_leaves", properties -> new TintedParticleLeavesBlock(0, properties), leavesProperties());
    public static final Block LONGYAN_LEAVES = register("longyan_leaves", properties -> new TintedParticleLeavesBlock(0, properties), leavesProperties());
    public static final Block POMEGRANATE_LEAVES = register("pomegranate_leaves", properties -> new TintedParticleLeavesBlock(0, properties), leavesProperties());
    public static final Block CHINESE_DATE_LEAVES = register("chinese_date_leaves", properties -> new TintedParticleLeavesBlock(0, properties), leavesProperties());
    public static final Block CHERRY_LEAVES = register("cherry_leaves", properties -> new TintedParticleLeavesBlock(0, properties), leavesProperties());
    /**
     * @see Blocks#JUNGLE_LEAVES
     */
    public static final Block JUNGLE_LEAVES = register("big_jungle_leaves", properties -> new BigLeavesBlock(0.01F, properties), leavesProperties());
    // 树苗
    public static final Block PEAR_SAPLING = sapling("pear_sapling", properties -> new SaplingBlock(ModTreeGrower.PEAR, properties));
    public static final Block LYCHEE_SAPLING = sapling("lychee_sapling", properties -> new SaplingBlock(ModTreeGrower.LYCHEE, properties));
    public static final Block PEACH_SAPLING = sapling("peach_sapling", properties -> new SaplingBlock(ModTreeGrower.PEACH, properties));
    public static final Block ORANGE_SAPLING = sapling("orange_sapling", properties -> new SaplingBlock(ModTreeGrower.ORANGE, properties));
    public static final Block LOQUAT_SAPLING = sapling("loquat_sapling", properties -> new SaplingBlock(ModTreeGrower.LOQUAT, properties));
    public static final Block MANGO_SAPLING = sapling("mango_sapling", properties -> new SaplingBlock(ModTreeGrower.MANGO, properties));
    public static final Block LEMON_SAPLING = sapling("lemon_sapling", properties -> new SaplingBlock(ModTreeGrower.LEMON, properties));
    public static final Block GRAPEFRUIT_SAPLING = sapling("grapefruit_sapling", properties -> new SaplingBlock(ModTreeGrower.GRAPEFRUIT, properties));
    public static final Block PERSIMMON_SAPLING = sapling("persimmon_sapling", properties -> new SaplingBlock(ModTreeGrower.PERSIMMON, properties));
    public static final Block PAPAYA_SAPLING = sapling("papaya_sapling", properties -> new SaplingBlock(ModTreeGrower.PAPAYA, properties));
    public static final Block HAWTHORN_SAPLING = sapling("hawthorn_sapling", properties -> new SaplingBlock(ModTreeGrower.HAWTHORN, properties));
    public static final Block LONGYAN_SAPLING = sapling("longyan_sapling", properties -> new SaplingBlock(ModTreeGrower.LONGYAN, properties));
    public static final Block POMEGRANATE_SAPLING = sapling("pomegranate_sapling", properties -> new SaplingBlock(ModTreeGrower.POMEGRANATE, properties));
    public static final Block CHINESE_DATE_SAPLING = sapling("chinese_date_sapling", properties -> new SaplingBlock(ModTreeGrower.CHINSES_DATE, properties));
    public static final Block CHERRY_SAPLING = sapling("cherry_sapling", properties -> new SaplingBlock(ModTreeGrower.CHERRY, properties));
    public static final Block BANANA_SAPLING = sapling("banana_sapling", properties -> new SaplingBlock(ModTreeGrower.BANANA, properties));
    public static final Block COCONUT_SAPLING = sapling("coconut_sapling", properties -> new SaplingBlock(ModTreeGrower.COCONUT, properties));
    // 作物
    public static final Block WHITE_RADDISH = crop("white_raddish", WhiteRaddishCrop::new);
    public static final Block STRAWBERRY = crop("strawberry", StrawBerryCrop::new);
    public static final Block BEANS = crop("beans", BeanCrop::new);
    public static final Block TOMATO = crop("tomato", TomatoCrop::new);
    public static final Block RED_BEAN = crop("red_bean", RedBeanCrop::new);
    public static final Block SWEET_POTATO = crop("sweet_potato", SweetPotatoCrop::new);
    public static final Block CUCUMBER = crop("cucumber", CucumberCrop::new);
    public static final Block PEANUT = crop("peanut", PeanutCrop::new);
    public static final Block CHILI = crop("chili", ChiliCrop::new);
    public static final Block GREEN_BEANS = crop("green_bean", GreenBeanCrop::new);
    public static final Block STICKY_RICE = crop("sticky_rice", StickyRiceCrop::new);
    public static final Block GRAPES = crop("grapes", GrapeCrop::new);
    public static final Block EGGPLANT = crop("eggplant", EggplantCrop::new);
    public static final Block GREEN_PEPPER = crop("green_pepper", GreenPepperCrop::new);
    public static final Block VEGETABLE = crop("vegetable", VegetableCrop::new);
    public static final Block RICE = crop("rice", RiceCrop::new);
    public static final Block CORN = crop("corn", CornCrop::new);
    // 液体
    public static final Block COOKING_OIL = registerWithoutBlockItem("cooking_oil", properties -> new LiquidBlock(ModFluids.COOKING_OIL_STILL, properties), Properties.ofFullCopy(Blocks.WATER));

    private static Block crop(String name, Function<Properties, Block> factory) {
        return registerWithoutBlockItem(name, factory, Properties.ofFullCopy(Blocks.CARROTS));
    }

    private static Block sapling(String name, Function<Properties, Block> factory) {
        return register(name, factory, Properties.ofFullCopy(Blocks.OAK_SAPLING));
    }

    private static Block register(String name, Properties properties) {
        return register(name, Block::new, properties);
    }

    private static Block registerWithoutBlockItem(String name, Function<Properties, Block> factory, Properties properties) {
        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, FoodCraft.of(name));
        return Registry.register(BuiltInRegistries.BLOCK, blockKey, factory.apply(properties.setId(blockKey)));
    }

    private static Block register(String name, Function<Properties, Block> factory, Properties properties) {
        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, FoodCraft.of(name));
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, FoodCraft.of(name));
        Block block = factory.apply(properties.setId(blockKey));
        BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
        Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    private static Properties leavesProperties() {
        return Properties.of()
                .mapColor(MapColor.PLANT)
                .strength(0.2F)
                .randomTicks()
                .sound(SoundType.GRASS)
                .noOcclusion()
                .isSuffocating(Blocks::never)
                .isViewBlocking(Blocks::never)
                .ignitedByLava()
                .pushReaction(PushReaction.DESTROY)
                .isRedstoneConductor(Blocks::never);
    }

    public static void init() {

    }
}

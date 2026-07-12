package com.crystal.foodcraft.datagen;

import com.crystal.foodcraft.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.concurrent.CompletableFuture;

/**
 * @see net.minecraft.data.loot.packs.VanillaBlockLoot VanillaBlockLoot
 */
public class ModBlockLootDataGeneration extends FabricBlockLootSubProvider {
    protected static final float[] NORMAL_FRUIT_CHANCES = new float[]{0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F};

    public ModBlockLootDataGeneration(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate() {
        this.add(Blocks.SHORT_GRASS, createGrassDrops());
        this.add(Blocks.OAK_LEAVES, createOakLeavesDrops());
        this.add(Blocks.JUNGLE_LEAVES, createJungleLeavesDrops());
        this.add(Blocks.ACACIA_LEAVES, createAcaciaLeavesDrop());
        this.add(Blocks.CHERRY_LEAVES, createCherryLeavesDrop());
    }

    public LootTable.Builder createGrassDrops() {
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        // 奖励条目
        AlternativesEntry.Builder entry = AlternativesEntry.alternatives(
                // 玉米
                LootItem.lootTableItem(Items.WHEAT_SEEDS)
                        .when(LootItemRandomChanceCondition.randomChance(0.33F)),
                // 黄瓜
                LootItem.lootTableItem(ModItems.CUCUMBER_SEEDS)
                        .when(LootItemRandomChanceCondition.randomChance(0.33F)),
                // 茄子
                LootItem.lootTableItem(ModItems.EGGPLANT_SEEDS)
                        .when(LootItemRandomChanceCondition.randomChance(0.33F)),
                // 朝天椒
                LootItem.lootTableItem(ModItems.CHILI)
                        .when(LootItemRandomChanceCondition.randomChance(0.33F)),
                // 青椒
                LootItem.lootTableItem(ModItems.PEPPER_SEEDS)
                        .when(LootItemRandomChanceCondition.randomChance(0.33F)),
                // 花生
                LootItem.lootTableItem(ModItems.PEANUT)
                        .when(LootItemRandomChanceCondition.randomChance(0.33F)),
                // 水稻
                LootItem.lootTableItem(ModItems.RICE)
                        .when(LootItemRandomChanceCondition.randomChance(0.33F)),
                // 檽米
                LootItem.lootTableItem(ModItems.STICKY_RICE)
                        .when(LootItemRandomChanceCondition.randomChance(0.33F)),
                // 红薯
                LootItem.lootTableItem(ModItems.SWEET_POTATO)
                        .when(LootItemRandomChanceCondition.randomChance(0.33F)),
                // 番茄
                LootItem.lootTableItem(ModItems.TOMATO_SEEDS)
                        .when(LootItemRandomChanceCondition.randomChance(0.33F)),
                // 白萝卜
                LootItem.lootTableItem(ModItems.WHITE_RADDISH)
                        .when(LootItemRandomChanceCondition.randomChance(0.33F)),
                // 白菜
                LootItem.lootTableItem(ModItems.VEGETABLE)
                        .when(LootItemRandomChanceCondition.randomChance(0.33F)),
                // 草莓
                LootItem.lootTableItem(ModItems.STRAWBERRY)
                        .when(LootItemRandomChanceCondition.randomChance(0.33F)),
                // 绿豆
                LootItem.lootTableItem(ModItems.GREEN_BEAN)
                        .when(LootItemRandomChanceCondition.randomChance(0.33F)),
                // 黄豆
                LootItem.lootTableItem(ModItems.LONG_BEAN)
                        .when(LootItemRandomChanceCondition.randomChance(0.33F)),
                // 红豆
                LootItem.lootTableItem(ModItems.RED_BEAN)
                        .when(LootItemRandomChanceCondition.randomChance(0.33F)),
                // 大葱
                LootItem.lootTableItem(ModItems.SCALLION)
                        .when(LootItemRandomChanceCondition.randomChance(0.33F)),

                // 其他0.23概率
                LootItem.lootTableItem(ModItems.CORN).when(LootItemRandomChanceCondition.randomChance(0.23f)),
                LootItem.lootTableItem(ModItems.CUCUMBER).when(LootItemRandomChanceCondition.randomChance(0.23f)),
                LootItem.lootTableItem(ModItems.EGGPLANT).when(LootItemRandomChanceCondition.randomChance(0.23f)),
                LootItem.lootTableItem(ModItems.CHILI).when(LootItemRandomChanceCondition.randomChance(0.23f)),
                LootItem.lootTableItem(ModItems.GREEN_PEPPER).when(LootItemRandomChanceCondition.randomChance(0.23f)),
                LootItem.lootTableItem(ModItems.PEANUT).when(LootItemRandomChanceCondition.randomChance(0.23f)),
                LootItem.lootTableItem(ModItems.RICE).when(LootItemRandomChanceCondition.randomChance(0.23f)),
                LootItem.lootTableItem(ModItems.STICKY_RICE).when(LootItemRandomChanceCondition.randomChance(0.23f)),
                LootItem.lootTableItem(ModItems.SWEET_POTATO).when(LootItemRandomChanceCondition.randomChance(0.23f)),
                LootItem.lootTableItem(ModItems.TOMATO).when(LootItemRandomChanceCondition.randomChance(0.23f)),
                LootItem.lootTableItem(ModItems.GRAPES).when(LootItemRandomChanceCondition.randomChance(0.23f)),
                LootItem.lootTableItem(ModItems.WHITE_RADDISH).when(LootItemRandomChanceCondition.randomChance(0.23f)),
                LootItem.lootTableItem(ModItems.VEGETABLE).when(LootItemRandomChanceCondition.randomChance(0.23f)),
                LootItem.lootTableItem(ModItems.STRAWBERRY).when(LootItemRandomChanceCondition.randomChance(0.23f)),
                LootItem.lootTableItem(ModItems.BEANS).when(LootItemRandomChanceCondition.randomChance(0.23f)),
                LootItem.lootTableItem(ModItems.LONGYAN).when(LootItemRandomChanceCondition.randomChance(0.23f)),
                LootItem.lootTableItem(ModItems.RED_BEAN).when(LootItemRandomChanceCondition.randomChance(0.23f))
        );

        return this.createShearsDispatchTable(Blocks.SHORT_GRASS, entry)
                // 掉落物受时运附魔影响
                .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE), 2));
    }

    /**
     * <p>丛林木 (Jungle Tree) 掉落水果</p>
     * <p>包含: 椰子(COCONUT), 香蕉(BANANA), 龙眼(LONGAN), 芒果(MANGO), 荔枝(LITCHI)</p>
     */
    public LootTable.Builder createJungleLeavesDrops() {
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        AlternativesEntry.Builder entry = AlternativesEntry.alternatives(
                // 椰子
                LootItem.lootTableItem(ModItems.COCONUT)
                        .when(LootItemRandomChanceCondition.randomChance(0.33F)),
                // 香蕉
                LootItem.lootTableItem(ModItems.BANANA)
                        .when(LootItemRandomChanceCondition.randomChance(0.33F)),
                // 龙眼
                LootItem.lootTableItem(ModItems.LONGYAN)
                        .when(LootItemRandomChanceCondition.randomChance(0.33F)),
                // 芒果
                LootItem.lootTableItem(ModItems.MANGO)
                        .when(LootItemRandomChanceCondition.randomChance(0.33F)),
                // 荔枝
                LootItem.lootTableItem(ModItems.LYCHEE)
                        .when(LootItemRandomChanceCondition.randomChance(0.33F))
        );
        return this.createLeavesDrops(Blocks.JUNGLE_LEAVES, Blocks.JUNGLE_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES)
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .when(this.doesNotHaveShearsOrSilkTouch())
                        .add(this.applyExplosionCondition(Blocks.JUNGLE_LEAVES, entry)
                                // 掉落物受时运附魔影响
                                .when(BonusLevelTableCondition.bonusLevelFlatChance(enchantments.getOrThrow(Enchantments.FORTUNE), NORMAL_FRUIT_CHANCES))));
    }

    /**
     * <p>橡木 (Oak Tree) 掉落水果</p>
     * <p>包含: 葡萄柚(GRAPEFRUIT), 桃子(PEACH), 柿子(PERSIMMON), 山楂(HAWTHORN), 枇杷(LOQUAT), 柠檬(LEMON), 梨(PEAR), 橙子(ORANGE)</p>
     */
    public LootTable.Builder createOakLeavesDrops() {
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        AlternativesEntry.Builder entry = AlternativesEntry.alternatives(
                // 苹果
                LootItem.lootTableItem(Items.APPLE)
                        .when(LootItemRandomChanceCondition.randomChance(0.33F)),
                // 柚子
                LootItem.lootTableItem(ModItems.GRAPEFRUIT)
                        .when(LootItemRandomChanceCondition.randomChance(0.33F)),
                // 桃子
                LootItem.lootTableItem(ModItems.PEACH)
                        .when(LootItemRandomChanceCondition.randomChance(0.33F)),
                // 柿子
                LootItem.lootTableItem(ModItems.PERSIMMON)
                        .when(LootItemRandomChanceCondition.randomChance(0.33F)),
                // 山楂
                LootItem.lootTableItem(ModItems.HAWTHORN)
                        .when(LootItemRandomChanceCondition.randomChance(0.33F)),
                // 枇杷
                LootItem.lootTableItem(ModItems.LOQUAT)
                        .when(LootItemRandomChanceCondition.randomChance(0.33F)),
                // 柠檬
                LootItem.lootTableItem(ModItems.LONGYAN)
                        .when(LootItemRandomChanceCondition.randomChance(0.33F)),
                // 梨子
                LootItem.lootTableItem(ModItems.PEAR)
                        .when(LootItemRandomChanceCondition.randomChance(0.33F)),
                // 橙子
                LootItem.lootTableItem(ModItems.ORANGE)
                        .when(LootItemRandomChanceCondition.randomChance(0.33F))
        );
        return this.createLeavesDrops(Blocks.OAK_LEAVES, Blocks.OAK_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES)
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .when(this.doesNotHaveShearsOrSilkTouch())
                        .add(this.applyExplosionCondition(Blocks.OAK_LEAVES, entry)
                                // 掉落物受时运附魔影响
                                .when(BonusLevelTableCondition.bonusLevelFlatChance(enchantments.getOrThrow(Enchantments.FORTUNE), NORMAL_FRUIT_CHANCES))));
    }

    /**
     * <p>合欢木 (Acacia Tree) 掉落水果</p>
     * <p>包含: 石榴(POMEGRANATE), 红枣(DATE)</p>
     */
    public LootTable.Builder createAcaciaLeavesDrop() {
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        AlternativesEntry.Builder entry = AlternativesEntry.alternatives(
                // 石榴
                LootItem.lootTableItem(ModItems.POMEGRANATE)
                        .when(LootItemRandomChanceCondition.randomChance(0.33F)),
                // 红枣
                LootItem.lootTableItem(ModItems.CHINESE_DATES)
                        .when(LootItemRandomChanceCondition.randomChance(0.33F))
        );
        return this.createLeavesDrops(Blocks.ACACIA_LEAVES, Blocks.ACACIA_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES)
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .when(this.doesNotHaveShearsOrSilkTouch())
                        .add(this.applyExplosionCondition(Blocks.ACACIA_LEAVES, entry)
                                // 掉落物受时运附魔影响
                                .when(BonusLevelTableCondition.bonusLevelFlatChance(enchantments.getOrThrow(Enchantments.FORTUNE), NORMAL_FRUIT_CHANCES))));
    }

    /**
     * <p>樱桃木 (Cherry Tree) 掉落水果</p>
     * <p>包含: 樱桃(CHERRY)</p>
     */
    public LootTable.Builder createCherryLeavesDrop() {
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        AlternativesEntry.Builder entry = AlternativesEntry.alternatives(
                // 樱桃
                LootItem.lootTableItem(ModItems.CHERRY)
                        .when(LootItemRandomChanceCondition.randomChance(0.33F))
        );
        return this.createLeavesDrops(Blocks.ACACIA_LEAVES, Blocks.ACACIA_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES)
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .when(this.doesNotHaveShearsOrSilkTouch())
                        .add(this.applyExplosionCondition(Blocks.ACACIA_LEAVES, entry)
                                // 掉落物受时运附魔影响
                                .when(BonusLevelTableCondition.bonusLevelFlatChance(enchantments.getOrThrow(Enchantments.FORTUNE), NORMAL_FRUIT_CHANCES))));
    }
}

package com.crystal.foodcraft.world.features.config;

import com.crystal.foodcraft.world.features.placer.FruitPlacer;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.FeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;

import java.util.List;
import java.util.Optional;

/**
 * @see net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration TreeConfiguration
 */
public class FruitTreeConfiguration implements FeatureConfiguration {
    public static final Codec<FruitTreeConfiguration> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BlockStateProvider.CODEC.fieldOf("trunk_provider").forGetter(c -> c.trunkProvider),
            TrunkPlacer.CODEC.fieldOf("trunk_placer").forGetter(c -> c.trunkPlacer),
            BlockStateProvider.CODEC.fieldOf("foliage_provider").forGetter(c -> c.foliageProvider),
            FoliagePlacer.CODEC.fieldOf("foliage_placer").forGetter(c -> c.foliagePlacer),
            BlockStateProvider.CODEC.fieldOf("fruit_provider").forGetter(c -> c.fruitProvider),
            FruitPlacer.CODEC.fieldOf("fruit_placer").forGetter(c -> c.fruitPlacer),
            RootPlacer.CODEC.optionalFieldOf("root_placer").forGetter(c -> c.rootPlacer),
            FeatureSize.CODEC.fieldOf("minimum_size").forGetter(c -> c.minimumSize),
            TreeDecorator.CODEC.listOf().fieldOf("decorators").forGetter(c -> c.decorators),
            Codec.BOOL.fieldOf("ignore_vines").orElse(false).forGetter(c -> c.ignoreVines),
            BlockStateProvider.CODEC.fieldOf("below_trunk_provider").orElse(TreeConfiguration.PLACE_BELOW_OVERWORLD_TRUNKS).forGetter(c -> c.belowTrunkProvider)
            )
            .apply(instance, FruitTreeConfiguration::new)
    );
    public final BlockStateProvider trunkProvider;
    public final TrunkPlacer trunkPlacer;
    public final BlockStateProvider foliageProvider;
    public final FoliagePlacer foliagePlacer;
    public final BlockStateProvider fruitProvider;
    public final FruitPlacer fruitPlacer;
    public final Optional<RootPlacer> rootPlacer;
    public final FeatureSize minimumSize;
    public final List<TreeDecorator> decorators;
    public final boolean ignoreVines;
    public final BlockStateProvider belowTrunkProvider;

    /**
     * @param trunkProvider 树干提供器
     * @param trunkPlacer 树干放置器
     * @param foliageProvider 树叶提供器
     * @param foliagePlacer 树叶放置器
     * @param fruitProvider 水果提供器
     * @param fruitPlacer 水果放置器
     * @param rootPlacer 树根放置器，一般为泥土方块
     * @param minimumSize 地物最小尺寸
     * @param decorators 树木装饰器
     * @param ignoreVines 是否忽略藤蔓
     * @param belowTrunkProvider 底部树干提供器
     */
    protected FruitTreeConfiguration(
            BlockStateProvider trunkProvider,
            TrunkPlacer trunkPlacer,
            BlockStateProvider foliageProvider,
            FoliagePlacer foliagePlacer,
            BlockStateProvider fruitProvider,
            FruitPlacer fruitPlacer,
            Optional<RootPlacer> rootPlacer,
            FeatureSize minimumSize,
            List<TreeDecorator> decorators,
            boolean ignoreVines,
            BlockStateProvider belowTrunkProvider
    ) {
        this.trunkProvider = trunkProvider;
        this.trunkPlacer = trunkPlacer;
        this.foliageProvider = foliageProvider;
        this.foliagePlacer = foliagePlacer;
        this.fruitProvider = fruitProvider;
        this.fruitPlacer = fruitPlacer;
        this.rootPlacer = rootPlacer;
        this.minimumSize = minimumSize;
        this.decorators = decorators;
        this.ignoreVines = ignoreVines;
        this.belowTrunkProvider = belowTrunkProvider;
    }
    
    public static class Builder {
        public final BlockStateProvider trunkProvider;
        private final TrunkPlacer trunkPlacer;
        public final BlockStateProvider foliageProvider;
        private final FoliagePlacer foliagePlacer;
        public final BlockStateProvider fruitProvider;
        private final FruitPlacer fruitPlacer;
        private final Optional<RootPlacer> rootPlacer;
        private final FeatureSize minimumSize;
        private List<TreeDecorator> decorators = ImmutableList.of();
        private boolean ignoreVines;
        private BlockStateProvider belowTrunkProvider;

        public Builder(
                BlockStateProvider trunkProvider,
                TrunkPlacer trunkPlacer,
                BlockStateProvider foliageProvider,
                FoliagePlacer foliagePlacer,
                BlockStateProvider fruitProvider,
                FruitPlacer fruitPlacer,
                Optional<RootPlacer> rootPlacer,
                FeatureSize minimumSize,
                BlockStateProvider belowTrunkProvider
        ) {
            this.trunkProvider = trunkProvider;
            this.trunkPlacer = trunkPlacer;
            this.foliageProvider = foliageProvider;
            this.foliagePlacer = foliagePlacer;
            this.fruitProvider = fruitProvider;
            this.fruitPlacer = fruitPlacer;
            this.rootPlacer = rootPlacer;
            this.minimumSize = minimumSize;
            this.belowTrunkProvider = belowTrunkProvider;
        }

        public Builder(
                BlockStateProvider trunkProvider,
                TrunkPlacer trunkPlacer,
                BlockStateProvider foliageProvider,
                FoliagePlacer foliagePlacer,
                BlockStateProvider fruitProvider,
                FruitPlacer fruitPlacer,
                FeatureSize minimumSize
        ) {
            this(
                    trunkProvider, 
                    trunkPlacer, 
                    foliageProvider, 
                    foliagePlacer, 
                    fruitProvider, 
                    fruitPlacer, 
                    Optional.empty(), 
                    minimumSize, 
                    TreeConfiguration.PLACE_BELOW_OVERWORLD_TRUNKS
            );
        }
        
        public Builder belowTrunkProvider(final BlockStateProvider belowTrunkProvider) {
            this.belowTrunkProvider = belowTrunkProvider;
            return this;
        }

        public Builder decorators(final List<TreeDecorator> decorators) {
            this.decorators = decorators;
            return this;
        }

        public Builder ignoreVines() {
            this.ignoreVines = true;
            return this;
        }

        public FruitTreeConfiguration build() {
            return new FruitTreeConfiguration(
                    this.trunkProvider,
                    this.trunkPlacer,
                    this.foliageProvider,
                    this.foliagePlacer,
                    this.fruitProvider,
                    this.fruitPlacer,
                    this.rootPlacer,
                    this.minimumSize,
                    this.decorators,
                    this.ignoreVines,
                    this.belowTrunkProvider
            );
        }
    }
}

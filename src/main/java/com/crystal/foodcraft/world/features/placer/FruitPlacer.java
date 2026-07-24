package com.crystal.foodcraft.world.features.placer;

import com.crystal.foodcraft.world.features.config.FruitTreeConfiguration;
import com.mojang.datafixers.Products.P1;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder.Instance;
import com.mojang.serialization.codecs.RecordCodecBuilder.Mu;
import com.crystal.foodcraft.register.ModRegistries;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;

public class FruitPlacer {
    public static final Codec<FruitPlacer> CODEC = ModRegistries.FRUIT_PLACER.byNameCodec().dispatch(FruitPlacer::type, FruitPlacerType::codec);
    public static final MapCodec<FruitPlacer> MAP_CODEC = RecordCodecBuilder.mapCodec(
            instance -> fruitPlacerPart(instance)
                    .apply(instance, FruitPlacer::new)
    );
    public final IntProvider offest;

    protected static <P extends FruitPlacer> P1<Mu<P>, IntProvider> fruitPlacerPart(Instance<P> instance) {
        return instance.group(
                IntProviders.codec(0, 16).fieldOf("offest").forGetter(placer -> placer.offest)
        );
    }

    public FruitPlacer(IntProvider offest) {
        this.offest = offest;
    }

    public FruitPlacerType<?> type() {
        return FruitPlacerType.FRUIT_PLACER;
    }

    public void createFruit(
            WorldGenLevel level,
            FruitPlacer.FruitSetter setter,
            RandomSource random,
            FruitTreeConfiguration config,
            int treeHeight,
            FoliagePlacer.FoliageAttachment foliageAttachment
    ) {
        // 一个附着点
        BlockPos origin = foliageAttachment.pos();
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        pos.setWithOffset(origin, 1, -2, 0);
        tryPlaceFruit(level, setter, random, config, pos);
        pos.setWithOffset(origin, -1, -2, 0);
        tryPlaceFruit(level, setter, random, config, pos);
        pos.setWithOffset(origin, 0, -2, 1);
        tryPlaceFruit(level, setter, random, config, pos);
        pos.setWithOffset(origin, 0, -2, -1);
        tryPlaceFruit(level, setter, random, config, pos);
    }

    public static boolean tryPlaceFruit(WorldGenLevel level, FruitSetter setter, RandomSource random, FruitTreeConfiguration config, BlockPos pos) {
        BlockState state = config.fruitProvider.getState(level, random, pos);
        // 写入状态
        setter.set(pos, state);
        return true;
    }

    public interface FruitSetter {

        void set(BlockPos pos, BlockState state);

        boolean isSet(BlockPos pos);
    }
}

package com.crystal.foodcraft.item.juice;

import com.crystal.foodcraft.register.ModRegistries;
import com.crystal.foodcraft.register.ModRegistryKeys;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.flag.FeatureElement;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @see net.minecraft.world.item.alchemy.Potion
 */
public class Juice implements FeatureElement {
    public static final Codec<Holder<Juice>> CODEC = ModRegistries.JUICE.holderByNameCodec();
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<Juice>> PACKET_CODEC = ByteBufCodecs.holderRegistry(ModRegistryKeys.JUICE);
    private final String name;
    private final int color;
    private final List<MobEffectInstance> effects;

    public Juice(String name, int color) {
        this(name, color, List.of());
    }

    public Juice(String name, int color, List<MobEffectInstance> effects) {
        this.name = name;
        this.color = color;
        this.effects = effects;
    }

    public String name() {
        return name;
    }

    public int color() {
        return color;
    }

    public List<MobEffectInstance> effects() {
        return effects;
    }

    public MobEffectInstance effect() {
        return this.effects.getFirst();
    }

    @NotNull
    @Override
    public FeatureFlagSet requiredFeatures() {
        return FeatureFlags.DEFAULT_FLAGS;
    }
}

package com.crystal.foodcraft.item.juice;

import com.crystal.foodcraft.register.ModDataComponents;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Optional;

public record JuiceContents(Optional<Holder<Juice>> juice, Optional<String> name, Optional<Integer> color, List<MobEffectInstance> effects) {
    private static final Codec<JuiceContents> FULL_CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Juice.CODEC.optionalFieldOf("juice").forGetter(JuiceContents::juice),
                    Codec.STRING.optionalFieldOf("name").forGetter(JuiceContents::name),
                    Codec.INT.optionalFieldOf("color").forGetter(JuiceContents::color),
                    MobEffectInstance.CODEC.listOf().optionalFieldOf("effects", List.of()).forGetter(JuiceContents::effects)
            ).apply(instance, JuiceContents::new)
    );
    public static final Codec<JuiceContents> CODEC = Codec.withAlternative(FULL_CODEC, Juice.CODEC, JuiceContents::new);
    public static final StreamCodec<RegistryFriendlyByteBuf, JuiceContents> PACKET_CODEC = StreamCodec.composite(
            Juice.PACKET_CODEC.apply(ByteBufCodecs::optional),
            JuiceContents::juice,
            ByteBufCodecs.STRING_UTF8.apply(ByteBufCodecs::optional),
            JuiceContents::name,
            ByteBufCodecs.INT.apply(ByteBufCodecs::optional),
            JuiceContents::color,
            MobEffectInstance.STREAM_CODEC.apply(ByteBufCodecs.list()),
            JuiceContents::effects,
            JuiceContents::new
    );

    public JuiceContents(Holder<Juice> juice) {
        this(Optional.of(juice), Optional.empty(), Optional.empty(), List.of());
    }

    public static ItemStack createItemStack(Item item, Holder<Juice> juice) {
        ItemStack itemStack = new ItemStack(item);
        itemStack.set(ModDataComponents.JUICE, new JuiceContents(juice));
        return itemStack;
    }

    public Component getName(String prefix) {
        String suffix = this.name.or(() -> this.juice.map(juice -> juice.value().name())).orElse("empty");
        return Component.translatable(prefix + suffix);
    }

    public int getColor(int defaultColor) {
        return this.color.isEmpty() ? getJuiceColor() : this.color.orElse(defaultColor);
    }

    public int getJuiceColor() {
        return this.juice.orElseThrow().value().color();
    }
}
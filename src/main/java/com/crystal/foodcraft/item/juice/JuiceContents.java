package com.crystal.foodcraft.item.juice;

import com.crystal.foodcraft.register.ModDataComponents;
import com.google.common.collect.Iterables;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.ConsumableListener;
import net.minecraft.world.item.component.TooltipProvider;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * @see PotionContents
 */
public record JuiceContents(Optional<Holder<Juice>> juice, Optional<String> name, Optional<Integer> color, List<MobEffectInstance> effects) implements ConsumableListener, TooltipProvider {
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

    public Iterable<MobEffectInstance> getAllEffects() {
        if (this.juice.isEmpty()) {
            return this.effects;
        } else {
            return this.effects.isEmpty() ? this.juice.get().value().effects() : Iterables.concat(this.juice.get().value().effects(), this.effects);
        }
    }

    public void forEachEffect(Consumer<MobEffectInstance> consumer, float durationScale) {
        if (this.juice.isPresent()) {
            for (MobEffectInstance effect : this.juice.get().value().effects()) {
                consumer.accept(effect.withScaledDuration(durationScale));
            }
        }

        for (MobEffectInstance effect : this.effects) {
            consumer.accept(effect.withScaledDuration(durationScale));
        }
    }

    public void applyToLivingEntity(LivingEntity entity, float durationScale) {
        if (entity.level() instanceof ServerLevel serverLevel) {
            Player player = entity instanceof Player playerEntity ? playerEntity : null;
            this.forEachEffect(effect -> {
                if (effect.getEffect().value().isInstantenous()) {
                    effect.getEffect().value().applyInstantenousEffect(serverLevel, player, player, entity, effect.getAmplifier(), 1.0);
                } else {
                    entity.addEffect(effect);
                }
            }, durationScale);
        }
    }

    @Override
    public void onConsume(@NotNull Level level, @NotNull LivingEntity user, ItemStack stack, @NotNull Consumable consumable) {
        this.applyToLivingEntity(user, stack.getOrDefault(DataComponents.POTION_DURATION_SCALE, 1.0F));
    }

    @Override
    public void addToTooltip(Item.TooltipContext context, @NotNull Consumer<Component> consumer, @NotNull TooltipFlag flag, DataComponentGetter components) {
        PotionContents.addPotionTooltip(this.getAllEffects(), consumer, components.getOrDefault(DataComponents.POTION_DURATION_SCALE, 1.0F), context.tickRate());
    }
}
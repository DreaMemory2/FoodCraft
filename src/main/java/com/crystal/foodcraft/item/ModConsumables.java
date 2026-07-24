package com.crystal.foodcraft.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import java.util.List;

public class ModConsumables {
    public static final Consumable GOLDEN_GRAPE_WINE = defaultDrink()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.SPEED, 3600, 0),
                                    new MobEffectInstance(MobEffects.STRENGTH, 3600, 0),
                                    new MobEffectInstance(MobEffects.HASTE, 3600, 0),
                                    new MobEffectInstance(MobEffects.INSTANT_HEALTH, 3600, 0),
                                    new MobEffectInstance(MobEffects.JUMP_BOOST, 3600, 0),
                                    new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 3600, 0)
                            )
                    )
            )
            .build();
    public static final Consumable GOLDEN_CIDER = defaultDrink()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.NIGHT_VISION, 3600, 1),
                                    new MobEffectInstance(MobEffects.ABSORPTION, 3600, 1),
                                    new MobEffectInstance(MobEffects.REGENERATION, 3600, 1),
                                    new MobEffectInstance(MobEffects.RESISTANCE, 3600, 1),
                                    new MobEffectInstance(MobEffects.WATER_BREATHING, 3600, 1),
                                    new MobEffectInstance(MobEffects.INVISIBILITY, 3600, 1)
                            )
                    )
            )
            .build();
    public static final Consumable GOLDEN_GRAPE_JUICE = defaultDrink()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.STRENGTH, 36000, 1),
                                    new MobEffectInstance(MobEffects.INSTANT_HEALTH, 36000, 1),
                                    new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 36000, 1)
                            )
                    )
            )
            .build();
    public static final Consumable GOLDEN_APPLE_JUICE = defaultDrink()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.ABSORPTION, 36000, 1),
                                    new MobEffectInstance(MobEffects.REGENERATION, 36000, 1),
                                    new MobEffectInstance(MobEffects.RESISTANCE, 36000, 1)
                            )
                    )
            )
            .build();

    public static Consumable.Builder defaultFood() {
        return Consumable.builder()
                .consumeSeconds(1.6F)
                .animation(ItemUseAnimation.EAT)
                .sound(SoundEvents.GENERIC_EAT)
                .hasConsumeParticles(true);
    }

    public static Consumable.Builder defaultDrink() {
        return Consumable.builder()
                .consumeSeconds(1.6F)
                .animation(ItemUseAnimation.DRINK)
                .sound(SoundEvents.GENERIC_DRINK)
                .hasConsumeParticles(true);
    }
}

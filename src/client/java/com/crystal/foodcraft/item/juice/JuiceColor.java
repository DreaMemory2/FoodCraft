package com.crystal.foodcraft.item.juice;

import com.crystal.foodcraft.register.ModDataComponents;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.ARGB;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

public record JuiceColor(int defaultColor) implements ItemTintSource {
    public static final MapCodec<JuiceColor> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            ExtraCodecs.RGB_COLOR_CODEC.fieldOf("default").forGetter(JuiceColor::defaultColor)
    ).apply(instance, JuiceColor::new));

    public JuiceColor() {
        this(-13083194);
    }

    @Override
    public int calculate(ItemStack itemStack, @Nullable ClientLevel level, @Nullable LivingEntity owner) {
        JuiceContents component = itemStack.get(ModDataComponents.JUICE);
        return component != null ? ARGB.opaque(component.getColor(this.defaultColor)) : ARGB.opaque(this.defaultColor);
    }

    @Override
    public @NotNull MapCodec<JuiceColor> type() {
        return MAP_CODEC;
    }
}

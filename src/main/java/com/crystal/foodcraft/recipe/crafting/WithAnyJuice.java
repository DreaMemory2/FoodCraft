package com.crystal.foodcraft.recipe.crafting;

import com.crystal.foodcraft.item.juice.Juice;
import com.crystal.foodcraft.item.juice.JuiceContents;
import com.crystal.foodcraft.register.ModDataComponents;
import com.crystal.foodcraft.register.ModRegistryKeys;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.context.ContextMap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.display.DisplayContentsFactory;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplayContext;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public record WithAnyJuice(SlotDisplay display) implements SlotDisplay {
    public static final MapCodec<WithAnyJuice> MAP_CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    SlotDisplay.CODEC.fieldOf("contents").forGetter(WithAnyJuice::display)
            ).apply(instance, WithAnyJuice::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, WithAnyJuice> STREAM_CODEC = StreamCodec.composite(
            SlotDisplay.STREAM_CODEC,
            WithAnyJuice::display,
            WithAnyJuice::new
    );
    public static final Type<@NotNull WithAnyJuice> TYPE = new Type<>(MAP_CODEC, STREAM_CODEC);

    @Override
    public <T> @NotNull Stream<T> resolve(@NotNull ContextMap context, @NotNull DisplayContentsFactory<T> factory) {
        if (factory instanceof DisplayContentsFactory.ForStacks<T> stacks) {
            List<ItemStack> items = this.display.resolveForStacks(context);
            Optional<? extends HolderLookup.RegistryLookup<Juice>> juices = Optional.ofNullable(context.getOptional(SlotDisplayContext.REGISTRIES)).flatMap(provider -> provider.lookup(ModRegistryKeys.JUICE));
            return juices.stream().flatMap(HolderLookup::listElements).flatMap(juice -> {
                JuiceContents contents = new JuiceContents(juice);
                return items.stream().map(item -> {
                    ItemStack copy = item.copy();
                    copy.set(ModDataComponents.JUICE, contents);
                    return stacks.forStack(copy);
                });
            });
        } else {
            return Stream.empty();
        }
    }

    @Override
    public @NotNull Type<? extends @NotNull SlotDisplay> type() {
        return TYPE;
    }
}

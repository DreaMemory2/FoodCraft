package com.crystal.foodcraft.network;

import com.crystal.foodcraft.block.entity.BrewBarrelBlockEntity;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.StreamServerDataProvider;

/**
 * @see com.crystal.foodcraft.block.entity.BrewBarrelBlockEntity BrewBarrelBlockEntity
 */
public class TickSyncDataProvider implements StreamServerDataProvider<@NotNull BlockAccessor, Integer> {
    public static final TickSyncDataProvider INSTANCE = new TickSyncDataProvider();

    private TickSyncDataProvider() {}

    @Override
    public @Nullable Integer streamData(BlockAccessor accessor) {
        if (accessor.getBlockEntity() instanceof BrewBarrelBlockEntity blockEntity)
            return blockEntity.getFermentingTime() / 20;
        return 0;
    }

    @Override
    public @NotNull StreamCodec<RegistryFriendlyByteBuf, Integer> streamCodec() {
        return ByteBufCodecs.VAR_INT.cast();
    }

    @Override
    public @NotNull Identifier getUid() {
        return Identifier.parse("crystalmod:fermenting_time");
    }
}

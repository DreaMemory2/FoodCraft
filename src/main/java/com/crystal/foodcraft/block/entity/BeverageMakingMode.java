package com.crystal.foodcraft.block.entity;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum BeverageMakingMode implements StringRepresentable {
    COOL("cool", 1),
    HEAT("heat", 2);

    public static final Codec<BeverageMakingMode> CODEC = StringRepresentable.fromValues(BeverageMakingMode::values);
    private final String id;
    private final int level;

    BeverageMakingMode(String id, int level) {
        this.id = id;
        this.level = level;
    }

    public String id() {
        return id;
    }

    public int level() {
        return level;
    }

    @Override
    public @NotNull String getSerializedName() {
        return id;
    }
}

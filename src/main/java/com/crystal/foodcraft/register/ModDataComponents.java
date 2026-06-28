package com.crystal.foodcraft.register;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.item.juice.JuiceContents;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.function.UnaryOperator;

public class ModDataComponents {
    public static final DataComponentType<JuiceContents> JUICE = register("juice", builder ->
            builder.persistent(JuiceContents.CODEC).networkSynchronized(JuiceContents.PACKET_CODEC));

    public static <T> DataComponentType<T> register(String id, UnaryOperator<DataComponentType.Builder<T>> builder) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, FoodCraft.of(id), builder.apply(DataComponentType.builder()).build());
    }
}

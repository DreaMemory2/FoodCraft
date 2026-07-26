package com.crystal.foodcraft.api;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

/**
 * ClassName: FabricStreamCodecs<br>
 * Description: <br>
 * Datetime: 2025/5/28 20:26<br>
 * @author Crystal
 * @version 1.0.1
 * @since 1.0.0
 */
public class FabricStreamCodecs {

    public static <B extends FriendlyByteBuf, V extends Enum<V>> StreamCodec<B, V> enumCodec(Class<V> enumClass) {
        return new StreamCodec<>() {
            @Override
            public V decode(B buf) {
                return buf.readEnum(enumClass);
            }

            @Override
            public void encode(B buf, V value) {
                buf.writeEnum(value);
            }
        };
    }
}

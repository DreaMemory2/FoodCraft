package com.crystal.foodcraft.block.crop;

import com.crystal.foodcraft.block.FCCropBlock;
import com.crystal.foodcraft.item.ModItems;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

public class RedBeanCrop extends FCCropBlock {

    public RedBeanCrop(Properties properties) {
        super(properties);
    }

    @NotNull
    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.RED_BEAN;
    }
}

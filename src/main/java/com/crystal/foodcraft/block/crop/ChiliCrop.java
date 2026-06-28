package com.crystal.foodcraft.block.crop;

import com.crystal.foodcraft.block.FCCropBlock;
import com.crystal.foodcraft.item.ModItems;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

public class ChiliCrop extends FCCropBlock {

    public ChiliCrop(Properties properties) {
        super(properties);
    }

    @NotNull
    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.CHILI;
    }
}

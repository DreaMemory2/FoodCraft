package com.crystal.foodcraft.block.crop;

import com.crystal.foodcraft.block.FCCropBlock;
import com.crystal.foodcraft.item.ModItems;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

public class CornCrop extends FCCropBlock {

    public CornCrop(Properties properties) {
        super(properties);
    }

    @NotNull
    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.CORN_SEEDS;
    }
}

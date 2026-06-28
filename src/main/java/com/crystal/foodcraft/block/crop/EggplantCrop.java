package com.crystal.foodcraft.block.crop;

import com.crystal.foodcraft.block.FCCropBlock;
import com.crystal.foodcraft.item.ModItems;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

public class EggplantCrop extends FCCropBlock {

    public EggplantCrop(Properties properties) {
        super(properties);
    }

    @NotNull
    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.EGGPLANT_SEEDS;
    }
}

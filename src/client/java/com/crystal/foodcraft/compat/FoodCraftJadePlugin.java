package com.crystal.foodcraft.compat;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.block.entity.BrewBarrelBlockEntity;
import com.crystal.foodcraft.block.machine.BrewBarrel;
import com.crystal.foodcraft.compat.jade.FermentingTimePlugin;
import com.crystal.foodcraft.network.TickSyncDataProvider;
import net.minecraft.resources.Identifier;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class FoodCraftJadePlugin implements IWailaPlugin {
    public static final Identifier MOD_ID = FoodCraft.of("jade_plugin");

    @Override
    public void registerClient(IWailaClientRegistration register) {
        register.registerBlockComponent(FermentingTimePlugin.INSTANCE, BrewBarrel.class);
    }

    @Override
    public void register(IWailaCommonRegistration register) {
        register.registerBlockDataProvider(TickSyncDataProvider.INSTANCE, BrewBarrelBlockEntity.class);
    }
}

package com.crystal.foodcraft;

import com.crystal.foodcraft.item.ModItems;
import com.crystal.foodcraft.jei.JuiceSubtypeInterpreter;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
public class FoodCraftJeiPlugin implements IModPlugin {

    @NotNull
    @Override
    public Identifier getPluginUid() {
        return FoodCraft.of("jei_plugin");
    }

    @Override
    public void registerItemSubtypes(@NotNull ISubtypeRegistration register) {
        register.registerSubtypeInterpreter(ModItems.JUICE, JuiceSubtypeInterpreter.INSTANCE);
    }
}

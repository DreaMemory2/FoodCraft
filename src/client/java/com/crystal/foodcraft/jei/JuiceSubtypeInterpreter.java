package com.crystal.foodcraft.jei;

import com.crystal.foodcraft.item.juice.JuiceContents;
import com.crystal.foodcraft.register.ModDataComponents;
import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class JuiceSubtypeInterpreter implements ISubtypeInterpreter<ItemStack> {
    public static final JuiceSubtypeInterpreter INSTANCE = new JuiceSubtypeInterpreter();

    private JuiceSubtypeInterpreter() {}

    @Override
    public Object getSubtypeData(@NotNull ItemStack stack, @NotNull UidContext context) {
        JuiceContents contents = stack.get(ModDataComponents.JUICE);
        if (contents == null) return null;
        return contents.juice().orElse(null);
    }
}

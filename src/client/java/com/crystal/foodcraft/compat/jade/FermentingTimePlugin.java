package com.crystal.foodcraft.compat.jade;

import com.crystal.foodcraft.network.TickSyncDataProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.Element;
import snownee.jade.api.ui.JadeUI;

import java.util.Optional;

public class FermentingTimePlugin implements IBlockComponentProvider {
    public static final FermentingTimePlugin INSTANCE = new FermentingTimePlugin();

    private FermentingTimePlugin() {}

    @Override
    public void appendTooltip(@NotNull ITooltip tooltip, BlockAccessor accessor, @NotNull IPluginConfig config) {
        Optional<Integer> time = TickSyncDataProvider.INSTANCE.decodeFromData(accessor);
        time.ifPresent(integer -> {
            Element icon = JadeUI.smallItem(new ItemStack(Items.CLOCK));
            tooltip.add(icon);
            tooltip.append(Component.translatable("tooltip.crystalcraft.fermenting_time", integer));
        });
    }

    @NotNull
    @Override
    public Identifier getUid() {
        return Identifier.parse("crystalmod:fermenting_time");
    }
}

package com.crystal.foodcraft.item.juice;

import com.crystal.foodcraft.register.ModDataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * @see net.minecraft.world.item.PotionItem PotionItem
 */
public class JuiceItem extends Item {

    public JuiceItem(Properties properties) {
        super(properties);
    }

    @NotNull
    @Override
    public ItemStack getDefaultInstance() {
        ItemStack itemStack = super.getDefaultInstance();
        itemStack.set(ModDataComponents.JUICE, new JuiceContents(Juices.WATER));
        return itemStack;
    }

    @NotNull
    @Override
    public Component getName(@NotNull ItemStack itemStack) {
        JuiceContents contents = itemStack.get(ModDataComponents.JUICE);
        return contents != null ? contents.getName(this.descriptionId + ".") : super.getName(itemStack);
    }
}

package com.crystal.foodcraft.screenhandler;

import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public abstract class BaseMachineMenu extends ScreenHandler {
    private final Container container;
    private final ContainerData data;
    private final Level level;

    public BaseMachineMenu(@Nullable MenuType<?> menuType, int containerId, Inventory inventory, Container container, ContainerData data) {
        super(menuType, containerId, container);
        this.container = container;
        this.data = data;
        this.level = inventory.player.level();
    }

    public abstract int getMachineSlots();

    /**
     * <p>制作进度条</p>
     */
    public float getBurnProgress() {
        int current = this.data.get(2);
        int total = this.data.get(3);
        return total != 0 && current != 0 ? Mth.clamp((float)current / total, 0.0F, 1.0F) : 0.0F;
    }

    /**
     * <p>燃烧进度条</p>
     */
    public float getLitProgress() {
        int litDuration = this.data.get(1);
        if (litDuration == 0) {
            litDuration = 200;
        }

        return Mth.clamp((float)this.data.get(0) / litDuration, 0.0F, 1.0F);
    }

    public boolean isLit() {
        return this.data.get(0) > 0;
    }

    /**
     * <p>该物品是否可燃烧</p>
     */
    public boolean isFuel(ItemStack itemStack) {
        return this.level.fuelValues().isFuel(itemStack);
    }
}

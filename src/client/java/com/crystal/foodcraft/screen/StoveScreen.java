package com.crystal.foodcraft.screen;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.screenhandler.StoveMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import org.jetbrains.annotations.NotNull;

public class StoveScreen extends BaseContainerScreen<@NotNull StoveMenu> {
    public static final Identifier TEXTURE = FoodCraft.of("textures/gui/container/stove.png");
    public static final Identifier STOVE_SPRITE = FoodCraft.of("container/stove_lit");

    public StoveScreen(StoveMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public void extractBackground(@NotNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, this.leftPos, this.topPos, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);
        // 燃烧图标
        renderLit(graphics, 80, 35, false);
        // 加热图标
        ContainerData data = menu.getData();
        if (data.get(1) > 0)
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, STOVE_SPRITE, 9, 9, 0, 0, 83 + leftPos, 22 + topPos, 9, 9);
    }
}

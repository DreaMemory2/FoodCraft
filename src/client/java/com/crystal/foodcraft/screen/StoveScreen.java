package com.crystal.foodcraft.screen;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.screenhandler.StoveMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class StoveScreen extends BaseContainerScreen<@NotNull StoveMenu> {
    public static final Identifier TEXTURE = FoodCraft.of("textures/gui/container/stove.png");
    public static final Identifier STOVE_SPRITE = FoodCraft.of("container/stove_lit");

    public StoveScreen(StoveMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public void init() {
        super.init();
        // 设置标题在容器页面的中心位置上
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    @Override
    public void extractBackground(@NotNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, this.leftPos, this.topPos, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);
        // 燃烧图标
        renderLit(graphics, 80, 35);
        // 加热图标
        if (this.menu.isLit()) {
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, STOVE_SPRITE, 9, 9, 0, 0, 83 + this.leftPos, 22 + this.topPos, 9, 9);
        }
    }
}

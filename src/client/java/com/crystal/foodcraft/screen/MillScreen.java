package com.crystal.foodcraft.screen;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.screenhandler.MillMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class MillScreen extends BaseContainerScreen<@NotNull MillMenu> {
    public static final Identifier TEXTURE = FoodCraft.of("textures/gui/container/mill.png");
    public static final Identifier BURN_PROGRESS_SPRITE = FoodCraft.of("container/mill_progress");

    public MillScreen(MillMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public void extractBackground(@NotNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, this.leftPos, this.topPos, 0.0F, 0.0F, this.imageWidth, this.imageHeight, BACKGROUND_TEXTURE_WIDTH, BACKGROUND_TEXTURE_HEIGHT);
        // 燃烧图标
        renderLit(graphics, 80, 35);
        // 进度图标
        if (this.menu.getData().get(0) > 0) {
            renderBurn(graphics, BURN_PROGRESS_SPRITE, 76, 20);
        }
    }
}

package com.crystal.foodcraft.screen;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.screenhandler.PotMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class PotScreen extends BaseContainerScreen<@NotNull PotMenu> {
    private static final Identifier TEXTURE = FoodCraft.of("textures/gui/container/pot.png");
    private static final Identifier POT_PROGRESS_SPRITE = FoodCraft.of("container/pot_progress");

    public PotScreen(PotMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public void extractRenderState(@NotNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractRenderState(graphics, mouseX, mouseY, a);
        renderPanAndPotState(graphics, POT_PROGRESS_SPRITE, 93, 19, 48, 37);
    }

    @Override
    public void extractBackground(@NotNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, this.leftPos, this.topPos, 0.0F, 0.0F, this.imageWidth, this.imageHeight, BACKGROUND_TEXTURE_WIDTH, BACKGROUND_TEXTURE_HEIGHT);
        // 燃烧图标
        renderLit(graphics, 80, 64);
    }
}

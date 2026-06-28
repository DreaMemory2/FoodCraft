package com.crystal.foodcraft.screen;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.screenhandler.BaseMachineMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public abstract class BaseContainerScreen<T extends BaseMachineMenu> extends AbstractContainerScreen<@NotNull T> {
    /**
     * <p>燃烧图标</p>
     */
    public static final Identifier LIT_PROGRESS_SPRITE = FoodCraft.of("container/lit_progress");
    public static final Identifier BURN_PROGRESS_SPRITE = FoodCraft.of("container/burn_progress");

    public BaseContainerScreen(T menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    public void renderLit(GuiGraphicsExtractor guiGraphics, int x, int y) {
        if (menu.isLit()) {
            var j1 = Mth.ceil((menu.getLitProgress() * 13.0F));
            guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, LIT_PROGRESS_SPRITE, 14, 14, 0, 14 - j1, this.leftPos + x, this.topPos + y - j1 + 14, 14, j1);
        }
    }

    public void renderBurn(GuiGraphicsExtractor guiGraphics, Identifier sprite, int x, int y) {
        var burnProgressWidth = Mth.ceil(this.menu.getBurnProgress() * 24.0F);
        guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, sprite, 24, 16, 0, 0, this.leftPos + x, this.topPos + y, burnProgressWidth, 16);
    }
}

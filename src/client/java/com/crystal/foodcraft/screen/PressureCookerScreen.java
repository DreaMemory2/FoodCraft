package com.crystal.foodcraft.screen;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.block.entity.PressureCookerBlockEntity;
import com.crystal.foodcraft.screenhandler.PressureCookerMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class PressureCookerScreen extends BaseContainerScreen<@NotNull PressureCookerMenu> {
    public static final Identifier TEXTURE = FoodCraft.of("textures/gui/container/pressure_cooker.png");

    public PressureCookerScreen(PressureCookerMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public void init() {
        super.init();
        // 设置标题在容器页面的中心位置上
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
        // 添加液体显示组件
        PressureCookerBlockEntity blockEntity = this.menu.getBlockEntity();
        this.addRenderableWidget(FluidWidget.builder(blockEntity.getFluidStorage()).position(this.leftPos + 18, this.topPos + 14).size(11, 59).posSupplier(blockEntity::getBlockPos).build());
    }

    @Override
    public void extractBackground(@NotNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, this.leftPos, this.topPos, 0.0F, 0.0F, this.imageWidth, this.imageHeight, BACKGROUND_TEXTURE_WIDTH, BACKGROUND_TEXTURE_HEIGHT);
        // 燃烧图标
        renderLit(graphics, 121, 61);
        // 进度图标
        if (this.menu.getData().get(0) > 0) {
            renderBurn(graphics, PROGRESS_SPRITE, 118, 30);
        }
    }
}

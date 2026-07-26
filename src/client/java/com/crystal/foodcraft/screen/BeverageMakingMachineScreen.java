package com.crystal.foodcraft.screen;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.block.entity.BMMachineBlockEntity;
import com.crystal.foodcraft.block.entity.BeverageMakingMode;
import com.crystal.foodcraft.screenhandler.BeverageMakingMachineMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class BeverageMakingMachineScreen extends BaseContainerScreen<@NotNull BeverageMakingMachineMenu> {
    public static final Identifier TEXTURE = FoodCraft.of("textures/gui/container/beverage_making_machine.png");
    public static final Identifier COOL_PROGRESS_SPRITE = FoodCraft.of("container/cool_progress");

    public BeverageMakingMachineScreen(BeverageMakingMachineMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public void init() {
        super.init();
        // 设置标题在容器页面的中心位置上
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
        // 添加液体显示组件
        BMMachineBlockEntity blockEntity = this.menu.getBlockEntity();
        this.addRenderableWidget(FluidWidget.builder(blockEntity.getFluidStorage()).position(this.leftPos + 18, this.topPos + 14).size(11, 59).posSupplier(blockEntity::getBlockPos).build());
    }

    @Override
    public void extractBackground(@NotNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, this.leftPos, this.topPos, 0.0F, 0.0F, this.imageWidth, this.imageHeight, BACKGROUND_TEXTURE_WIDTH, BACKGROUND_TEXTURE_HEIGHT);

        if (this.menu.getData().get(0) > 0) renderBurn(graphics, 58, 30);

        int progress = Mth.ceil(getLitProgress() * 12.0F);
        if (menu.getData().get(4) == BeverageMakingMode.COOL.level()) {
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, COOL_PROGRESS_SPRITE, 13, 12, 0, 12 - progress, this.leftPos + 146, this.topPos + 54 - progress + 12, 13, progress);
        }
        if (menu.getData().get(4) == BeverageMakingMode.HEAT.level()) {
            renderLit(graphics, 144, 23);
        }
    }
}

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
import net.minecraft.world.inventory.ContainerData;
import org.jetbrains.annotations.NotNull;

public abstract class BaseContainerScreen<T extends BaseMachineMenu> extends AbstractContainerScreen<@NotNull T> {
    public static final Identifier PROGRESS_SPRITE = FoodCraft.of("container/make_progress");
    private static final Identifier COOK_PROGRESS_SPRITE = FoodCraft.of("container/cook_progress");
    private static final Identifier COOKED_INDICATOR_SPRITE = FoodCraft.of( "container/cooked_indicator");
    private static final Identifier OVERCOOKED_INDICATOR_SPRITE = FoodCraft.of("container/overcooked_indicator");
    private static final Identifier LIT_PROGRESS_SPRITE = FoodCraft.of("container/lit_progress");

    public BaseContainerScreen(T menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public void init() {
        super.init();
        // 设置标题在容器页面的中心位置上
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    /**
     * @param canLit 是否可持续保持加热状态
     */
    public void renderLit(GuiGraphicsExtractor guiGraphics, int x, int y, boolean canLit) {
        ContainerData data = menu.getData();
        // 如果canLit为true，且data.get(1)的isLit为true，则可持续保持加热状态
        if (canLit && data.get(1) == 1) {
            guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, LIT_PROGRESS_SPRITE, 14, 14, 0, 1, this.leftPos + x, this.topPos + y, 14, 13);
        }
        // 如果canLit为false，且data.get(0)的burnTime为true，则火焰随燃烧时间减少而熄灭
        else if (data.get(0) > 0) {
            int progress = Mth.ceil((getLitProgress() * 13.0F));
            guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, LIT_PROGRESS_SPRITE, 14, 14, 0, 14 - progress, leftPos + x, topPos + y - progress + 14, 14, progress);
        }
    }

    /**
     * <p>渲染是否处于加热状态，是否处于受热状态</p>
     */
    public void renderLit(GuiGraphicsExtractor guiGraphics, int x, int y) {
        renderLit(guiGraphics, x, y, true);
    }

    public void renderCookedIndicator(GuiGraphicsExtractor guiGraphics, int x, int y, int minCookedLine) {
        guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, COOKED_INDICATOR_SPRITE, 1, 5, 0, 0, x + minCookedLine + this.leftPos, y + this.topPos, 1, 5);
    }

    public void renderOverCookedIndicator(GuiGraphicsExtractor guiGraphics, int x, int y, int maxCookedLine) {
        guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, OVERCOOKED_INDICATOR_SPRITE, 1, 5, 0, 0, x + maxCookedLine + this.leftPos, y + this.topPos, 1, 5);
    }

    public void renderBurn(GuiGraphicsExtractor guiGraphics, Identifier sprite, int x, int y) {
        if (getBurnProgress() > 0) {
            int j1 = Mth.floor(getBurnProgress() * 24.0F);
            guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, sprite, 24, 16, 0, 0, leftPos + x, topPos + y, j1, 16);
        }
    }

    /**
     * <p>渲染平底锅和锅烹饪进度条</p>
     * @param sprite 制作进度条样式：Pot进度条和默认进度条
     * @param x1 制作进度条位置
     * @param y1 制作进度条位置
     * @param x2 烹饪进度条位置
     * @param y2 烹饪进度条位置
     */
    public void renderPanAndPotState(GuiGraphicsExtractor graphics, Identifier sprite, int x1, int y1, int x2, int y2) {
        ContainerData data = menu.getData();
        // 如果烹饪时间为零，则渲染进度条
        int cookingTime = data.get(0);
        if (cookingTime == 0) return;

        int maxCookingTime = data.get(2);
        int minCookingTime = data.get(3);
        int maxTime = data.get(4);
        // 制作进度条
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, sprite, 24, 17, 0, 0, x1 + leftPos, y1 + topPos,
                Math.min(Mth.floor(24 * ((float)cookingTime / ((maxCookingTime + minCookingTime) / 2.0f))), 24), 17);

        // 距离最小烹饪条件的长度和距离最大烹饪条件长度
        int minCookedLine = Mth.floor(78 * ((float)minCookingTime / maxTime));
        int maxCookedLine = Mth.floor(78 * ((float)maxCookingTime / maxTime));

        // 如果进度条在最小烹饪时间，则
        if (cookingTime <= minCookingTime) {
            // 烹饪的进度条
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, COOK_PROGRESS_SPRITE, 78, 3, 0, 0, x2 + leftPos, y2 + topPos,
                    Mth.floor(78 * ((float) cookingTime / maxTime)), 3);
        }
        // 如果进度条在最小值和最大值之间，则
        else if (cookingTime <= maxCookingTime) {
            // 已经完成进度条
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, COOK_PROGRESS_SPRITE, 78, 3, 0, 0, x2 + leftPos, y2 + topPos,
                    minCookedLine, 3);
            // 介于最小值和最大值之间的进度条
            // 开启透明度通道 3407667 #33FF33
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, COOK_PROGRESS_SPRITE, 78, 3, minCookedLine, 0, x2 + minCookedLine + leftPos, y2 + topPos,
                    Mth.floor((maxCookedLine - minCookedLine) * ((float) (cookingTime - minCookingTime) / (maxCookingTime - minCookingTime))), 3);
        }
        // 如果进度条超过最大值，则
        else {
            // 已经完成进度条
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, COOK_PROGRESS_SPRITE, 78, 3, 0, 0, x2 + leftPos, y2 + topPos,
                    maxCookedLine, 3);
            // 大于最大烹饪时间的进度条
            // 开启透明度通道 3407667 #33FF33
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, COOK_PROGRESS_SPRITE, 78, 3, maxCookedLine, 0, x2 + maxCookedLine + leftPos, y2 + topPos,
                    Mth.floor((78 - maxCookedLine) * (((float) (cookingTime - maxCookingTime) / (maxTime - maxCookingTime)))), 3);
        }
        // 烹饪界限条
        renderCookedIndicator(graphics, x2, y2, minCookedLine);
        renderOverCookedIndicator(graphics, x2, y2, maxCookedLine);
    }

    /**
     * <p>设置方块实体的当前剩余燃烧时间: [0, 1]</p>
     * <p>case 0 : return BlockEntity.this.burnTime;</p>
     * <p>case 1 : return BlockEntity.this.maxBurnTime;</p>
     */
    public float getLitProgress() {
        ContainerData data = menu.getData();
        int i = data.get(1);
        if (i == 0) i = 200;
        return Mth.clamp((float) data.get(0) / (float) i, 0.0F, 1.0F);
    }

    /**
     * <p>设置方块实体的当前剩余制作时间: [0, 1]</p>
     * <p>case 2 : return BlockEntity.this.cookingTime;</p>
     * <p>case 3 : return BlockEntity.this.maxCookingTime;</p>
     */
    public float getBurnProgress() {
        var data = menu.getData();
        int i = data.get(2);
        int j = data.get(3);
        return j != 0 && i != 0 ? Mth.clamp((float)i / (float)j, 0.0F, 1.0F) : 0.0F;
    }
}

package com.crystal.foodcraft.screen;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.screenhandler.PanMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import org.jetbrains.annotations.NotNull;

public class PanScreen extends BaseContainerScreen<@NotNull PanMenu> {
    private static final Identifier TEXTURE = FoodCraft.of("textures/gui/container/pan.png");
    private static final Identifier COOK_PROGRESS_SPRITE = FoodCraft.of("container/cook_progress");
    private static final Identifier PAN_PROGRESS_SPRITE = BaseContainerScreen.BURN_PROGRESS_SPRITE;
    private static final Identifier COOKED_INDICATOR_SPRITE = FoodCraft.of("container/cooked_indicator");
    private static final Identifier OVERCOOKED_INDICATOR_SPRITE = FoodCraft.of("container/overcooked_indicator");

    public PanScreen(PanMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public void init() {
        super.init();
        // 设置标题在容器页面的中心位置上
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    @Override
    public void extractRenderState(@NotNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractRenderState(graphics, mouseX, mouseY, a);
        ContainerData data = menu.getContainerData();
        if (data.get(2) != 0 && data.get(3) != 0) {
            int cookingTime = data.get(0);
            int minCookingTime = data.get(1);
            int maxCookingTime = data.get(2);
            int maxTime = data.get(3);

            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, PAN_PROGRESS_SPRITE, 24, 17, 0, 0, 72 + this.leftPos, 39 + this.topPos,
                    Math.min(Mth.floor(24 * ((float) cookingTime / ((maxCookingTime + minCookingTime) / 2.0f))), 24), 17);

            int cookedX = Mth.floor(78 * ((float) minCookingTime / maxTime));
            int overcookedX = Mth.floor(78 * ((float) maxCookingTime / maxTime));

            if (cookingTime <= minCookingTime) {
                graphics.blitSprite(RenderPipelines.GUI_TEXTURED, COOK_PROGRESS_SPRITE, 78, 3, 0, 0, 46 + this.leftPos, 62 + this.topPos,
                        Mth.floor(78 * ((float) cookingTime / maxTime)), 3);
            } else if (cookingTime <= maxCookingTime) {
                graphics.blitSprite(RenderPipelines.GUI_TEXTURED, COOK_PROGRESS_SPRITE, 78, 3, 0, 0, 46 + this.leftPos, 62 + this.topPos, cookedX, 3);
                // 开启透明度通道 3407667 #33FF33
                graphics.blitSprite(RenderPipelines.GUI_TEXTURED, COOK_PROGRESS_SPRITE, 78, 3, cookedX, 0, 46 + cookedX + this.leftPos, 62 + this.topPos,
                        Mth.floor((overcookedX - cookedX) * ((float) (cookingTime - minCookingTime) / (maxCookingTime - minCookingTime))), 3);
            } else {
                graphics.blitSprite(RenderPipelines.GUI_TEXTURED, COOK_PROGRESS_SPRITE, 78, 3, 0, 0, 46 + this.leftPos, 62 + this.topPos, overcookedX, 3);
                // 开启透明度通道 3407667 #33FF33
                graphics.blitSprite(RenderPipelines.GUI_TEXTURED, COOK_PROGRESS_SPRITE, 78, 3, overcookedX, 0,
                        46 + overcookedX + this.leftPos, 62 + this.topPos,
                        Mth.floor((78 - overcookedX) * (((float) (cookingTime - maxCookingTime) / (maxTime - maxCookingTime)))), 3);
            }
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, COOKED_INDICATOR_SPRITE, 1, 5, 0, 0, 46 + cookedX + this.leftPos, 61 + this.topPos, 1, 5);
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, OVERCOOKED_INDICATOR_SPRITE, 1, 5, 0, 0, 46 + overcookedX + this.leftPos, 61 + this.topPos, 1, 5);
        }
    }

    @Override
    public void extractBackground(@NotNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, this.leftPos, this.topPos, 0.0F, 0.0F, this.imageWidth, this.imageHeight, BACKGROUND_TEXTURE_WIDTH, BACKGROUND_TEXTURE_HEIGHT);
        // 燃烧图标
        renderLit(graphics, 80, 18);
    }
}

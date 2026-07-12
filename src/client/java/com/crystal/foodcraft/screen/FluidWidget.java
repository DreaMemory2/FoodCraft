package com.crystal.foodcraft.screen;

import com.crystal.foodcraft.util.ScreenUtils;
import com.crystal.foodcraft.util.SimpleFluidStorage;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderingRegistry;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.layouts.LayoutElement;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * <p>Renderable - 用于渲染，需要通过 addRenderableWidget 方法将组件注册到屏幕上</p>
 * <p>映射表：</p>
 * <p>{@link LayoutElement}: net.minecraft.client.gui.widget.Widget</p>
 * <p>{@link Renderable}: net.minecraft.client.gui.Drawable</p>
 */
public class FluidWidget implements Renderable, LayoutElement, GuiEventListener, NarratableEntry {
    /**
     * <p>设置单一液体储罐（或液体槽位）</p>
     * <p>用于存储单一液体槽位渲染</p>
     */
    private final SingleFluidStorage fluidStorage;
    /**
     * <p>设置方块位置，原因液体因位置不同且渲染效果也不同</p>
     * <p>例如：海洋的水和沼泽的水的颜色不同</p>
     */
    private final Supplier<BlockPos> posSupplier;
    /**
     * <p>设置液体渲染纹理的大小</p>
     */
    private final int width;
    private final int height;
    /**
     * <p>设置液体渲染纹理的位置</p>
     */
    private int x;
    private int y;

    public FluidWidget(SingleFluidStorage fluidStorage, int x, int y, int width, int height, Supplier<BlockPos> posSupplier) {
        this.fluidStorage = fluidStorage;
        this.posSupplier = posSupplier;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    @Override
    public void extractRenderState(@NotNull GuiGraphicsExtractor context, int mouseX, int mouseY, float a) {
        // 获取储罐中的液体
        Fluid fluid = this.fluidStorage.variant.getFluid();
        // 液体的容量
        long fluidAmount = this.fluidStorage.getAmount();
        // 如果液体的容量小于等于0，则不渲染液体
        if (fluidAmount <= 0) return;
        // 储罐的容量
        long capacity = this.fluidStorage.getCapacity();
        // 液体的长度（即液体的宽度），液体占容量的百分比乘以高度
        int fluidHeight = Math.round(((float) fluidAmount / capacity) * this.height);

        FluidRenderHandler fluidRenderHandler = FluidRenderingRegistry.get(fluid);
        if (fluidRenderHandler == null) return;

        // 获取液体储罐的位置
        BlockPos pos = this.posSupplier.get();
        // 液体静止状态（默认状态）
        FluidState fluidState = fluid.defaultFluidState();
        // 世界
        ClientLevel world = Minecraft.getInstance().level;
        if (world == null) return;
        // 获取静态精灵图（静态纹理图）
        TextureAtlasSprite sprite = Minecraft.getInstance().getModelManager().getFluidStateModelSet().get(fluidState).stillMaterial().sprite();
        // 着色颜色，例如：不同群系中的水的颜色，岩浆为-1
        int tintColor = FluidVariantRendering.getColor(this.fluidStorage.variant, world, pos);

        // 液体图像是从上到下绘制，所以y减去液体高度
        ScreenUtils.renderTiledSprite(context, sprite, this.x, this.y + this.height - fluidHeight, this.width, fluidHeight, tintColor);

        // 绘制液体提示框
        // 判断鼠标是否在绘制区域上方，若鼠标停留在绘制区域，则绘制提示框
        if (isPointWithinBounds(x, y, width, height, mouseX, mouseY)) {
            drawTooltip(context, mouseX, mouseY);
        }
    }

    /**
     * <p>通过{@code context}绘制鼠标停留提示框</p>
     * @param context 绘制上下文
     * @param mouseX 鼠标的位置
     * @param mouseY 鼠标的位置
     */
    protected void drawTooltip(GuiGraphicsExtractor context, int mouseX, int mouseY) {
        // 获取液体，液体容量，储罐容量
        Fluid fluid = this.fluidStorage.variant.getFluid();
        long fluidAmount = this.fluidStorage.getAmount();
        long fluidCapacity = this.fluidStorage.getCapacity();

        // 文本绘制区域
        Font textRenderer = Minecraft.getInstance().font;
        // 判断液体是为空，且液体容量大于 0
        if (fluid != null && fluidAmount > 0) {
            List<Component> texts = List.of(
                    // 先绘制液体名称
                    Component.translatable(fluid.defaultFluidState().createLegacyBlock().getBlock().getDescriptionId()),
                    // 再绘制液体占比
                    Component.literal("%s / %s mB".formatted(SimpleFluidStorage.getMB(fluidAmount), SimpleFluidStorage.getMB(fluidCapacity)))
            );

            context.setComponentTooltipForNextFrame(textRenderer, texts, mouseX, mouseY);
        }
    }

    /**
     * <p>仅检查鼠标位置是否在液体框内</p>
     * <p>鼠标的位置介于左侧至右侧，且底侧至顶侧之间框</p>
     * @param pointX 鼠标指向的位置
     * @param pointY 鼠标指向的位置
     */
    private static boolean isPointWithinBounds(int x, int y, int width, int height, int pointX, int pointY) {
        return pointX >= x && pointX <= x + width && pointY >= y && pointY <= y + height;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @NotNull
    @Override
    public ScreenRectangle getRectangle() {
        return LayoutElement.super.getRectangle();
    }

    /**
     * <p>设置子组件</p>
     */
    @Override
    public void visitWidgets(@NotNull Consumer<AbstractWidget> widgetVisitor) {}

    public static Builder builder(SingleFluidStorage fluidStorage) {
        return new Builder(fluidStorage);
    }

    @Override
    public void setFocused(boolean focused) {
    }

    @Override
    public boolean isFocused() {
        return true;
    }

    @NotNull
    @Override
    public NarrationPriority narrationPriority() {
        return NarrationPriority.HOVERED;
    }

    @Override
    public void updateNarration(@NotNull NarrationElementOutput output) {

    }

    /**
     * <p>快速构建组件，减少构建组件时间</p>
     */
    public static class Builder {
        private final SingleFluidStorage fluidStorage;
        private Supplier<BlockPos> posSupplier = () -> BlockPos.ZERO;
        private int width;
        private int height;
        private int x;
        private int y;

        public Builder(SingleFluidStorage fluidStorage) {
            this.fluidStorage = fluidStorage;
        }

        public Builder posSupplier(Supplier<BlockPos> posSupplier) {
            this.posSupplier = posSupplier;
            return this;
        }

        public Builder position(int x, int y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public Builder size(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public Builder bounds(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            return this;
        }

        public FluidWidget build() {
            return new FluidWidget(fluidStorage, x, y, width, height, posSupplier);
        }
    }
}

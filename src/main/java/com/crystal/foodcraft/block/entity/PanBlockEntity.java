package com.crystal.foodcraft.block.entity;

import com.crystal.foodcraft.api.TickableBlockEntity;
import com.crystal.foodcraft.recipe.ModRecipeTypes;
import com.crystal.foodcraft.recipe.PanningRecipe;
import com.crystal.foodcraft.screenhandler.PanMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;

public class PanBlockEntity extends HeatedBlockEntity implements TickableBlockEntity {
    private NonNullList<ItemStack> items = NonNullList.withSize(4, ItemStack.EMPTY);
    public ContainerData data = new ContainerData() {
        @Override
        public int get(int dataId) {
            return switch (dataId) {
                case 0 -> PanBlockEntity.this.cookingTime;
                case 1 -> PanBlockEntity.this.isLit ? 1 : 0;
                case 2 -> PanBlockEntity.this.maxCookingTime;
                case 3 -> PanBlockEntity.this.minCookingTime;
                case 4 -> PanBlockEntity.this.maxTime;
                default -> -1;
            };
        }

        @Override
        public void set(int dataId, int value) {
            switch (dataId) {
                case 0 -> PanBlockEntity.this.cookingTime = value;
                case 1 -> PanBlockEntity.this.isLit = false;
                case 2 -> PanBlockEntity.this.minCookingTime = value;
                case 3 -> PanBlockEntity.this.maxCookingTime = value;
                case 4 -> PanBlockEntity.this.maxTime = value;
            }
        }

        @Override
        public int getCount() {
            return 5;
        }
    };

    public PanBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.PAN, worldPosition, blockState);
    }

    @Override
    public void tick() {
        Level world = Objects.requireNonNull(getLevel());
        // 判断下方方块是否提供热源
        this.updateLit(world);
        // 判断是否槽位含义花生油
        ItemStack oil = items.getFirst();
        if (oil.isEmpty()) return;

        SingleRecipeInput input = new SingleRecipeInput(items.get(1));
        Optional<RecipeHolder<@NotNull PanningRecipe>> recipe = ((ServerLevel) world).recipeAccess().getRecipeFor(ModRecipeTypes.PAN_RECIPE_TYPE, input, world);
        // 开始烹饪
        startCooking(recipe);

        if (recipe.isPresent() && cookingTime > 0) {
            // items.get(12).isEmpty() 防止快速消耗食材
            if (cookingTime >= minCookingTime && cookingTime < maxCookingTime && items.get(2).isEmpty()) {
                // 如果没有烹饪过时，则输出结果食材
                ItemStack result = recipe.get().value().assemble(input);
                items.set(2, result);
                // 消耗食材
                input.item().shrink(1);
                oil.shrink(1);
            }
        }
        // 结束烹饪
        endCooking(items, 2);
    }

    @Override
    protected void loadAdditional(@NotNull ValueInput tag) {
        super.loadAdditional(tag);
        // 加载库存中的物品
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this.items);
    }

    @Override
    protected void saveAdditional(@NotNull ValueOutput tag) {
        super.saveAdditional(tag);
        // 存储库存中的物品
        ContainerHelper.saveAllItems(tag, this.items);
    }

    @NotNull
    @Override
    public AbstractContainerMenu createMenu(int containerId, @NotNull Inventory inventory) {
        return new PanMenu(containerId, inventory, this, data);
    }

    @NotNull
    @Override
    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(@NotNull NonNullList<ItemStack> items) {
        this.items = items;
    }

    @NotNull
    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.foodcraft.pan");
    }

    @Override
    public int getContainerSize() {
        return 4;
    }
}

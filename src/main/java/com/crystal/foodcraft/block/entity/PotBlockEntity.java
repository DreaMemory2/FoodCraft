package com.crystal.foodcraft.block.entity;

import com.crystal.foodcraft.api.TickableBlockEntity;
import com.crystal.foodcraft.recipe.ModRecipeTypes;
import com.crystal.foodcraft.recipe.PottingRecipe;
import com.crystal.foodcraft.recipe.input.PottingRecipeInput;
import com.crystal.foodcraft.screenhandler.PotMenu;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;

public class PotBlockEntity extends HeatedBlockEntity implements TickableBlockEntity {
    private NonNullList<ItemStack> items = NonNullList.withSize(14, ItemStack.EMPTY);
    public ContainerData data = new ContainerData() {
        @Override
        public int get(int dataId) {
            return switch (dataId) {
                case 0 -> PotBlockEntity.this.cookingTime;
                case 1 -> PotBlockEntity.this.isLit ? 1 : 0;
                case 2 -> PotBlockEntity.this.maxCookingTime;
                case 3 -> PotBlockEntity.this.minCookingTime;
                case 4 -> PotBlockEntity.this.maxTime;
                default -> -1;
            };
        }

        @Override
        public void set(int dataId, int value) {
            switch (dataId) {
                case 0 -> PotBlockEntity.this.cookingTime = value;
                case 1 -> PotBlockEntity.this.isLit = false;
                case 2 -> PotBlockEntity.this.maxCookingTime = value;
                case 3 -> PotBlockEntity.this.minCookingTime = value;
                case 4 -> PotBlockEntity.this.maxTime = value;
            }
        }

        @Override
        public int getCount() {
            return 5;
        }
    };

    public PotBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.POT, pos, blockState);
    }

    @Override
    public void tick() {
        Level world = Objects.requireNonNull(getLevel());
        // 判断下方方块是否提供热源
        this.updateLit(world);

        PottingRecipeInput inputs = PottingRecipeInput.input(items).slots();
        Optional<RecipeHolder<@NotNull PottingRecipe>> recipe = ((ServerLevel) world).recipeAccess().getRecipeFor(ModRecipeTypes.POT_RECIPE_TYPE, inputs, world);

        // 开始烹饪
        startCooking(recipe);

        if (recipe.isPresent() && cookingTime > 0) {
            // items.get(12).isEmpty() 防止快速消耗食材
            // 如果烹饪时间介于最小值和最大值之间，则生产食物
            if (cookingTime >= minCookingTime && cookingTime < maxCookingTime && items.get(12).isEmpty()) {
                // 如果没有烹饪过时，则输出结果食材
                ItemStack result = recipe.get().value().assemble(inputs);
                items.set(12, result);
                // 消耗食材
                consumeIngredient(PottingRecipeInput.input(items));
            }
        }

        // 结束烹饪
        endCooking(items, 12);
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
    protected AbstractContainerMenu createMenu(int containerId, @NotNull Inventory inventory) {
        return new PotMenu(containerId, inventory, this, data);
    }

    @NotNull
    @Override
    public NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(@NotNull NonNullList<ItemStack> items) {
        this.items = items;
    }

    @NotNull
    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.foodcraft.pot");
    }

    @Override
    public int getContainerSize() {
        return 14;
    }
}

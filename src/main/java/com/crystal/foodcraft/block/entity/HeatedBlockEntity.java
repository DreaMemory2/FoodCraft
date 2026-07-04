package com.crystal.foodcraft.block.entity;

import com.crystal.foodcraft.block.basic.CookableProvider;
import com.crystal.foodcraft.block.basic.HeatableProvider;
import com.crystal.foodcraft.network.NetWorkBlockEntity;
import com.crystal.foodcraft.recipe.HeatedRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * <p>需要提供热源的方块</p>
 */
public abstract class HeatedBlockEntity extends NetWorkBlockEntity implements CookableProvider, HeatableProvider {
    public int cookingTime;
    public int maxCookingTime;
    public int minCookingTime;
    public int maxTime;
    public boolean isLit;

    protected HeatedBlockEntity(BlockEntityType<?> type, BlockPos worldPosition, BlockState blockState) {
        super(type, worldPosition, blockState);
    }

    public void init() {
        maxTime = 0;
        minCookingTime = 0;
        maxCookingTime = 0;
        cookingTime = 0;
        setChanged();
    }

    @Override
    protected void loadAdditional(@NotNull ValueInput input) {
        super.loadAdditional(input);

        this.cookingTime = input.getIntOr("CookingTime", cookingTime);
        this.minCookingTime = input.getIntOr("MinCookingTime", minCookingTime);
        this.maxCookingTime = input.getIntOr("MaxCookingTime", maxCookingTime);
        this.maxTime = input.getIntOr("MaxTime", 0);
        this.isLit = input.getBooleanOr("IsLit", false);
    }

    @Override
    protected void saveAdditional(@NotNull ValueOutput output) {
        super.saveAdditional(output);

        output.putInt("CookingTime", this.cookingTime);
        output.putInt("MinCookingTime", this.minCookingTime);
        output.putInt("MaxCookingTime", this.maxCookingTime);
        output.putInt("MaxTime", this.maxTime);
        output.putBoolean("IsLit", this.isLit);
    }

    public void updateLit(Level world) {
        // 是否提供热源
        if (isHeated(world, getBlockPos(), getBlockState())) {
            isLit = true;
            detectLit(world, getBlockPos(), getBlockState(), true);
        } else {
            isLit = false;
            detectLit(world, getBlockPos(), getBlockState(), false);
            init();
        }
    }

    public <T extends @NotNull HeatedRecipe<RecipeInput>> void startCooking(Optional<RecipeHolder<T>> recipe) {
        // 第一次开始烹饪时，设置最值烹饪时间
        if (recipe.isPresent() && cookingTime == 0) {
            minCookingTime = recipe.get().value().getMinTime();
            maxCookingTime = recipe.get().value().getMaxTime();
            maxTime = recipe.get().value().getMaxTime() + 50;
            ++cookingTime;
        } else if (cookingTime > 0) ++cookingTime;

        // 如果烹饪时间小于最小值，并且食谱为空，则初始化
        if (cookingTime < minCookingTime && recipe.isEmpty()) init();
    }

    public void endCooking(NonNullList<ItemStack> items, int result) {
        // 如果烹饪时间超时（烹饪时间大于最大值），则使食物烧焦
        if (cookingTime > maxCookingTime && !items.get(result).isEmpty()) {
            // 使食物烧焦
            consumeFood(items, result, result + 1);
        }
        // 如果烹饪时间达到总时长，则初始化（或制作下一个食谱）
        if (cookingTime >= maxTime) {
            init();
        }
    }
}

package com.crystal.foodcraft.block.basic;

import com.crystal.foodcraft.item.ModItems;
import com.crystal.foodcraft.recipe.input.PottingRecipeInput;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public interface CookableProvider {

    default void produceFood(ItemStack result, NonNullList<ItemStack> items, int index) {
        ItemStack itemStack = items.get(2);
        if (itemStack.isEmpty()) {
            items.set(index, result.copy());
        } else {
            itemStack.grow(result.getCount());
        }
    }

    /**
     * 消耗食物，使得食物烧焦
     * @param items 非空物品列表
     * @param result 输出结果槽位的下标
     * @param charring 输出烧焦食物的下标
     */
    default void consumeFood(NonNullList<ItemStack> items, int result, int charring) {
        // 结果食材一定烧焦
        items.get(result).shrink(1);
        // 生成烧焦食物
        ItemStack itemStack = items.get(charring);
        if (itemStack.isEmpty()) {
            items.set(charring, new ItemStack(ModItems.CHARRED_FOOD));
        } else {
            itemStack.grow(1);
            items.set(charring, itemStack);
        }
    }

    /**
     * 消耗材料
     * @param inputs 输入槽
     */
    default void consumeIngredient(PottingRecipeInput.Positioned inputs) {
        // 消耗材料
        if (inputs != null) {
            // 消耗主材
            for (int i = 0; i < inputs.staples().size(); i++) {
                if (!inputs.staples().get(i).isEmpty()) {
                    inputs.staples().get(i).shrink(1);
                }
            }
            // 消耗配料
            for (int j = 0; j < inputs.flavoring().size(); j++) {
                if (!inputs.flavoring().get(j).isEmpty()) {
                    inputs.flavoring().get(j).shrink(1);
                }
            }
        }
    }
}

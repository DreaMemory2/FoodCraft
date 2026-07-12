package com.crystal.foodcraft.datagen.provider;

import com.crystal.foodcraft.recipe.crafting.JuiceImbueRecipe;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.CustomCraftingRecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;

public abstract class ModRecipeProvider extends RecipeProvider {
    private final HolderGetter<Item> items;
    private final HolderGetter<Fluid> fluids;

    protected ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);

        this.items = registries.lookupOrThrow(Registries.ITEM);
        this.fluids = registries.lookupOrThrow(Registries.FLUID);
    }

    public ChoppingRecipeBuilder chopping(ItemLike result, int count) {
        return ChoppingRecipeBuilder.chopping(this.items, result, count);
    }

    public MillingRecipeBuilder milling(Item result) {
        return MillingRecipeBuilder.milling(this.items, result);
    }

    public PottingRecipeBuilder potting(Item result, int count, int minTime, int maxTime) {
        return PottingRecipeBuilder.potting(this.items, result, count, minTime, maxTime);
    }

    public PottingRecipeBuilder potting(Item result, int minTime, int maxTime) {
        return PottingRecipeBuilder.potting(this.items, result, minTime, maxTime);
    }

    public PanningRecipeBuilder panning(Item result, int minTime, int maxTime) {
        return PanningRecipeBuilder.panning(this.items, result, minTime, maxTime);
    }

    public FryingRecipeBuilder frying(Item result) {
        return FryingRecipeBuilder.frying(this.items, this.fluids, result);
    }

    /**
     * @param result 主要产出物品，没有副产物
     * @param count 主要产物产出数量
     * @return 酿桶配方构建器
     */
    public BrewRecipeBuilder brewing(Item result, int count) {
        return BrewRecipeBuilder.brewing(this.items, this.fluids, result, count);
    }

    /**
     * @param result 主要产出物品
     * @param count1 主要产物产出数量
     * @param byproduct 副产物
     * @param count2 副产物产出数量
     * @return 酿桶配方构建器
     */
    public BrewRecipeBuilder brewing(Item result, int count1, Item byproduct, int count2) {
        return BrewRecipeBuilder.brewing(this.items, this.fluids, result, count1, byproduct, count2);
    }

    public PressureCookingBuilder pressureCooking(Item result, int count) {
        return PressureCookingBuilder.pressureCooking(this.items, this.fluids, result, count);
    }

    public PressureCookingBuilder pressureCooking(Item result) {
        return PressureCookingBuilder.pressureCooking(this.items, this.fluids, result);
    }

    /**
     * <p>自定义合成配方</p>
     * TODO 自定义果汁配方未能解决果汁组件配方
     * @see JuiceImbueRecipe
     */
    public CustomCraftingRecipeBuilder customCrafting(Item result, Item source, Item material) {
        return CustomCraftingRecipeBuilder.customCrafting(RecipeCategory.FOOD, (commonInfo, craftingBookInfo) ->
                new JuiceImbueRecipe(commonInfo, craftingBookInfo, Ingredient.of(source), Ingredient.of(material), new ItemStackTemplate(material)));
    }
}

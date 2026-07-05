package com.crystal.foodcraft.compat;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.block.ModBlocks;
import com.crystal.foodcraft.compat.jei.*;
import com.crystal.foodcraft.item.ModItems;
import com.crystal.foodcraft.screenhandler.*;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.*;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
public class FoodCraftJeiPlugin implements IModPlugin {
    @NotNull
    @Override
    public Identifier getPluginUid() {
        return FoodCraft.of("jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration register) {
        IGuiHelper helper = register.getJeiHelpers().getGuiHelper();
        register.addRecipeCategories(new MillingRecipeCategory(helper));
        register.addRecipeCategories(new PottingRecipeCategory(helper));
        register.addRecipeCategories(new PanningRecipeCategory(helper));
        register.addRecipeCategories(new FryingRecipeCategory(helper));
        register.addRecipeCategories(new BrewingRecipeCategory(helper));
        register.addRecipeCategories(new PressureCookingRecipeCategory(helper));
    }

    @Override
    public void registerRecipes(@NotNull IRecipeRegistration register) {
        FoodCraftRecipes recipes = new FoodCraftRecipes();
        // 自定义配方
        register.addRecipes(ModRecipeHolderTypes.MILLING_RECIPE_TYPE, recipes.getMillingRecipes());
        register.addRecipes(ModRecipeHolderTypes.POTTING_RECIPE_TYPE, recipes.getPottingRecipes());
        register.addRecipes(ModRecipeHolderTypes.PANNING_RECIPE_TYPE, recipes.getPanningRecipes());
        register.addRecipes(ModRecipeHolderTypes.FRYING_RECIPE_TYPE, recipes.getFryingRecipes());
        register.addRecipes(ModRecipeHolderTypes.BREWING_RECIPE_TYPE, recipes.getBrewingRecipes());
        register.addRecipes(ModRecipeHolderTypes.PRESSURE_COOKING_RECIPE_TYPE, recipes.getPressureCookingRecipes());
    }

    @Override
    public void registerGuiHandlers(@NotNull IGuiHandlerRegistration register) {
        // 注册GUI交互器，四个参数分别是：区域的起始位置x, 区域的起始位置y, 区域宽度和高度
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration register) {
        // 将自定义JEI配方页面和对应配方方块和该方块屏幕绑定
        register.addRecipeTransferHandler(MillMenu.class, ModMenuTypes.MILL, ModRecipeHolderTypes.MILLING_RECIPE_TYPE, 1, 1, 3, 36);
        register.addRecipeTransferHandler(PotMenu.class, ModMenuTypes.POT, ModRecipeHolderTypes.POTTING_RECIPE_TYPE, 0, 12, 13, 36);
        register.addRecipeTransferHandler(PanMenu.class, ModMenuTypes.PAN, ModRecipeHolderTypes.PANNING_RECIPE_TYPE, 0, 2, 4, 36);
        register.addRecipeTransferHandler(FryingMachineMenu.class, ModMenuTypes.FRYING_MACHINE, ModRecipeHolderTypes.FRYING_RECIPE_TYPE, 2, 1, 4, 36);
        register.addRecipeTransferHandler(BrewBarrelMenu.class, ModMenuTypes.BREW_BARREL, ModRecipeHolderTypes.BREWING_RECIPE_TYPE, 1, 3, 6, 36);
        register.addRecipeTransferHandler(PressureCookerMenu.class, ModMenuTypes.PRESSURE_COOKER, ModRecipeHolderTypes.PRESSURE_COOKING_RECIPE_TYPE, 2, 3, 6, 36);
    }

    @Override
    public void registerRecipeCatalysts(@NotNull IRecipeCatalystRegistration register) {
        register.addCraftingStation(ModRecipeHolderTypes.MILLING_RECIPE_TYPE, ModBlocks.MILL);
        register.addCraftingStation(ModRecipeHolderTypes.POTTING_RECIPE_TYPE, ModBlocks.POT);
        register.addCraftingStation(ModRecipeHolderTypes.PANNING_RECIPE_TYPE, ModBlocks.PAN);
        register.addCraftingStation(ModRecipeHolderTypes.FRYING_RECIPE_TYPE, ModBlocks.FRYING_MACHINE);
        register.addCraftingStation(ModRecipeHolderTypes.BREWING_RECIPE_TYPE, ModBlocks.BREW_BARREL);
        register.addCraftingStation(ModRecipeHolderTypes.PRESSURE_COOKING_RECIPE_TYPE, ModBlocks.PRESSURE_COOKER);
    }

    @Override
    public void registerItemSubtypes(@NotNull ISubtypeRegistration register) {
        register.registerSubtypeInterpreter(ModItems.JUICE, JuiceSubtypeInterpreter.INSTANCE);
    }
}

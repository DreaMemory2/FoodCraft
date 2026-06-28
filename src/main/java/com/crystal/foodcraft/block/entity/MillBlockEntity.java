package com.crystal.foodcraft.block.entity;

import com.crystal.foodcraft.api.ItemHandlerProvider;
import com.crystal.foodcraft.recipe.MillingRecipe;
import com.crystal.foodcraft.recipe.ModRecipeTypes;
import com.crystal.foodcraft.screenhandler.MillMenu;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class MillBlockEntity extends BaseBlockEntity implements ItemHandlerProvider, RecipeCraftingHolder {
    public static final int WORK_TIME = 200;
    public static final Codec<Map<ResourceKey<Recipe<?>>, String>> LAST_RECIPE = Codec.unboundedMap(Recipe.KEY_CODEC, Codec.STRING);
    private NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);
    public int cookingTime = 0;
    public int burnTime = 0;
    public int maxCookingTime = 0;
    public int maxBurnTime = 0;
    private final Map<ResourceKey<Recipe<?>>, String> lastRecipe = new HashMap<>();
    private final RecipeManager.CachedCheck<@NotNull RecipeInput, @NotNull MillingRecipe> quickCheck;
    protected final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int dataId) {
            return switch (dataId) {
                case 0 -> MillBlockEntity.this.burnTime;
                case 1 -> MillBlockEntity.this.maxBurnTime;
                case 2 -> MillBlockEntity.this.cookingTime;
                case 3 -> MillBlockEntity.this.maxCookingTime;
                default -> 0;
            };
        }

        @Override
        public void set(int dataId, int value) {
            switch (dataId) {
                case 0 -> MillBlockEntity.this.burnTime = value;
                case 1 -> MillBlockEntity.this.maxBurnTime = value;
                case 2 -> MillBlockEntity.this.cookingTime = value;
                case 3 -> MillBlockEntity.this.maxCookingTime = value;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    };

    public MillBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.MILL, worldPosition, blockState);
        this.quickCheck = RecipeManager.createCheck(ModRecipeTypes.MILLING_RECIPE_TYPE);
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

    @Override
    public void setItem(int slot, @NotNull ItemStack itemStack) {
        ItemStack oldStack = this.items.get(slot);
        boolean same = !itemStack.isEmpty() && ItemStack.isSameItemSameComponents(oldStack, itemStack);
        this.items.set(slot, itemStack);
        itemStack.limitSize(this.getMaxStackSize(itemStack));
        if (slot == 0 && !same && level instanceof ServerLevel) {
            this.maxCookingTime = WORK_TIME;
            this.cookingTime = 0;
            this.setChanged();
        }
    }

    @NotNull
    @Override
    public Component getDefaultName() {
        return Component.translatable("container.foodcraft.mill");
    }

    @Override
    public @NotNull AbstractContainerMenu createMenu(int containerId, @NotNull Inventory inventory) {
        return new MillMenu(containerId, inventory, this, this.dataAccess);
    }

    @Override
    protected void loadAdditional(@NotNull ValueInput tag) {
        super.loadAdditional(tag);
        // 加载库存中的物品
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this.items);

        this.cookingTime = tag.getIntOr("cookingTime", 0);
        this.burnTime = tag.getIntOr("burnTime", 0);
        this.maxCookingTime = tag.getIntOr("maxCookingTime", 0);
        this.maxBurnTime = tag.getIntOr("maxBurnTime", 0);

        this.lastRecipe.putAll(tag.read("LastRecipe", LAST_RECIPE).orElse(Map.of()));
    }

    @Override
    protected void saveAdditional(@NotNull ValueOutput tag) {
        super.saveAdditional(tag);
        // 存储库存中的物品
        ContainerHelper.saveAllItems(tag, items);

        tag.putInt("CookingTime", this.cookingTime);
        tag.putInt("BurnTime", this.burnTime);
        tag.putInt("MaxCookingTime", this.maxCookingTime);
        tag.putInt("MaxBurnTime", this.maxBurnTime);

        tag.store("LastRecipe", LAST_RECIPE, this.lastRecipe);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, @NotNull MillBlockEntity mill) {
        ItemStack fuelItem = mill.items.get(1);
        ItemStack ingredient = mill.items.getFirst();
        // 是否正在燃烧
        boolean isLit;
        if (mill.burnTime > 0) {
            mill.burnTime--;
            isLit = true;
        } else {
            isLit = false;
        }
        detectLit(level, blockPos, blockState, isLit);
        mill.setChanged();

        boolean hasFuel = !fuelItem.isEmpty();
        boolean hasIngredient = !ingredient.isEmpty();

        if (isLit || hasFuel && hasIngredient) {
            if (level instanceof ServerLevel serverLevel) {
                SingleRecipeInput input = new SingleRecipeInput(ingredient);
                RecipeHolder<@NotNull MillingRecipe> recipe = mill.quickCheck.getRecipeFor(input, serverLevel).orElse(null);
                if (recipe != null) {
                    int maxStackSize = mill.getMaxStackSize();
                    ItemStack result = recipe.value().assemble(input);
                    if (!result.isEmpty() && canBurn(mill.items, maxStackSize, result)) {
                        if (!isLit) {
                            int newLitTime = mill.getBurnItem(serverLevel.fuelValues(), fuelItem);
                            mill.burnTime = newLitTime;
                            mill.maxBurnTime = newLitTime;
                            if (newLitTime > 0) {
                                consumeFuel(mill.items, 1, fuelItem);
                                isLit = true;
                                detectLit(level, blockPos, blockState, isLit);
                                mill.setChanged();
                            }
                        }

                        if (isLit) {
                            mill.cookingTime++;
                            if (mill.cookingTime == mill.maxCookingTime) {
                                mill.cookingTime = 0;
                                mill.maxCookingTime = WORK_TIME;
                                burn(mill.items, ingredient, result);
                                mill.setRecipeUsed(recipe);
                                mill.setChanged();
                            }
                        } else {
                            mill.cookingTime = 0;
                        }
                    } else {
                        mill.cookingTime = 0;
                    }
                } else {
                    mill.cookingTime = 0;
                }
            } else if (mill.cookingTime > 0) {
                mill.cookingTime = Mth.clamp(mill.cookingTime - 2, 0, mill.maxCookingTime);
            }
        }
    }

    @Override
    public int getContainerSize() {
        return 3;
    }

    @Override
    public void setRecipeUsed(@Nullable RecipeHolder<?> recipeUsed) {
        if (recipeUsed != null) {
            ResourceKey<Recipe<?>> id = recipeUsed.id();
            this.lastRecipe.put(id, "LastRecipe");
        }
    }

    @Override
    public @Nullable RecipeHolder<?> getRecipeUsed() {
        return null;
    }

    @Override
    public ContainerData getInventory() {
        return this.dataAccess;
    }
}

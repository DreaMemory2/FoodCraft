package com.crystal.foodcraft.block.entity;

import com.crystal.foodcraft.api.HeatableHandleProvider;
import com.crystal.foodcraft.item.ModItems;
import com.crystal.foodcraft.recipe.ModRecipeTypes;
import com.crystal.foodcraft.recipe.PanningRecipe;
import com.crystal.foodcraft.screenhandler.PanMenu;
import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class PanBlockEntity extends BaseBlockEntity implements HeatableHandleProvider, RecipeCraftingHolder {
    private NonNullList<ItemStack> items = NonNullList.withSize(4, ItemStack.EMPTY);
    private static final Codec<Map<ResourceKey<Recipe<?>>, Integer>> LAST_RECIPE_CODEC = Codec.unboundedMap(Recipe.KEY_CODEC, Codec.INT);
    private final Reference2IntOpenHashMap<ResourceKey<Recipe<?>>> recipesUsed = new Reference2IntOpenHashMap<>();
    @Nullable
    private RecipeHolder<@NotNull PanningRecipe> lastRecipe = null;
    private final RecipeManager.CachedCheck<@NotNull RecipeInput, @NotNull PanningRecipe> quickCheck;
    public final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int i) {
            return switch (i) {
                case 0 -> PanBlockEntity.this.cookingTime;
                case 1 -> PanBlockEntity.this.minCookingTime;
                case 2 -> PanBlockEntity.this.maxCookingTime;
                case 3 -> PanBlockEntity.this.maxTime;
                case 4 -> PanBlockEntity.this.proficiency;
                case 5 -> PanBlockEntity.this.isHeated(Objects.requireNonNull(PanBlockEntity.this.getLevel()), PanBlockEntity.this.getBlockPos()) ? 1 : 0;
                default -> 0;
            };
        }

        @Override
        public void set(int i, int i1) {
            switch (i) {
                case 0 -> PanBlockEntity.this.cookingTime = i1;
                case 1 -> PanBlockEntity.this.minCookingTime = i1;
                case 2 -> PanBlockEntity.this.maxCookingTime = i1;
                case 3 -> PanBlockEntity.this.maxTime = i1;
                case 4 -> PanBlockEntity.this.proficiency = i1;
            }
        }

        @Override
        public int getCount() {
            return 6;
        }
    };

    public int cookingTime = 0;
    public int minCookingTime;
    public int maxCookingTime;
    public int maxTime;
    public int proficiency;

    private boolean cooked;
    private boolean overcooked;

    public PanBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.PAN, worldPosition, blockState);
        this.quickCheck = RecipeManager.createCheck(ModRecipeTypes.PAN_RECIPE_TYPE);
    }

    @Override
    protected void loadAdditional(@NotNull ValueInput tag) {
        super.loadAdditional(tag);
        // 加载库存中的物品
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this.items);

        this.cookingTime = tag.getIntOr("CookingTime", 0);
        this.minCookingTime = tag.getIntOr("MinCookingTime", 0);
        this.maxCookingTime = tag.getIntOr("MaxCookingTime", 0);
        this.maxTime = tag.getIntOr("MaxTime", 0);

        this.recipesUsed.clear();
        this.recipesUsed.putAll(tag.read("LastRecipe", LAST_RECIPE_CODEC).orElse(Map.of()));
    }

    @Override
    protected void saveAdditional(@NotNull ValueOutput tag) {
        super.saveAdditional(tag);
        // 存储库存中的物品
        ContainerHelper.saveAllItems(tag, this.items);

        tag.putInt("CookingTime", this.cookingTime);
        tag.putInt("MinCookingTime", this.minCookingTime);
        tag.putInt("MaxCookingTime", this.maxCookingTime);
        tag.putInt("MaxTime", this.maxTime);

        tag.store("LastRecipe", LAST_RECIPE_CODEC, this.recipesUsed);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, @NotNull PanBlockEntity pan) {
        ItemStack input = pan.items.get(1);
        if (pan.lastRecipe != null) {
            if (pan.cookingTime >= pan.minCookingTime) {
                if (pan.cookingTime > pan.maxCookingTime) {
                    if (!pan.overcooked && !pan.items.get(2).isEmpty()) {
                        pan.overcooked = true;
                        pan.cooked = false;
                        // 烧焦了
                        ItemStack charredFood = pan.items.get(3);
                        if (charredFood.isEmpty()) {
                            pan.items.set(3, new ItemStack(ModItems.CHARRED_FOOD));
                        } else {
                            charredFood.grow(charredFood.getCount());
                        }
                        pan.items.set(2, ItemStack.EMPTY);
                        level.playSound(null, blockPos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);
                    }
                } else {
                    if (!pan.cooked && level instanceof ServerLevel serverLevel) {
                        SingleRecipeInput recipeInput = new SingleRecipeInput(input);
                        Optional<RecipeHolder<@NotNull PanningRecipe>> recipe = serverLevel.recipeAccess().getRecipeFor(ModRecipeTypes.PAN_RECIPE_TYPE, recipeInput, level);
                        if (recipe.isEmpty()) {
                            pan.items.set(2, ItemStack.EMPTY);
                        } else {
                            burn(pan.items, input, recipe.get().value().assemble(null));
                            pan.setRecipeUsed(recipe.get());
                        }
                        pan.cooked = true;

                        level.playSound(null, blockPos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 1.0F);
                    }
                }
            }
            if (pan.isHeated(level, blockPos)) {
                pan.cookingTime += pan.proficiency + 1;
            }
            if (pan.cookingTime >= pan.maxTime) {
                pan.reset();
            }
        }

        if (pan.lastRecipe == null && level instanceof ServerLevel serverLevel) {
            SingleRecipeInput recipeInput = new SingleRecipeInput(input);
            RecipeHolder<@NotNull PanningRecipe> recipe = pan.quickCheck.getRecipeFor(recipeInput, serverLevel).orElse(null);
            if (recipe != null && pan.isHeated(level, blockPos)
                && !pan.items.get(0).isEmpty() // Has oil
                && pan.items.get(2).isEmpty()) {
                pan.items.getFirst().shrink(1);
                pan.toCraft(recipe);
                detectLit(serverLevel, blockPos, blockState, true);
            } else {
                pan.reset();
                detectLit(serverLevel, blockPos, blockState, false);
                pan.setChanged();
            }
        }
    }

    public void toCraft(RecipeHolder<@NotNull PanningRecipe> recipe) {
        lastRecipe = recipe;
        cookingTime = 0;
        minCookingTime = recipe.value().getMinTime();
        maxCookingTime = recipe.value().getMaxTime();
        maxTime = recipe.value().getMaxTime() + 50;

        setChanged();
    }

    public void reset() {
        lastRecipe = null;
        cookingTime = 0;
        minCookingTime = 0;
        maxCookingTime = 0;
        maxTime = 0;
        cooked = false;
        overcooked = false;
        recipesUsed.clear();
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

    @NotNull
    @Override
    public AbstractContainerMenu createMenu(int containerId, @NotNull Inventory inventory) {
        return new PanMenu(containerId, inventory, this, this.dataAccess);
    }

    @Override
    public void setRecipeUsed(@Nullable RecipeHolder<?> recipe) {
        if (recipe != null) {
            ResourceKey<Recipe<?>> recipeID = recipe.id();
            this.recipesUsed.addTo(recipeID, 1);
        }
    }

    @Override
    public @Nullable RecipeHolder<?> getRecipeUsed() {
        return null;
    }

    @Override
    public int getContainerSize() {
        return 4;
    }
}

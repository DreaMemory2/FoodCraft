package com.crystal.foodcraft.block.entity;

import com.crystal.foodcraft.api.HeatableHandleProvider;
import com.crystal.foodcraft.item.ModItems;
import com.crystal.foodcraft.recipe.ModRecipeTypes;
import com.crystal.foodcraft.recipe.PottingRecipe;
import com.crystal.foodcraft.recipe.input.PottingRecipeInput;
import com.crystal.foodcraft.screenhandler.PotMenu;
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

public class PotBlockEntity extends BaseBlockEntity implements HeatableHandleProvider, RecipeCraftingHolder {
    private NonNullList<ItemStack> items = NonNullList.withSize(14, ItemStack.EMPTY);
    private static final Codec<Map<ResourceKey<Recipe<?>>, Integer>> LAST_RECIPE_CODEC = Codec.unboundedMap(Recipe.KEY_CODEC, Codec.INT);
    private final Reference2IntOpenHashMap<ResourceKey<Recipe<?>>> recipesUsed = new Reference2IntOpenHashMap<>();
    @Nullable
    private RecipeHolder<@NotNull PottingRecipe> lastRecipe = null;
    private final RecipeManager.CachedCheck<@NotNull RecipeInput, @NotNull PottingRecipe> quickCheck;
    public final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> PotBlockEntity.this.cookingTime;
                case 1 -> PotBlockEntity.this.minCookingTime;
                case 2 -> PotBlockEntity.this.maxCookingTime;
                case 3 -> PotBlockEntity.this.maxTime;
                case 4 -> PotBlockEntity.this.proficiency;
                case 5 -> PotBlockEntity.this.isHeated(Objects.requireNonNull(PotBlockEntity.this.getLevel()), PotBlockEntity.this.getBlockPos()) ? 1 : 0;
                default -> 0;
            };
        }

        @Override
        public void set(int i, int i1) {
            switch (i) {
                case 0 -> PotBlockEntity.this.cookingTime = i1;
                case 1 -> PotBlockEntity.this.minCookingTime = i1;
                case 2 -> PotBlockEntity.this.maxCookingTime = i1;
                case 3 -> PotBlockEntity.this.maxTime = i1;
                case 4 -> PotBlockEntity.this.proficiency = i1;
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

    public PotBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.POT, pos, blockState);
        this.quickCheck = RecipeManager.createCheck(ModRecipeTypes.POT_RECIPE_TYPE);
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

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, @NotNull PotBlockEntity pot) {
        PottingRecipeInput inputs = PottingRecipeInput.input(pot.items).input();
        if (pot.lastRecipe != null) {
            if (pot.cookingTime >= pot.minCookingTime) {
                if (pot.cookingTime >= pot.maxCookingTime) {
                    if (!pot.overcooked) {
                        pot.cooked = false;
                        pot.overcooked = true;
                        // 烧焦了
                        ItemStack charredFood = pot.items.get(13);
                        if (charredFood.isEmpty()) {
                            pot.items.set(13, new ItemStack(ModItems.CHARRED_FOOD));
                        } else {
                            charredFood.grow(charredFood.getCount());
                        }
                        pot.items.set(12, ItemStack.EMPTY);
                        level.playSound(null, blockPos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);
                    }
                } else {
                    if (!pot.cooked && level instanceof ServerLevel serverLevel) {
                        Optional<RecipeHolder<@NotNull PottingRecipe>> recipe = serverLevel.recipeAccess().getRecipeFor(ModRecipeTypes.POT_RECIPE_TYPE, inputs, level);
                        if (recipe.isEmpty()) {
                            pot.items.set(12, ItemStack.EMPTY);
                        } else {
                            ItemStack result = recipe.get().value().assemble(null);
                            pot.consumeItem(pot.items, result, PottingRecipeInput.input(pot.items));
                            pot.setRecipeUsed(recipe.get());
                        }
                        pot.cooked = true;

                        level.playSound(null, blockPos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 1.0F);
                    }
                }
            }
            if (pot.isHeated(level, blockPos)) {
                pot.cookingTime += pot.proficiency + 1;
            }
            if (pot.cookingTime >= pot.maxTime) {
                pot.reset();
            }
        }

        if (pot.lastRecipe == null && level instanceof ServerLevel serverLevel) {
            RecipeHolder<@NotNull PottingRecipe> recipe = pot.quickCheck.getRecipeFor(inputs, serverLevel).orElse(null);
            if (recipe != null && pot.isHeated(level, blockPos) && pot.items.get(12).isEmpty()) {
                pot.toCraft(recipe);
                detectLit(serverLevel, blockPos, blockState, true);
            } else {
                pot.reset();
                detectLit(serverLevel, blockPos, blockState, false);
                pot.setChanged();
            }
        }
    }

    public void toCraft(RecipeHolder<@NotNull PottingRecipe> recipe) {
        lastRecipe = recipe;
        cookingTime = 0;
        minCookingTime = recipe.value().getMinTime();
        maxCookingTime = recipe.value().getMaxTime();
        maxTime = recipe.value().getMaxTime() + 50;

        setChanged();
    }

    @NotNull
    @Override
    public NonNullList<ItemStack> getItems() {
        return items;
    }

    private void reset() {
        this.lastRecipe = null;
        this.cookingTime = 0;
        this.minCookingTime = 0;
        this.maxCookingTime = 0;
        this.maxTime = 0;
        this.cooked = false;
        this.overcooked = false;
        this.recipesUsed.clear();
    }

    @Override
    protected void setItems(@NotNull NonNullList<ItemStack> items) {
        this.items = items;
    }

    private void consumeItem(NonNullList<ItemStack> items, ItemStack result, PottingRecipeInput.Positioned inputs) {
        ItemStack resultItemStack = items.get(12);
        if (resultItemStack.isEmpty()) {
            items.set(12, result.copy());
        } else {
            resultItemStack.grow(result.getCount());
        }
        // 消耗材料
        if (inputs != null) {
            for (int i = 8; i < inputs.ingredients().size() + 8; i++) {
                if (i <= 11 && inputs.ingredients().get(i) != null) {
                    inputs.ingredients().get(i).shrink(1);
                }
            }
            for (int i = 0; i < inputs.staples().size(); i++) {
                inputs.staples().get(i).shrink(1);
            }
        }
    }

    @NotNull
    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.foodcraft.pot");
    }

    @NotNull
    @Override
    protected AbstractContainerMenu createMenu(int containerId, @NotNull Inventory inventory) {
        return new PotMenu(containerId, inventory, this, this.dataAccess);
    }

    @Override
    public int getContainerSize() {
        return 14;
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
        return this.lastRecipe;
    }
}

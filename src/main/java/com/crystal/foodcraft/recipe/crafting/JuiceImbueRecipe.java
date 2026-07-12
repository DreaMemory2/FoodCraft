package com.crystal.foodcraft.recipe.crafting;

import com.crystal.foodcraft.recipe.ModRecipeTypes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.ShapelessCraftingRecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @see ImbueRecipe
 */
public class JuiceImbueRecipe extends NormalCraftingRecipe {
    public static final MapCodec<JuiceImbueRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    CommonInfo.MAP_CODEC.forGetter(recipe -> recipe.commonInfo),
                    CraftingBookInfo.MAP_CODEC.forGetter(recipe -> recipe.bookInfo),
                    Ingredient.CODEC.fieldOf("source").forGetter(recipe -> recipe.source),
                    Ingredient.CODEC.fieldOf("material").forGetter(recipe -> recipe.material),
                    ItemStackTemplate.CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
            ).apply(instance, JuiceImbueRecipe::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, JuiceImbueRecipe> STREAM_CODEC = StreamCodec.composite(
            CommonInfo.STREAM_CODEC,
            recipe -> recipe.commonInfo,
            CraftingBookInfo.STREAM_CODEC,
            recipe -> recipe.bookInfo,
            Ingredient.CONTENTS_STREAM_CODEC,
            recipe -> recipe.source,
            Ingredient.CONTENTS_STREAM_CODEC,
            recipe -> recipe.material,
            ItemStackTemplate.STREAM_CODEC,
            recipe -> recipe.result,
            JuiceImbueRecipe::new
    );
    private final Ingredient source;
    private final Ingredient material;
    private final ItemStackTemplate result;

    public JuiceImbueRecipe(CommonInfo commonInfo, CraftingBookInfo bookInfo, Ingredient source, Ingredient material, ItemStackTemplate result) {
        super(commonInfo, bookInfo);
        this.source = source;
        this.material = material;
        this.result = result;
    }

    @Override
    public boolean matches(CraftingInput input, @NotNull Level level) {
        if (input.ingredientCount() == 2) {
            return this.source.test(input.getItem(0))
                    && this.material.test(input.getItem(1));
        }
        return false;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull CraftingInput input) {
        // TODO 未能解决带有Juice组件物品合成冰淇淋的配方
        return this.result.create();
    }

    @Override
    public @NotNull RecipeSerializer<@NotNull JuiceImbueRecipe> getSerializer() {
        return ModRecipeTypes.JUICE_IMBUE_RECIPE_SERIALIZER;
    }

    @Override
    protected @NotNull PlacementInfo createPlacementInfo() {
        return PlacementInfo.create(List.of(this.source, this.material));
    }

    @Override
    public @NotNull List<RecipeDisplay> display() {
        SlotDisplay material = this.material.display();
        WithAnyJuice source = new WithAnyJuice(this.source.display());
        SlotDisplay.ItemStackSlotDisplay result = new SlotDisplay.ItemStackSlotDisplay(this.result);
        return List.of(new ShapelessCraftingRecipeDisplay(
                List.of(material, source),
                result, new SlotDisplay.ItemSlotDisplay(Items.CRAFTING_TABLE)));
    }
}

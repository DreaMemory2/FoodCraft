package com.crystal.foodcraft.screenhandler;

import com.crystal.foodcraft.recipe.ChoppingRecipe;
import com.crystal.foodcraft.recipe.ModRecipeTypes;
import com.crystal.foodcraft.recipe.input.ChoppingRecipeInput;
import com.crystal.foodcraft.tag.ModItemTags;
import com.crystal.foodcraft.screenhandler.inventory.ChoppingBoardResultSlot;
import com.crystal.foodcraft.screenhandler.inventory.KnifeInputSlot;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.ticks.ContainerSingleItem;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

public class ChoppingBoardMenu extends ScreenHandler {
    private final ContainerLevelAccess access;
    private final Player player;
    private final CraftingContainer craftSlots = new TransientCraftingContainer(this, 3, 1);
    private final ResultContainer resultSlots = new ResultContainer();
    private final Container knifeContainer = new ContainerSingleItem() {
        private ItemStack theItem = ItemStack.EMPTY;

        @NotNull
        @Override
        public ItemStack getTheItem() {
            return theItem;
        }

        @Override
        public void setTheItem(@NotNull ItemStack itemStack) {
            this.theItem = itemStack;
        }

        @Override
        public void setChanged() {
            slotsChanged(knifeContainer);
        }

        @Override
        public boolean stillValid(@NotNull Player player) {
            return true;
        }
    };

    public ChoppingBoardMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, ContainerLevelAccess.NULL);
    }

    public ChoppingBoardMenu(int containerId, Inventory inventory, ContainerLevelAccess access) {
        super(ModMenuTypes.CHOPPING_BOARD, containerId, inventory);
        this.access = access;
        this.player = inventory.player;
        // 菜刀槽
        this.addSlot(new KnifeInputSlot(this, this.knifeContainer, 0, 31, 26));
        // 输入槽
        this.addSlot(new Slot(this.craftSlots, 0, 70, 26));
        this.addSlot(new Slot(this.craftSlots, 1, 97, 26));
        this.addSlot(new Slot(this.craftSlots, 2, 124, 26));
        // 输出槽
        this.addSlot(new ChoppingBoardResultSlot(this.player, craftSlots, resultSlots, knifeContainer, 0, 97, 58));
        /*this.addSlot(new FurnaceResultSlot(inventory.player, container, 4, 97, 58));*/
        // 物品栏，以左上角为原点，距离左侧偏移8px，距离顶部84px（相对定位）
        this.addStandardInventorySlots(inventory, 8, 84);
    }

    /**
     * 菜板输出槽位
     */
    public Slot getResultSlot() {
        return this.slots.get(4);
    }

    protected static void slotChangedCraftingGrid(ChoppingBoardMenu menu, Level level, Player player, CraftingContainer craftSlots, ResultContainer resultSlots, Container knifeSlots, RecipeHolder<@NonNull ChoppingRecipe> recipe) {
        if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {
            if (knifeSlots.getItem(0).isEmpty()) return;

            ChoppingRecipeInput recipeInput = new ChoppingRecipeInput(craftSlots);

            ServerPlayer serverPlayer = (ServerPlayer) player;
            ItemStack itemStack = ItemStack.EMPTY;
            Optional<RecipeHolder<@NonNull ChoppingRecipe>> optional = serverLevel.recipeAccess().getRecipeFor(ModRecipeTypes.CHOPPING_RECIPE_TYPE, recipeInput, serverLevel);
            /*Optional<RecipeHolder<@NonNull ChoppingRecipe>> optional = RecipeManager.createCheck(ModRecipe.CHOPPING_RECIPE_TYPE).getRecipeFor(recipeInput, serverLevel);*/
            if (optional.isPresent()) {
                RecipeHolder<@NonNull ChoppingRecipe> recipeHolder = optional.get();
                ChoppingRecipe choppingRecipe = recipeHolder.value();
                if (resultSlots.setRecipeUsed(serverPlayer, recipeHolder)) {
                    ItemStack itemStack2 = choppingRecipe.assemble(recipeInput);
                    if (itemStack2.isItemEnabled(level.enabledFeatures())) {
                        itemStack = itemStack2;
                    }
                }
            }

            resultSlots.setItem(0, itemStack);
            menu.setRemoteSlot(0, itemStack);
            serverPlayer.connection.send(new ClientboundContainerSetSlotPacket(menu.containerId,  menu.incrementStateId(), 4, itemStack));
        }
    }

    @Override
    public void slotsChanged(@NotNull Container container) {
        this.access.execute((level, pos) ->
                slotChangedCraftingGrid(this, level, this.player, this.craftSlots, this.resultSlots, this.knifeContainer, null));
    }

    /**
     * 当玩家关闭容器时
     */
    @Override
    public void removed(@NotNull Player player) {
        super.removed(player);
        this.access.execute((level, pos) -> {
            this.clearContainer(player, this.craftSlots);
            this.clearContainer(player, this.knifeContainer);
        });
    }

    /**
     * 输入槽是否为菜刀
     */
    public boolean isKnife(ItemStack itemStack) {
        return itemStack.is(ModItemTags.KNIVES);
    }
}

package com.crystal.foodcraft.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @see net.minecraft.world.item.FlintAndSteelItem FlintAndSteelItem
 */
public class DarkBlueMultifunctionTools extends MultifunctionTool {

    private static final Logger log = LoggerFactory.getLogger(DarkBlueMultifunctionTools.class);

    public DarkBlueMultifunctionTools(Properties properties) {
        super(properties);
    }

    @Override
    public void onCraftedBy(@NotNull ItemStack itemStack, @NotNull Player player) {
        HolderLookup<Enchantment> lookup = player.level().holderLookup(Registries.ENCHANTMENT);
        Holder<Enchantment> efficiency = lookup.get(Enchantments.EFFICIENCY).orElseThrow();
        Holder<Enchantment> unbreaking = lookup.get(Enchantments.UNBREAKING).orElseThrow();
        Holder<Enchantment> fortune = lookup.get(Enchantments.FORTUNE).orElseThrow();
        ItemEnchantments.Mutable enchantment = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
        enchantment.set(efficiency, 6);
        enchantment.set(unbreaking, 6);
        enchantment.set(fortune, 3);
        itemStack.set(DataComponents.ENCHANTMENTS, enchantment.toImmutable());
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        BlockPos relative = pos.relative(context.getClickedFace());
        if (context.getClickedFace() == Direction.DOWN) return InteractionResult.FAIL;
        ItemStack itemStack = context.getItemInHand();
        if (!state.isAir() && state.canSurvive(level, relative)) {
            // 播放火把放置声音
            level.playSound(player, relative, SoundEvents.WOOD_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
            if (context.getClickedFace() == Direction.UP) {
                level.setBlock(relative, Blocks.TORCH.defaultBlockState(), 11);
            } else {
                BlockState torch = Blocks.WALL_TORCH.defaultBlockState();
                BlockState newTorch = torch.setValue(WallTorchBlock.FACING, context.getClickedFace());
                level.setBlock(relative, newTorch, 11);
            }
            level.gameEvent(player, GameEvent.BLOCK_PLACE, pos);
            if (player instanceof ServerPlayer serverPlayer) {
                CriteriaTriggers.PLACED_BLOCK.trigger(serverPlayer, relative, itemStack);
                itemStack.hurtAndBreak(1, player, context.getHand().asEquipmentSlot());
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }
}

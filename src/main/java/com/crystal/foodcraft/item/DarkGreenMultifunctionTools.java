package com.crystal.foodcraft.item;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class DarkGreenMultifunctionTools extends MultifunctionTool {

    public DarkGreenMultifunctionTools(Properties properties) {
        super(properties);
    }

    @Override
    public void onCraftedBy(@NotNull ItemStack itemStack, @NotNull Player player) {
        HolderLookup<Enchantment> lookup = player.level().holderLookup(Registries.ENCHANTMENT);
        Holder<Enchantment> efficiency = lookup.get(Enchantments.EFFICIENCY).orElseThrow();
        Holder<Enchantment> unbreaking = lookup.get(Enchantments.UNBREAKING).orElseThrow();
        Holder<Enchantment> silkTouch = lookup.get(Enchantments.SILK_TOUCH).orElseThrow();
        ItemEnchantments.Mutable enchantment = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
        enchantment.set(efficiency, 6);
        enchantment.set(unbreaking, 6);
        enchantment.set(silkTouch, 1);
        itemStack.set(DataComponents.ENCHANTMENTS, enchantment.toImmutable());
    }

    @Override
    public @NotNull InteractionResult use(Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        if (level.isClientSide()) return InteractionResult.PASS;
        ItemStack itemStack = player.getItemInHand(hand);
        if (player.getHealth() < player.getMaxHealth()) {
            // 玩家回血
            player.heal(1.0F);
            player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
            // 生成爱心粒子
            player.level().addParticle(
                    ParticleTypes.HEART,
                    player.getX(), player.getY() + 1, player.getZ(),
                    0, 0.1D, 0
            );
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }
}

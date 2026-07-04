package com.crystal.foodcraft.fluid;

import com.crystal.foodcraft.block.ModBlocks;
import com.crystal.foodcraft.item.ModItems;
import com.crystal.foodcraft.register.ModFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.WaterFluid;
import org.jetbrains.annotations.NotNull;

public abstract class CookingOilFluid extends WaterFluid {
    @Override
    public @NotNull Fluid getFlowing() {
        return ModFluids.COOKING_OIL_FLOWING;
    }

    @Override
    public @NotNull Fluid getSource() {
        return ModFluids.COOKING_OIL_STILL;
    }

    @Override
    public @NotNull Item getBucket() {
        return ModItems.PEANUT_OIL_BUCKET;
    }

    @Override
    public @NotNull BlockState createLegacyBlock(@NotNull FluidState fluidState) {
        return ModBlocks.COOKING_OIL.defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(fluidState));
    }

    @Override
    public boolean isSame(@NotNull Fluid other) {
        return other == ModFluids.COOKING_OIL_FLOWING || other == ModFluids.COOKING_OIL_STILL;
    }

    @Override
    public boolean canBeReplacedWith(@NotNull FluidState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull Fluid other, @NotNull Direction direction) {
        return direction == Direction.DOWN;
    }

    public static class Source extends CookingOilFluid {

        @Override
        public boolean isSource(@NotNull FluidState fluidState) {
            return true;
        }

        @Override
        public int getAmount(@NotNull FluidState fluidState) {
            return 8;
        }
    }

    public static class Flowing extends CookingOilFluid {

        @Override
        public boolean isSource(@NotNull FluidState fluidState) {
            return false;
        }

        @Override
        public int getAmount(@NotNull FluidState fluidState) {
            return fluidState.getValue(LEVEL);
        }

        /**
         * <p>必须保留super父类的方法，否则报异常</p>
         * @exception IllegalArgumentException 没有设置{@code BooleanProperty.FALLING}属性的值
         */
        @Override
        protected void createFluidStateDefinition(StateDefinition.@NotNull Builder<Fluid, @NotNull FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }
    }
}

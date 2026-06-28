package com.crystal.foodcraft.block.entity;

import com.crystal.foodcraft.FoodCraft;
import com.crystal.foodcraft.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.NotNull;

public class ModBlockEntities {
    public static final BlockEntityType<@NotNull PotBlockEntity> POT =
            register("pot", PotBlockEntity::new , ModBlocks.POT);
    public static final BlockEntityType<@NotNull PressureCookerBlockEntity> PRESSURE_COOKER =
            register("pressure_cooker", PressureCookerBlockEntity::new, ModBlocks.PRESSURE_COOKER);
    public static final BlockEntityType<@NotNull BrewBarrelBlockEntity> BREW_BARREL =
            register("brew_barrel", BrewBarrelBlockEntity::new, ModBlocks.BREW_BARREL);
    public static final BlockEntityType<@NotNull PanBlockEntity> PAN =
            register("pan", PanBlockEntity::new, ModBlocks.PAN);
    public static final BlockEntityType<@NotNull BMMachineBlockEntity> BEVERAGE_MAKING_MACHINE =
            register("beverage_making_machine", BMMachineBlockEntity::new , ModBlocks.BEVERAGE_MAKING_MACHINE);
    public static final BlockEntityType<@NotNull MillBlockEntity> MILL =
            register("mill", MillBlockEntity::new , ModBlocks.MILL);
    public static final BlockEntityType<@NotNull FryingMachineBlockEntity> FRYING_MACHINE =
            register("frying_machine", FryingMachineBlockEntity::new, ModBlocks.FRYING_MACHINE);
    public static final BlockEntityType<@NotNull StoveBlockEntity> STOVE =
            register("stove", StoveBlockEntity::new, ModBlocks.STOVE);

    private static <T extends BlockEntity> BlockEntityType<T> register(String name, FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory, Block... blocks) {
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, FoodCraft.of(name), FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
    }

    public static void init() {

    }
}

package com.crystal.foodcraft.screenhandler;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.jetbrains.annotations.NotNull;

public final class ModMenuTypes {
    public static final MenuType<@NotNull ChoppingBoardMenu> CHOPPING_BOARD = register("chopping_board", ChoppingBoardMenu::new);
    public static final MenuType<@NotNull PotMenu> POT = register("pot", PotMenu::new);
    public static final MenuType<@NotNull PressureCookerMenu> PRESSURE_COOKER = register("pressure_cooker", PressureCookerMenu::new);
    public static final MenuType<@NotNull BrewBarrelMenu> BREW_BARREL = register("brew_barrel", BrewBarrelMenu::new);
    public static final MenuType<@NotNull PanMenu> PAN = register("pan", PanMenu::new);
    public static final MenuType<@NotNull BeverageMakingMachineMenu> BEVERAGE_MAKING_MACHINE = register("beverage_making_machine", BeverageMakingMachineMenu::new);
    public static final MenuType<@NotNull MillMenu> MILL = register("mill", MillMenu::new);
    public static final MenuType<@NotNull FryingMachineMenu> FRYING_MACHINE = register("frying_machine", FryingMachineMenu::new);
    public static final MenuType<@NotNull StoveMenu> STOVE = register("stove", StoveMenu::new);

    public static <T extends AbstractContainerMenu> MenuType<@NotNull T> register(String name, MenuType.MenuSupplier<@NotNull T> constructor) {
        return Registry.register(BuiltInRegistries.MENU, name, new MenuType<>(constructor, FeatureFlagSet.of()));
    }
}

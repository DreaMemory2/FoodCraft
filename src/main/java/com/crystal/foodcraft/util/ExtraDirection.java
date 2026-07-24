package com.crystal.foodcraft.util;

import net.minecraft.core.Vec3i;

public enum ExtraDirection {
    DOWN("down", new Vec3i(0, -1, 0)),
    UP("up", new Vec3i(0, 1, 0)),
    NORTH("north", new Vec3i(0, 0, -1)),
    SOUTH("south", new Vec3i(0, 0, 1)),
    WEST("west", new Vec3i(-1, 0, 0)),
    EAST("east", new Vec3i(1, 0, 0)),
    // 西北左上角
    NORTHWEST_UP("northwest_up", new Vec3i(-1, 1, -1)),
    // 西北左下角
    NORTHWEST_DOWN("northwest_down", new Vec3i(-1, -1, -1)),
    // 东北左上角
    NORTHEAST_UP("northeast_up", new Vec3i(1, 1, -1)),
    // 东北左下角
    NORTHEAST_DOWN("northeast_down", new Vec3i(1, -1, -1)),
    // 东南左上角
    SOUTHEAST_UP("southeast_up", new Vec3i(1, 1, 1)),
    // 东南左下角
    SOUTHEAST_DOWN("southeast_down", new Vec3i(1, -1, 1)),
    // 西南左上角
    SOUTHWEST_UP("southwest_up", new Vec3i(-1, 1, 1)),
    // 西南左下角
    SOUTHWEST_DOWN("southwest_down", new Vec3i(-1, -1, 1));
    private final String name;
    private final Vec3i normal;

    ExtraDirection(String name, Vec3i normal) {
        this.name = name;
        this.normal = normal;
    }

    public int getStepX() {
        return this.normal.getX();
    }

    public int getStepY() {
        return this.normal.getY();
    }

    public int getStepZ() {
        return this.normal.getZ();
    }

    public Vec3i getVec3i() {
        return normal;
    }

    public String getName() {
        return name;
    }
}

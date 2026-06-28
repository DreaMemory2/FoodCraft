package com.crystal.foodcraft.network;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SyncFluidTank extends SingleVariantStorage<@NotNull FluidVariant> {
    @Nullable
    private Runnable onContentsChanged;
    private final long capacity;

    public SyncFluidTank(long capacity) {
        this.capacity = capacity;
    }

    @Override
    protected FluidVariant getBlankVariant() {
        return FluidVariant.blank();
    }

    @Override
    protected long getCapacity(FluidVariant variant) {
        return this.capacity;
    }

    @Override
    protected void onFinalCommit() {
        if (onContentsChanged != null && getBlankVariant().isBlank()) {
            onContentsChanged.run();
        }
    }

    public void setOnContentsChanged(@Nullable Runnable onContentsChanged) {
        this.onContentsChanged = onContentsChanged;
    }
}

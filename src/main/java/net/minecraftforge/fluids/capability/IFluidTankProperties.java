package net.minecraftforge.fluids.capability;

import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;

public interface IFluidTankProperties {

    @Nullable
    FluidStack getContents();

    int getCapacity();

    boolean canFill();

    boolean canDrain();

}

package net.minecraftforge.fluids.capability;

import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;

public interface IFluidHandler {

    int fill(FluidStack resource, boolean doFill);

    @Nullable
    FluidStack drain(int maxDrain, boolean doDrain);

    @Nullable
    FluidStack drain(FluidStack resource, boolean doDrain);

    IFluidTankProperties[] getTankProperties();

}

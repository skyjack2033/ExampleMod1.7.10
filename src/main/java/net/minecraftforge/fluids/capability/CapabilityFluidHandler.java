package net.minecraftforge.fluids.capability;

import net.minecraftforge.common.capabilities.Capability;

public class CapabilityFluidHandler {

    public static final Capability<IFluidHandler> FLUID_HANDLER_CAPABILITY = new Capability<>("fluid_handler");

    private CapabilityFluidHandler() {
    }

}

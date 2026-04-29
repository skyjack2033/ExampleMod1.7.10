package net.minecraftforge.fluids.capability;

import net.minecraft.item.ItemStack;

public interface IFluidHandlerItem extends IFluidHandler {

    ItemStack getContainer();

}

package com.glodblock.github.coremod;

import appeng.api.storage.data.IAEItemStack;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

public class CoreModHooks {

    public static boolean shouldRegisterFake(ItemStack stack) {
        return false;
    }

    public static double getFluidSize(IAEItemStack stack) {
        return 0.0;
    }

    public static ItemStack removeFluidPackets(InventoryCrafting ic, int slot) {
        return ic.getStackInSlot(slot);
    }

    public static IAEItemStack wrapFluidPacketStack(IAEItemStack stack) {
        return stack;
    }

}

package com.glodblock.github.common.item.fake;

import net.minecraft.item.ItemStack;

public class FakeItemRegister {

    public static void registerFake(ItemStack stack) {
    }

    public static boolean isFake(ItemStack stack) {
        return false;
    }

    public static Object getStack(ItemStack stack) {
        return null;
    }

}

package github.kasuminova.mmce.client.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public final class ItemStackUtils {

    private ItemStackUtils() {
    }

    public static boolean isEmpty(ItemStack stack) {
        return stack == null || stack.stackSize <= 0;
    }

    public static String getDisplayName(ItemStack stack) {
        if (isEmpty(stack)) {
            return "";
        }
        return stack.getDisplayName();
    }

    public static NBTTagCompound writeNBTOversize(ItemStack stack) {
        NBTTagCompound tag = new NBTTagCompound();
        stack.writeToNBT(tag);
        return tag;
    }

    public static ItemStack readNBTOversize(NBTTagCompound tag) {
        return ItemStack.loadItemStackFromNBT(tag);
    }

}

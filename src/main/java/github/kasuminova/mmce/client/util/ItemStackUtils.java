package github.kasuminova.mmce.client.util;

import net.minecraft.item.ItemStack;

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

}

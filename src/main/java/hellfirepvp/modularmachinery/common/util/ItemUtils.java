package hellfirepvp.modularmachinery.common.util;

import net.minecraft.item.ItemStack;

public final class ItemUtils {

    private ItemUtils() {
    }

    public static int getItemDamage(ItemStack stack) {
        if (stack == null) {
            return 0;
        }
        return stack.getItemDamage();
    }
}

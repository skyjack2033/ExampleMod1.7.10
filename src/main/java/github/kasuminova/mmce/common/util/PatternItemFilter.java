package github.kasuminova.mmce.common.util;

import net.minecraft.item.ItemStack;

public interface PatternItemFilter {

    boolean filter(ItemStack stack);

}

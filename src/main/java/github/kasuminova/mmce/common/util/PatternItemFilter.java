package github.kasuminova.mmce.common.util;

import appeng.util.inv.filter.IAEItemFilter;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class PatternItemFilter implements IAEItemFilter {

    public static final PatternItemFilter INSTANCE = new PatternItemFilter();

    private PatternItemFilter() {
    }

    public boolean filter(ItemStack stack) {
        return true;
    }

    @Override
    public boolean allowExtract(IItemHandler inv, int slot, int amount) {
        return true;
    }

    @Override
    public boolean allowInsert(IItemHandler inv, int slot, ItemStack stack) {
        return stack != null && stack.stackSize > 0;
    }

}

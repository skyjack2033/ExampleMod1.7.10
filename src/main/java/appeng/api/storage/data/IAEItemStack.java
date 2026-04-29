package appeng.api.storage.data;

import net.minecraft.item.ItemStack;

public interface IAEItemStack {
    ItemStack getItemStack();
    long getStackSize();
    void setStackSize(long size);
    IAEItemStack copy();
}

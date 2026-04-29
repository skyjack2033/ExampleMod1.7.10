package appeng.api.storage.data;

import net.minecraft.item.ItemStack;

public interface IAEItemStack extends IAEStack<IAEItemStack> {
    ItemStack getItemStack();
    boolean hasTagCompound();
    void add(IAEItemStack other);
    IAEItemStack copy();
    net.minecraft.item.Item getItem();
    int getItemDamage();
    boolean sameOre(IAEItemStack other);
    boolean isSameType(IAEItemStack other);
    boolean isSameType(ItemStack other);
}

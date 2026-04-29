package appeng.util.inv;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public interface IAEAppEngInventory {
    void onChangeInventory(IItemHandler inv, int slot, InvOperation op, ItemStack removed, ItemStack added);
    int getInventoryStackLimit();
}

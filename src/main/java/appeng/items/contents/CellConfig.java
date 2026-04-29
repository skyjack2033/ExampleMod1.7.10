package appeng.items.contents;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.items.IItemHandler;

public class CellConfig implements IItemHandler {
    private final ItemStack cell;

    public CellConfig(ItemStack cell) {
        this.cell = cell;
    }

    @Override public int getSlots() { return 0; }
    @Override public ItemStack getStackInSlot(int slot) { return null; }
    @Override public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) { return stack; }
    @Override public ItemStack extractItem(int slot, int amount, boolean simulate) { return null; }
    @Override public int getSlotLimit(int slot) { return 0; }
    @Override public boolean isItemValid(int slot, ItemStack stack) { return false; }
}

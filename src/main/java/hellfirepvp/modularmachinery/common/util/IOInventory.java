package hellfirepvp.modularmachinery.common.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class IOInventory implements IItemHandler {

    private final ItemStack[] inventory;

    public IOInventory(int size) {
        this.inventory = new ItemStack[size];
    }

    public void setStackInSlot(int slot, ItemStack stack) {
        if (slot >= 0 && slot < inventory.length) {
            inventory[slot] = stack;
        }
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        if (slot >= 0 && slot < inventory.length) {
            return inventory[slot];
        }
        return null;
    }

    @Override
    public int getSlots() {
        return inventory.length;
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        return stack;
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        ItemStack stackInSlot = getStackInSlot(slot);
        if (stackInSlot == null) {
            return null;
        }
        return stackInSlot.copy();
    }

    @Override
    public int getSlotLimit(int slot) {
        return 64;
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        return true;
    }
}

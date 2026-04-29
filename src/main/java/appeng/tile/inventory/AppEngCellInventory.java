package appeng.tile.inventory;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import appeng.api.storage.ICellInventoryHandler;
import appeng.util.inv.IAEAppEngInventory;
import appeng.util.inv.filter.IAEItemFilter;

public class AppEngCellInventory implements IItemHandler {

    public AppEngCellInventory(IAEAppEngInventory inventory, int slots) {
    }

    @Override
    public int getSlots() {
        return 0;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return null;
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        return stack;
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return null;
    }

    @Override
    public int getSlotLimit(int slot) {
        return 64;
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        return true;
    }

    public void setHandler(int slot, ICellInventoryHandler handler) {
    }

    public void setFilter(IAEItemFilter filter) {
    }
}

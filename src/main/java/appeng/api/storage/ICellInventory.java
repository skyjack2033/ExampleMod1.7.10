package appeng.api.storage;

import appeng.api.config.Actionable;
import appeng.api.config.FuzzyMode;
import appeng.api.networking.security.BaseActionSource;
import appeng.api.storage.data.IAEStack;
import appeng.api.storage.data.IItemList;
import appeng.tile.inventory.IAEStackInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public interface ICellInventory<StackType extends IAEStack> extends IMEInventory<StackType> {
    ItemStack getItemStack();
    double getIdleDrain();
    FuzzyMode getFuzzyMode();
    IInventory getConfigInventory();
    IAEStackInventory getConfigAEInventory();
    IInventory getUpgradesInventory();
    int getBytesPerType();
    boolean canHoldNewItem();
    long getTotalBytes();
    long getFreeBytes();
    long getUsedBytes();
    long getTotalItemTypes();
    long getStoredItemCount();
    long getStoredItemTypes();
    long getRemainingItemTypes();
    long getRemainingItemCount();
    long getRemainingItemsCountDist(StackType item);
    int getUnusedItemCount();
    int getStatusForCell();
    String getOreFilter();
}

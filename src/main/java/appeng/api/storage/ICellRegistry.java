package appeng.api.storage;

import net.minecraft.item.ItemStack;
import appeng.api.storage.data.IAEItemStack;
import appeng.api.storage.data.ISaveProvider;
import java.util.List;

public interface ICellRegistry {
    void addCellHandler(ICellHandler handler);
    ICellInventoryHandler getCellInventory(IAEItemStack stack, ISaveProvider saveProvider, IStorageChannel channel);
    ICellInventoryHandler getCellInventory(IAEItemStack stack, ISaveProvider saveProvider, IStorageChannel channel, IAccessType accessType);
    ICellHandler getHandler(ItemStack stack);
    List<ICellHandler> getHandlerNames();
    int getHandlerCount();
    List<ICellHandler> handlers();
}

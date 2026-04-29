package appeng.core.features.registries.cell;

import net.minecraft.item.ItemStack;
import appeng.api.storage.IAccessType;
import appeng.api.storage.ICellHandler;
import appeng.api.storage.ICellInventoryHandler;
import appeng.api.storage.ICellRegistry;
import appeng.api.storage.IStorageChannel;
import appeng.api.storage.data.IAEItemStack;
import appeng.api.storage.data.ISaveProvider;

import java.util.ArrayList;
import java.util.List;

public class CellRegistry implements ICellRegistry {

    private List<ICellHandler> handlers = new ArrayList<>();

    public CellRegistry(ICellHandler handler) {
        this.handlers.add(handler);
    }

    @Override
    public void addCellHandler(ICellHandler handler) {
    }

    @Override
    public ICellInventoryHandler getCellInventory(IAEItemStack stack, ISaveProvider saveProvider, IStorageChannel channel) {
        return null;
    }

    @Override
    public ICellInventoryHandler getCellInventory(IAEItemStack stack, ISaveProvider saveProvider, IStorageChannel channel, IAccessType accessType) {
        return null;
    }

    @Override
    public ICellHandler getHandler(ItemStack stack) {
        return null;
    }

    @Override
    public List<ICellHandler> getHandlerNames() {
        return new ArrayList<ICellHandler>();
    }

    @Override
    public int getHandlerCount() {
        return 0;
    }

    @Override
    public List<ICellHandler> handlers() {
        return handlers;
    }
}

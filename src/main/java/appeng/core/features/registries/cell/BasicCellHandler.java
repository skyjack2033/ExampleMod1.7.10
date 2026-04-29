package appeng.core.features.registries.cell;

import appeng.api.implementations.tiles.IChestOrDrive;
import appeng.api.storage.ICellHandler;
import appeng.api.storage.ICellInventoryHandler;
import appeng.api.storage.IMEInventory;
import appeng.api.storage.IMEInventoryHandler;
import appeng.api.storage.ISaveProvider;
import appeng.api.storage.StorageChannel;
import appeng.api.storage.data.IAEStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BasicCellHandler implements ICellHandler {
    
    public boolean isCell(ItemStack is) { return false; }

    
    public IIcon getTopTexture_Light() { return null; }

    
    public IIcon getTopTexture_Medium() { return null; }

    
    public IIcon getTopTexture_Dark() { return null; }

    
    public void openChestGui(EntityPlayer player, IChestOrDrive chest, ICellHandler cellHandler, IMEInventoryHandler inv, ItemStack is, StorageChannel chan) {
    }

    
    public int getStatusForCell(ItemStack is, IMEInventory handler) { return 0; }

    
    public double cellIdleDrain(ItemStack is, IMEInventory handler) { return 0; }

    public IMEInventoryHandler getCellInventory(ItemStack is, ISaveProvider container, StorageChannel channel) {
        return null;
    }

    public ICellInventoryHandler getCellInventory(ItemStack is, ISaveProvider host, StorageChannel channel, int accessType) {
        return null;
    }

    public <T extends IAEStack> IMEInventoryHandler<T> getCellInventory(ItemStack is, ISaveProvider container, StorageChannel channel, Class<T> type) {
        return null;
    }
}

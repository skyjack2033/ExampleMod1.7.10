package appeng.me.helpers;

import appeng.api.networking.GridFlags;
import appeng.api.networking.IGrid;
import appeng.api.networking.IGridNode;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Stub for 1.7.10 port
 */
public class AENetworkProxy {
    public AENetworkProxy() {
    }

    public AENetworkProxy(Object o, String s, ItemStack is, boolean b) {
    }

    public IGrid getGrid() {
        return null;
    }

    public void setIdlePowerUsage(double d) {
    }

    public void setFlags(GridFlags... flags) {
    }

    public boolean isActive() {
        return false;
    }

    public IGridNode getNode() {
        return null;
    }

    public void onReady() {
    }

    public void readFromNBT(NBTTagCompound compound) {
    }

    public void writeToNBT(NBTTagCompound compound) {
    }

    public void onChunkUnload() {
    }

    public void invalidate() {
    }

    public void setVisualRepresentation(ItemStack is) {
    }
}

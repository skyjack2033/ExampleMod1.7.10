package appeng.api.storage.data;

import net.minecraft.nbt.NBTTagCompound;

public interface IAEStack {
    long getStackSize();
    void setStackSize(long size);
    <T extends IAEStack> T copy();
    void writeToNBT(NBTTagCompound tag);
    void setCraftable(boolean craftable);
    boolean isCraftable();
}

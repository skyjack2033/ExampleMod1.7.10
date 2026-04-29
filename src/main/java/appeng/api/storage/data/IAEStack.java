package appeng.api.storage.data;

import net.minecraft.nbt.NBTTagCompound;

public interface IAEStack<StackType extends IAEStack> {
    long getStackSize();
    StackType setStackSize(long size);
    boolean isCraftable();
    StackType setCraftable(boolean craftable);
    void writeToNBT(NBTTagCompound tag);
    StackType copy();
    void add(StackType other);
    void incStackSize(long amount);
    void decStackSize(long amount);
    StackType reset();
    boolean isMeaningful();
    long getCountRequestable();
    StackType setCountRequestable(long count);
    void incCountRequestable(long amount);
    void decCountRequestable(long amount);
}

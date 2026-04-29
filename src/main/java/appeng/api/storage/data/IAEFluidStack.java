package appeng.api.storage.data;

public interface IAEFluidStack {
    long getStackSize();
    void setStackSize(long size);
    IAEFluidStack copy();
}

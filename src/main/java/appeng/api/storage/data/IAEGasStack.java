package appeng.api.storage.data;

public interface IAEGasStack extends IAEStack {
    long getStackSize();
    void setStackSize(long size);
    IAEGasStack copy();
}

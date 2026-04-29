package github.kasuminova.ecoaeextension.common.item.estorage;

import appeng.api.storage.StorageChannel;
import appeng.api.storage.data.IAEFluidStack;
import appeng.tile.inventory.AppEngInternalInventory;
import github.kasuminova.ecoaeextension.ECOAEExtension;
import github.kasuminova.ecoaeextension.common.block.ecotech.estorage.prop.DriveStorageLevel;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class EStorageCellFluid extends EStorageCell<IAEFluidStack> {

    public static final EStorageCellFluid LEVEL_A = new EStorageCellFluid(DriveStorageLevel.A, 16, 4);
    public static final EStorageCellFluid LEVEL_B = new EStorageCellFluid(DriveStorageLevel.B, 64, 16);
    public static final EStorageCellFluid LEVEL_C = new EStorageCellFluid(DriveStorageLevel.C, 256, 64);

    public EStorageCellFluid(final DriveStorageLevel level, final int millionBytes, final int byteMultiplier) {
        super(level, millionBytes, byteMultiplier);
        setUnlocalizedName(ECOAEExtension.MOD_ID + '.' + "estorage_cell_fluid_" + millionBytes + "m");
    }

    @Override
    public int getTotalTypes(@Nonnull final ItemStack cellItem) {
        return 25;
    }

    @Override
    public int getBytesPerType(@Nonnull final ItemStack cellItem) {
        return byteMultiplier * 1024;
    }

    @Override
    public int BytePerType(@Nonnull final ItemStack cellItem) {
        return byteMultiplier * 1024;
    }

    @Override
    public AppEngInternalInventory getConfigInventory(final ItemStack is) {
        return null;
    }

    @Nonnull
    @Override
    public StorageChannel getChannel() {
        return StorageChannel.FLUIDS;
    }
}

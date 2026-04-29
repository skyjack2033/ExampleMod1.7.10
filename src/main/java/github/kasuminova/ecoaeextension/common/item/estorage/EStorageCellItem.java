package github.kasuminova.ecoaeextension.common.item.estorage;

import appeng.api.storage.StorageChannel;
import appeng.api.storage.data.IAEItemStack;
import github.kasuminova.ecoaeextension.ECOAEExtension;
import github.kasuminova.ecoaeextension.common.block.ecotech.estorage.prop.DriveStorageLevel;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class EStorageCellItem extends EStorageCell<IAEItemStack> {

    public static final EStorageCellItem LEVEL_A = new EStorageCellItem(DriveStorageLevel.A, 16, 4);
    public static final EStorageCellItem LEVEL_B = new EStorageCellItem(DriveStorageLevel.B, 64, 16);
    public static final EStorageCellItem LEVEL_C = new EStorageCellItem(DriveStorageLevel.C, 256, 64);

    public EStorageCellItem(final DriveStorageLevel level, final int millionBytes, final int byteMultiplier) {
        super(level, millionBytes, byteMultiplier);
        setUnlocalizedName(ECOAEExtension.MOD_ID + '.' + "estorage_cell_item_" + millionBytes + "m");
    }

    @Override
    public int getTotalTypes(@Nonnull final ItemStack cellItem) {
        return 315;
    }

    @Override
    public int BytePerType(@Nonnull final ItemStack cellItem) {
        return byteMultiplier * 1024;
    }

    @Nonnull
    @Override
    public StorageChannel getChannel() {
        return StorageChannel.ITEMS;
    }
}

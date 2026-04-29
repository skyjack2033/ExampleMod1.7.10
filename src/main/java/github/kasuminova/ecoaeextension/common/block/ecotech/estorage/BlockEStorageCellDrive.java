package github.kasuminova.ecoaeextension.common.block.ecotech.estorage;

import appeng.api.storage.ICellInventoryHandler;
import appeng.api.storage.StorageChannel;
import appeng.api.storage.data.IAEStack;
import appeng.tile.inventory.AppEngCellInventory;
import appeng.util.helpers.ItemHandlerUtil;
import github.kasuminova.ecoaeextension.ECOAEExtension;
import github.kasuminova.ecoaeextension.common.block.ecotech.estorage.prop.DriveStatus;
import github.kasuminova.ecoaeextension.common.block.ecotech.estorage.prop.DriveStorageCapacity;
import github.kasuminova.ecoaeextension.common.block.ecotech.estorage.prop.DriveStorageLevel;
import github.kasuminova.ecoaeextension.common.block.ecotech.estorage.prop.DriveStorageType;
import github.kasuminova.ecoaeextension.common.block.prop.FacingProp;
import github.kasuminova.ecoaeextension.common.core.CreativeTabNovaEng;
import github.kasuminova.ecoaeextension.common.estorage.EStorageCellHandler;
import github.kasuminova.ecoaeextension.common.item.estorage.EStorageCell;
import github.kasuminova.ecoaeextension.common.tile.ecotech.estorage.EStorageCellDrive;
import github.kasuminova.ecoaeextension.common.util.EnumFacingCompat;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

@SuppressWarnings("deprecation")
public class BlockEStorageCellDrive extends BlockEStoragePart {

    public static final BlockEStorageCellDrive INSTANCE = new BlockEStorageCellDrive();

    protected BlockEStorageCellDrive() {
        super(Material.iron);
        this.setHardness(20.0F);
        this.setResistance(2000.0F);
        this.setStepSound(Block.soundTypeMetal);
        this.setHarvestLevel("pickaxe", 2);
        this.setCreativeTab(CreativeTabNovaEng.INSTANCE);
        this.setDefaultState(this.stateContainer.getBaseState()
                .withProperty(FacingProp.HORIZONTALS, ForgeDirection.NORTH)
                .withProperty(DriveStorageType.STORAGE_TYPE, DriveStorageType.EMPTY)
                .withProperty(DriveStorageLevel.STORAGE_LEVEL, DriveStorageLevel.EMPTY)
                .withProperty(DriveStorageCapacity.STORAGE_CAPACITY, DriveStorageCapacity.EMPTY)
                .withProperty(DriveStatus.STATUS, DriveStatus.IDLE)
        );
        this.setBlockName(ECOAEExtension.MOD_ID + '.' + "estorage_cell_drive");
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(@Nonnull final World world, final int meta) {
        return new EStorageCellDrive();
    }

    @Override
    public void breakBlock(final World worldIn, final int x, final int y, final int z, final Block block, final int meta) {
        TileEntity te = worldIn.getTileEntity(x, y, z);
        if (te instanceof EStorageCellDrive) {
            EStorageCellDrive drive = (EStorageCellDrive) te;
            AppEngCellInventory inv = drive.getDriveInv();
            for (int i = 0; i < inv.getSlots(); i++) {
                ItemStack stack = inv.getStackInSlot(i);
                if (stack != null && stack.stackSize > 0) {
                    dropBlockAsItem(worldIn, x, y, z, stack);
                    ItemHandlerUtil.setStackInSlot(inv, i, null);
                }
            }
        }

        super.breakBlock(worldIn, x, y, z, block, meta);
    }

    @Override
    public int getLightValue(@Nonnull final IBlockAccess world, final int x, final int y, final int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof EStorageCellDrive) {
            EStorageCellDrive drive = (EStorageCellDrive) te;
            AppEngCellInventory driveInv = drive.getDriveInv();
            ItemStack stack = driveInv.getStackInSlot(0);
            return (stack != null && stack.stackSize > 0) ? 6 : 2;
        }
        return 2;
    }

    @SuppressWarnings("rawtypes")
    public IBlockState getActualState(@Nonnull final IBlockState state, @Nonnull final IBlockAccess world, final int x, final int y, final int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (!(te instanceof EStorageCellDrive)) {
            return state;
        }
        EStorageCellDrive drive = (EStorageCellDrive) te;
        AppEngCellInventory driveInv = drive.getDriveInv();
        ItemStack stack = driveInv.getStackInSlot(0);
        if (stack.stackSize <= 0) {
            return state;
        }

        EStorageCellHandler handler = EStorageCellHandler.getHandler(stack);
        if (handler == null) {
            return state;
        }

        EStorageCell<?> cell = (EStorageCell<?>) stack.getItem();
        DriveStorageLevel level = cell.getLevel();
        DriveStorageType type = EStorageCellDrive.getCellType(cell);
        if (type == null) {
            return state;
        }

        ICellInventoryHandler cellInventory = null;
        for (final StorageChannel channel : new StorageChannel[]{StorageChannel.ITEMS, StorageChannel.FLUIDS}) {
            cellInventory = handler.getCellInventory(stack, drive, channel);
            if (cellInventory != null) {
                break;
            }
        }

        if (cellInventory == null) {
            return state;
        }

        return state.withProperty(DriveStorageLevel.STORAGE_LEVEL, level)
                .withProperty(DriveStorageType.STORAGE_TYPE, type)
                .withProperty(DriveStatus.STATUS, drive.isWriting() ? DriveStatus.RUN : DriveStatus.IDLE)
                .withProperty(DriveStorageCapacity.STORAGE_CAPACITY, EStorageCellDrive.getCapacity(cellInventory));
    }

    public IBlockState getStateFromMeta(final int meta) {
        return getDefaultState().withProperty(FacingProp.HORIZONTALS, EnumFacingCompat.byHorizontalIndex(meta));
    }

    public int getMetaFromState(@Nonnull final IBlockState state) {
        return EnumFacingCompat.toHorizontalIndex(state.getValue(FacingProp.HORIZONTALS));
    }

    @Override
    public void onBlockPlacedBy(@Nonnull final World world,
                                final int x, final int y, final int z,
                                @Nonnull final EntityLivingBase placer,
                                @Nonnull final ItemStack stack)
    {
        int dir = MathHelper.floor_double((double) (placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        dir = (dir + 2) & 3;
        world.setBlockMetadataWithNotify(x, y, z, dir, 2);
    }

    @Nonnull
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this,
                FacingProp.HORIZONTALS,
                DriveStorageType.STORAGE_TYPE,
                DriveStorageLevel.STORAGE_LEVEL,
                DriveStorageCapacity.STORAGE_CAPACITY,
                DriveStatus.STATUS
        );
    }

}

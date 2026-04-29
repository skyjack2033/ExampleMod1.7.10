package github.kasuminova.ecoaeextension.common.block.ecotech.estorage;

import github.kasuminova.ecoaeextension.ECOAEExtension;
import github.kasuminova.ecoaeextension.common.block.ecotech.estorage.prop.EnergyCellStatus;
import github.kasuminova.ecoaeextension.common.core.CreativeTabNovaEng;
import github.kasuminova.ecoaeextension.common.tile.ecotech.estorage.EStorageEnergyCell;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings("deprecation")
public class BlockEStorageEnergyCell extends BlockEStoragePart {
    public static final BlockEStorageEnergyCell L4 = new BlockEStorageEnergyCell("l4", 10_000_000D);
    public static final BlockEStorageEnergyCell L6 = new BlockEStorageEnergyCell("l6", 100_000_000D);
    public static final BlockEStorageEnergyCell L9 = new BlockEStorageEnergyCell("l9", 1_000_000_000D);

    protected final double maxEnergyStore;

    protected BlockEStorageEnergyCell(final String level, double maxEnergyStore) {
        super(Material.iron);
        this.setHardness(20.0F);
        this.setResistance(2000.0F);
        this.setStepSound(Block.soundTypeMetal);
        this.setHarvestLevel("pickaxe", 2);
        this.setCreativeTab(CreativeTabNovaEng.INSTANCE);
        this.setBlockName(ECOAEExtension.MOD_ID + '.' + "estorage_energy_cell_" + level);
        this.maxEnergyStore = maxEnergyStore;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(@Nonnull final World world, final int meta) {
        return new EStorageEnergyCell(maxEnergyStore);
    }

    @Override
    public int getLightValue(@Nonnull final IBlockAccess world, final int x, final int y, final int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof EStorageEnergyCell) {
            EStorageEnergyCell cell = (EStorageEnergyCell) te;
            return EStorageEnergyCell.getStatusFromFillFactor(cell.getFillFactor()).ordinal() * 2;
        }
        return 0;
    }

    @Override
    public void dropBlockAsItemWithChance(@Nonnull final World worldIn, final int x, final int y, final int z, final int meta, final float chance, final int fortune) {
    }

    @Override
    public void breakBlock(final World world, final int x, final int y, final int z, final Block block, final int meta) {
        ItemStack dropped = new ItemStack(Item.getItemFromBlock(this));
        if (dropped.stackSize <= 0) {
            dropBlockAsItem(world, x, y, z, dropped);
            world.removeTileEntity(x, y, z);
            return;
        }
        TileEntity te = world.getTileEntity(x, y, z);
        if (!(te instanceof EStorageEnergyCell)) {
            dropBlockAsItem(world, x, y, z, dropped);
            world.removeTileEntity(x, y, z);
            return;
        }
        EStorageEnergyCell cell = (EStorageEnergyCell) te;

        NBTTagCompound tag = new NBTTagCompound();
        cell.writeCustomNBT(tag);
        cell.setEnergyStored(0D);
        dropped.setTagCompound(tag);
        dropBlockAsItem(world, x, y, z, dropped);
        world.removeTileEntity(x, y, z);
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

        NBTTagCompound tag = stack.getTagCompound();
        if (tag != null && tag.hasKey("energyStored") && tag.hasKey("maxEnergyStore")) {
            TileEntity te = world.getTileEntity(x, y, z);
            if (te instanceof EStorageEnergyCell) {
                EStorageEnergyCell cell = (EStorageEnergyCell) te;
                cell.readCustomNBT(tag);
            }
        }
    }

}

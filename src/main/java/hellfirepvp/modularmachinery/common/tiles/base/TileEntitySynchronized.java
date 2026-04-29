package hellfirepvp.modularmachinery.common.tiles.base;

import github.kasuminova.ecoaeextension.common.util.BlockPos;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;

public class TileEntitySynchronized extends TileEntity {

    protected boolean loaded = false;

    public void readCustomNBT(NBTTagCompound compound) {
        // Stub: no custom NBT to read.
    }

    public void writeCustomNBT(NBTTagCompound compound) {
        // Stub: no custom NBT to write.
    }


    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        readCustomNBT(compound);
    }


    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        writeCustomNBT(compound);
    }

    public void markForUpdate() {
        markDirty();
        if (worldObj != null) {
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
    }

    public void markForUpdateSync() {
        markDirty();
        if (worldObj != null) {
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
    }


    public World getWorld() {
        return worldObj;
    }

    public BlockPos getPos() {
        return new BlockPos(xCoord, yCoord, zCoord);
    }


    public Packet getDescriptionPacket() {
        NBTTagCompound compound = new NBTTagCompound();
        writeToNBT(compound);
        return null; // Stub: no network packet implemented.
    }

    public void onLoad() {
        loaded = true;
    }

    public void updateContainingBlockInfo() {
        super.updateContainingBlockInfo();
        final World world = getWorld();
        // Stub: block state update (1.8+ API, not available in 1.7.10)
        if (FMLCommonHandler.instance().getEffectiveSide().isClient() && worldObj != null) {
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
    }
}

package github.kasuminova.ecoaeextension.common.tile.ecotech.ecalculator;

import github.kasuminova.ecoaeextension.common.block.ecotech.ecalculator.BlockECalculatorCellDrive;
import github.kasuminova.ecoaeextension.common.block.ecotech.ecalculator.BlockECalculatorTransmitterBus;
import github.kasuminova.ecoaeextension.common.block.ecotech.ecalculator.prop.Levels;
import github.kasuminova.ecoaeextension.common.block.ecotech.ecalculator.prop.TransmitterBusLinkLevel;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import github.kasuminova.ecoaeextension.common.util.BlockPos;
import net.minecraft.world.World;

public class ECalculatorTransmitterBus extends ECalculatorPart {

    protected boolean upConnected = false;
    protected boolean downConnected = false;

    @Override
    public void onAssembled() {
        super.onAssembled();
        connectCellDrives();
    }

    @Override
    public void onDisassembled() {
        super.onDisassembled();
        disconnectCellDrives();
    }

    protected void connectCellDrives() {
        boolean changed = false;

        if (connectCellDrive(ForgeDirection.UP)) {
            if (!this.upConnected) {
                changed = true;
                this.upConnected = true;
            }
        } else if (this.upConnected) {
            changed = true;
            this.upConnected = false;
        }
        if (connectCellDrive(ForgeDirection.DOWN)) {
            if (!this.downConnected) {
                changed = true;
                this.downConnected = true;
            }
        } else if (this.downConnected) {
            changed = true;
            this.downConnected = false;
        }

        if (changed) {
            markForUpdateSync();
        }
    }

    protected void disconnectCellDrives() {
        boolean changed = false;

        if (this.upConnected && disconnectCellDrive(ForgeDirection.UP)) {
            changed = true;
            this.upConnected = false;
        }
        if (this.downConnected && disconnectCellDrive(ForgeDirection.DOWN)) {
            changed = true;
            this.downConnected = false;
        }

        if (changed) {
            markForUpdateSync();
        }
    }

    protected boolean disconnectCellDrive(final ForgeDirection disconnectFacing) {
        if (worldObj == null) return false;
        BlockPos disconnectPos = getPos().offset(disconnectFacing);
        Block block = worldObj.getBlock(disconnectPos.getX(), disconnectPos.getY(), disconnectPos.getZ());
        if (!(block instanceof BlockECalculatorCellDrive)) {
            return false;
        }

        ForgeDirection facing = getFacingFromMeta(worldObj.getBlockMetadata(disconnectPos.getX(), disconnectPos.getY(), disconnectPos.getZ()));
        ForgeDirection currentFacing = getFacingFromMeta(worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
        if (facing != currentFacing) {
            return false;
        }

        if (!(worldObj.getTileEntity(disconnectPos.getX(), disconnectPos.getY(), disconnectPos.getZ()) instanceof ECalculatorCellDrive drive)) {
            return false;
        }

        drive.disconnectTransmitter();
        return true;
    }

    protected boolean connectCellDrive(final ForgeDirection connectFacing) {
        if (worldObj == null) return false;
        BlockPos connectPos = getPos().offset(connectFacing);
        Block block = worldObj.getBlock(connectPos.getX(), connectPos.getY(), connectPos.getZ());
        if (!(block instanceof BlockECalculatorCellDrive)) {
            return false;
        }

        ForgeDirection facing = getFacingFromMeta(worldObj.getBlockMetadata(connectPos.getX(), connectPos.getY(), connectPos.getZ()));
        ForgeDirection currentFacing = getFacingFromMeta(worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
        if (facing != currentFacing) {
            return false;
        }

        if (!(worldObj.getTileEntity(connectPos.getX(), connectPos.getY(), connectPos.getZ()) instanceof ECalculatorCellDrive drive)) {
            return false;
        }

        return drive.connectTransmitter(connectFacing.getOpposite(), getControllerLevel());
    }

    public void neighborChanged(final BlockPos changedPos) {
        if (getController() != null) {
            if (changedPos.equals(getPos().up()) || changedPos.equals(getPos().down())) {
                connectCellDrives();
            }
        } else {
            disconnectCellDrives();
        }
    }

    @Override
    public void readCustomNBT(final NBTTagCompound compound) {
        super.readCustomNBT(compound);

        this.upConnected = compound.getBoolean("upConnected");
        this.downConnected = compound.getBoolean("downConnected");

        updateContainingBlockInfo();
    }

    @Override
    public void writeCustomNBT(final NBTTagCompound compound) {
        super.writeCustomNBT(compound);

        compound.setBoolean("upConnected", this.upConnected);
        compound.setBoolean("downConnected", this.downConnected);
    }

    public TransmitterBusLinkLevel getLinkLevel() {
        final Levels controllerLevel = getControllerLevel();
        if (controllerLevel == null) {
            return TransmitterBusLinkLevel.NONE;
        }

        switch (controllerLevel) {
            case L4: {
                return TransmitterBusLinkLevel.L4;
            }
            case L6: {
                return TransmitterBusLinkLevel.L6;
            }
            case L9: {
                return TransmitterBusLinkLevel.L9;
            }
        }

        return TransmitterBusLinkLevel.NONE;
    }

    public boolean isAllConnected() {
        return this.upConnected && this.downConnected;
    }

    public boolean isUpConnected() {
        return this.upConnected;
    }

    public boolean isDownConnected() {
        return this.downConnected;
    }

    /**
     * Converts block metadata to a horizontal ForgeDirection.
     * Convention matches BlockECalculatorCellDrive.onBlockPlacedBy / BlockECalculatorTransmitterBus.onBlockPlacedBy:
     * 0=NORTH, 1=EAST, 2=SOUTH, 3=WEST
     */
    private static ForgeDirection getFacingFromMeta(int meta) {
        switch (meta & 3) {
            case 0: return ForgeDirection.NORTH;
            case 1: return ForgeDirection.EAST;
            case 2: return ForgeDirection.SOUTH;
            case 3: return ForgeDirection.WEST;
            default: return ForgeDirection.NORTH;
        }
    }

}

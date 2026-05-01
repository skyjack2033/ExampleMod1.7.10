package github.kasuminova.ecoaeextension.common.tile;

import net.minecraft.nbt.NBTTagCompound;

/**
 * Replacement for TileCustomController - removes MMCE dependency.
 * Extends TileNovaController instead of TileMultiblockMachineController.
 */
public abstract class TileCustomController extends TileNovaController {

    // Placeholder - the original MMCE-based status is no longer needed
    protected Object controllerStatus = null;

    protected void readMachineNBT(NBTTagCompound compound) {
    }

    @Override
    public void readCustomNBT(NBTTagCompound compound) {
        readMachineNBT(compound);
    }

    @Override
    public void writeCustomNBT(NBTTagCompound compound) {
    }
}

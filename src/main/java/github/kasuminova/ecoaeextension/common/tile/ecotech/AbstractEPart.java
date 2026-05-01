package github.kasuminova.ecoaeextension.common.tile.ecotech;

import github.kasuminova.ecoaeextension.common.util.BlockPos;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public abstract class AbstractEPart<C> extends NovaTileBase implements EPart<C> {

    protected C partController = null;

    @Override
    public void setController(final C controller) {
        this.partController = controller;
    }

    @Nullable
    @Override
    public C getController() {
        return partController;
    }

    public void onAssembled() {
    }

    public void onDisassembled() {
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }

    @Override
    public void onChunkUnload() {
        super.onChunkUnload();
        callDisassemble(partController);
    }

    @Override
    public void invalidate() {
        super.invalidate();
        callDisassemble(partController);
    }

    private static void callDisassemble(Object controller) {
        if (controller instanceof EPartController) {
            ((EPartController<?>) controller).disassemble();
        } else if (controller instanceof NovaPartController) {
            ((NovaPartController<?>) controller).disassemble();
        }
    }

    @Override
    public void readCustomNBT(final NBTTagCompound compound) {
    }

    @Override
    public void readFromNBT(final NBTTagCompound compound) {
        super.readFromNBT(compound);
        readCustomNBT(compound);
    }

    @Override
    public void writeToNBT(final NBTTagCompound compound) {
        super.writeToNBT(compound);
    }

    /**
     * Refresh client block state to actual state.
     */
    @Override
    @SuppressWarnings("ConstantValue")
    public void updateContainingBlockInfo() {
        super.updateContainingBlockInfo();
        final World world = getWorld();
        if (world != null && world.isRemote) {
            world.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
    }

}

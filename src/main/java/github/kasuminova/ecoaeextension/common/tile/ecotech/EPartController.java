package github.kasuminova.ecoaeextension.common.tile.ecotech;

import github.kasuminova.ecoaeextension.ECOAEExtension;
import github.kasuminova.ecoaeextension.common.util.BlockPos;
import github.kasuminova.ecoaeextension.common.util.NovaAsyncExecutor;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Controller base for ECO subsystems.
 * Extends NovaPartController for structure validation and part management.
 */
public abstract class EPartController<P extends EPart<?>> extends NovaPartController<P> {

    /**
     * Called on every server tick when structure is formed.
     * @return true to trigger async tick processing
     */
    protected abstract boolean onSyncTick();

    /**
     * Called asynchronously after onSyncTick returns true.
     */
    protected void onAsyncTick() {
    }

    @Override
    public void doControllerTick() {
        checkRotation();

        boolean structureValid = this.doStructureCheck();
        if (structureValid) {
            this.setStructureFormed(true);
        } else {
            this.setStructureFormed(false);
        }

        if (!this.isStructureFormed()) {
            disassemble();
            return;
        }
        if (!assemble()) {
            return;
        }

        if (onSyncTick()) {
            NovaAsyncExecutor.getInstance().submitTask(this::onAsyncTick);
        }
    }

    public boolean checkControllerShared() {
        BlockPos pos = getPos();
        World world = getWorld();
        TileEntity up = world.getTileEntity(pos.getX(), pos.getY() + 2, pos.getZ());
        if (up instanceof EPartController<?> partController) {
            if (partController.getControllerBlock() == getControllerBlock()) {
                return true;
            }
        }
        TileEntity down = world.getTileEntity(pos.getX(), pos.getY() - 2, pos.getZ());
        if (down instanceof EPartController<?> partController) {
            return partController.getControllerBlock() == getControllerBlock();
        }
        return false;
    }

    @Override
    protected void updateComponents() {
        clearParts();
        clearFoundComponents();

        BlockPos pos = getPos();
        World world = getWorld();

        int maxLen = getWorkerLength();
        if (maxLen <= 0) maxLen = 12;

        int dx = 0, dz = 0;
        ForgeDirection dir = controllerRotation;
        if (dir == ForgeDirection.NORTH) dz = -maxLen;
        else if (dir == ForgeDirection.SOUTH) dz = maxLen;
        else if (dir == ForgeDirection.WEST) dx = -maxLen;
        else if (dir == ForgeDirection.EAST) dx = maxLen;

        int startX = Math.min(pos.getX(), pos.getX() + dx);
        int endX = Math.max(pos.getX(), pos.getX() + dx);
        int startZ = Math.min(pos.getZ(), pos.getZ() + dz);
        int endZ = Math.max(pos.getZ(), pos.getZ() + dz);
        int startY = pos.getY() - 1;
        int endY = pos.getY() + 1;

        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                for (int z = startZ; z <= endZ; z++) {
                    if (!world.blockExists(x, y, z)) continue;
                    TileEntity te = world.getTileEntity(x, y, z);
                    if (te instanceof AbstractEPart<?>) {
                        try {
                            @SuppressWarnings("unchecked")
                            P part = (P) te;
                            if (part.getController() != this) {
                                ((EPart) part).setController(this);
                            }
                            parts.addPart(part);
                            onAddPart(part);
                        } catch (ClassCastException e) {
                            ECOAEExtension.log.error("Invalid EPart found at ({}, {}, {}) !", x, y, z);
                            ECOAEExtension.log.error(e);
                        }
                    }
                }
            }
        }
    }

    protected abstract void onAddPart(P part);

    @Override
    protected void checkRotation() {
        if (controllerRotation != null) {
            return;
        }
        BlockPos p = getPos();
        Block block = getWorld().getBlock(p.getX(), p.getY(), p.getZ());
        if (getControllerBlock().isInstance(block)) {
            int meta = getWorld().getBlockMetadata(p.getX(), p.getY(), p.getZ());
            if (meta >= 0 && meta < ForgeDirection.VALID_DIRECTIONS.length) {
                controllerRotation = ForgeDirection.VALID_DIRECTIONS[meta];
            } else {
                controllerRotation = ForgeDirection.NORTH;
            }
        } else {
            ECOAEExtension.log.warn("Invalid controller block at {} !", getPos());
            controllerRotation = ForgeDirection.NORTH;
        }
    }

    protected abstract Class<? extends Block> getControllerBlock();

    @Override
    public void invalidate() {
        super.invalidate();
    }

    @Override
    public boolean isWorking() {
        return assembled;
    }
}

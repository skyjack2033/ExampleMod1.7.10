package hellfirepvp.modularmachinery.common.tiles.base;

import github.kasuminova.ecoaeextension.ECOAEExtension;
import github.kasuminova.ecoaeextension.common.util.BlockPos;
import github.kasuminova.mmce.common.util.StructureDefinition;
import github.kasuminova.mmce.common.helper.IDynamicPatternInfo;
import github.kasuminova.mmce.common.util.TimeRecorder;
import hellfirepvp.modularmachinery.common.machine.DynamicMachine;
import hellfirepvp.modularmachinery.common.util.MachinePattern;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.*;

public class TileMultiblockMachineController extends TileEntitySynchronized {

    // Structure check
    protected int lastStructureCheckTick = -1;
    protected int structureCheckDelay = 40;
    protected int structureCheckCounter = 0;
    protected int maxStructureCheckDelay = 100;

    // Controller state
    protected boolean structureFormed = false;
    protected ForgeDirection controllerRotation = ForgeDirection.NORTH;
    protected DynamicMachine foundMachine = null;
    protected MachinePattern foundPattern = null;
    protected StructureDefinition structureDef = null;
    protected Object tickExecutor = null;
    protected TimeRecorder timeRecorder = new TimeRecorder();

    // Work mode
    public enum WorkMode {
        SYNC,
        SEMI_SYNC
    }
    protected WorkMode workMode = WorkMode.SYNC;

    // Components
    protected Map<Object, Object> foundComponents = new HashMap<>();

    public TileMultiblockMachineController getController() {
        return this;
    }

    public Object getMachine() {
        return null;
    }

    public DynamicMachine getFoundMachine() {
        return foundMachine;
    }

    public ForgeDirection getControllerRotation() {
        return controllerRotation;
    }

    public void setControllerRotation(ForgeDirection rotation) {
        this.controllerRotation = rotation;
    }

    public void setFoundMachine(DynamicMachine machine) {
        this.foundMachine = machine;
    }

    public void setStructureDef(StructureDefinition def) {
        this.structureDef = def;
    }

    public StructureDefinition getStructureDef() {
        return structureDef;
    }

    public boolean doStructureCheck() {
        if (worldObj == null || structureDef == null) return false;

        BlockPos controllerPos = getPos();
        if (controllerPos == null) return false;

        // Check all fixed parts
        for (StructureDefinition.PatternEntry entry : structureDef.fixedParts) {
            BlockPos worldPos = rotateAndOffset(entry.x, entry.y, entry.z, controllerPos);
            Block block = worldObj.getBlock(worldPos.getX(), worldPos.getY(), worldPos.getZ());
            int meta = worldObj.getBlockMetadata(worldPos.getX(), worldPos.getY(), worldPos.getZ());
            if (!entry.matches(block, meta)) {
                return false;
            }
        }

        // Check dynamic patterns with minimum sizes
        for (StructureDefinition.DynamicPattern dp : structureDef.dynamicPatterns) {
            boolean found = false;
            for (int i = 0; i < dp.maxSize; i++) {
                boolean allMatch = true;
                for (StructureDefinition.PatternEntry entry : dp.parts) {
                    int px = entry.x + dp.direction.offsetX * i;
                    int py = entry.y + dp.direction.offsetY * i;
                    int pz = entry.z + dp.direction.offsetZ * i;
                    BlockPos worldPos = rotateAndOffset(px, py, pz, controllerPos);
                    Block block = worldObj.getBlock(worldPos.getX(), worldPos.getY(), worldPos.getZ());
                    int meta = worldObj.getBlockMetadata(worldPos.getX(), worldPos.getY(), worldPos.getZ());
                    if (!entry.matches(block, meta)) {
                        allMatch = false;
                        break;
                    }
                }
                if (allMatch && i >= dp.minSize - 1) {
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }

        return true;
    }

    public boolean isStructureFormed() {
        return structureFormed;
    }

    public void setStructureFormed(boolean formed) {
        this.structureFormed = formed;
        notifyStructureFormedState(formed);
    }

    public void markNoUpdateSync() {
        markDirty();
    }

    public IDynamicPatternInfo getDynamicPattern(String name) {
        if (structureDef != null && worldObj != null) {
            for (StructureDefinition.DynamicPattern dp : structureDef.dynamicPatterns) {
                if (dp.name.equals(name)) {
                    return new SimpleDynamicPatternInfo(dp);
                }
            }
        }
        return null;
    }

    public void notifyStructureFormedState(boolean formed) {
        if (worldObj == null) return;
        // Structure formation state changed — subclasses may override
    }

    protected void updateComponents() {
        // Subclasses override to scan for components
    }

    protected void readMachineNBT(net.minecraft.nbt.NBTTagCompound compound) {
    }

    private BlockPos rotateAndOffset(int rx, int ry, int rz, BlockPos origin) {
        // Rotate relative position based on controller rotation and add to origin
        int dx, dz;
        switch (controllerRotation) {
            case NORTH: dx = rx;  dz = rz;  break;
            case SOUTH: dx = -rx; dz = -rz; break;
            case WEST:  dx = -rz; dz = rx;  break;
            case EAST:  dx = rz;  dz = -rx; break;
            default:    dx = rx;  dz = rz;  break;
        }
        return new BlockPos(origin.getX() + dx, origin.getY() + ry, origin.getZ() + dz);
    }

    private static class SimpleDynamicPatternInfo implements IDynamicPatternInfo {
        private final StructureDefinition.DynamicPattern dp;

        SimpleDynamicPatternInfo(StructureDefinition.DynamicPattern dp) {
            this.dp = dp;
        }

        @Override
        public github.kasuminova.mmce.common.util.DynamicPattern getPattern() {
            github.kasuminova.mmce.common.util.DynamicPattern p = new github.kasuminova.mmce.common.util.DynamicPattern(dp, 0);
            p.setSize(dp.maxSize);
            return p;
        }

        @Override
        public int getPriority() {
            return 0;
        }

        @Override
        public int getSize() {
            return dp.maxSize;
        }
    }
}

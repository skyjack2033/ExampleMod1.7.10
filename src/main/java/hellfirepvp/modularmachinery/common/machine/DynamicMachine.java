package hellfirepvp.modularmachinery.common.machine;

import github.kasuminova.ecoaeextension.common.util.BlockPos;
import github.kasuminova.mmce.common.helper.IDynamicPatternInfo;
import hellfirepvp.modularmachinery.common.tiles.base.TileEntitySynchronized;
import net.minecraft.util.math.Vec3i;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DynamicMachine {

    private final String machineName;

    public DynamicMachine(String machineName) {
        this.machineName = machineName;
    }

    public String getMachineName() {
        return machineName;
    }

    public TileEntitySynchronized getController() {
        return null;
    }

    public boolean hasMachine() {
        return false;
    }

    public void onDeactivated() {
    }

    public void onActivated() {
    }

    public Object getContainerProvider() {
        return null;
    }

    public Optional<DynamicMachine> getAsOptional() {
        return Optional.of(this);
    }

    public Map<BlockPos, IDynamicPatternInfo> getTileBlocksArray() {
        return new HashMap<>();
    }

    public Vec3i getMin() {
        return new Vec3i(0, 0, 0);
    }

    public Vec3i getMax() {
        return new Vec3i(0, 0, 0);
    }
}

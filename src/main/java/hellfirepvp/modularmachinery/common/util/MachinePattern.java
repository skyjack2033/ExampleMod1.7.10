package hellfirepvp.modularmachinery.common.util;

import github.kasuminova.ecoaeextension.common.util.BlockPos;
import net.minecraft.util.math.Vec3i;

import java.util.Collections;
import java.util.Map;

public class MachinePattern {

    public Map<BlockPos, Object> getTileBlocksArray() {
        return Collections.emptyMap();
    }

    public Vec3i getMin() {
        return new Vec3i(0, 0, 0);
    }

    public Vec3i getMax() {
        return new Vec3i(0, 0, 0);
    }
}

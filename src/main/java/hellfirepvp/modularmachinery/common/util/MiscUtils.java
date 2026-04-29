package hellfirepvp.modularmachinery.common.util;

import github.kasuminova.ecoaeextension.common.util.BlockPos;
import net.minecraftforge.common.util.ForgeDirection;

public final class MiscUtils {

    private MiscUtils() {
    }

    public static int clamp(int value, int min, int max) {
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }

    public static BlockPos rotateYCCWNorthUntil(BlockPos pos, ForgeDirection rotation) {
        // Stub for 1.7.10 port
        return pos;
    }
}

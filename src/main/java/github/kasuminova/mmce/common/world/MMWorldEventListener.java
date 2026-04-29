package github.kasuminova.mmce.common.world;

import github.kasuminova.ecoaeextension.common.util.BlockPos;
import net.minecraft.world.World;

public class MMWorldEventListener {

    public static final MMWorldEventListener INSTANCE = new MMWorldEventListener();

    public boolean isAreaChanged(World world, BlockPos minPos, BlockPos maxPos) {
        return false;
    }
}

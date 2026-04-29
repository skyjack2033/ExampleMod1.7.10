package github.kasuminova.ecoaeextension.common.util;

import net.minecraft.block.Block;
import net.minecraft.world.World;

/**
 * Compatibility layer for 1.12.2 World blockstate operations on 1.7.10.
 */
public class WorldCompat {
    /**
     * 1.7.10 compatible setBlock. Uses World.setBlock (x/y/z style).
     */
    public static boolean setBlockState(World world, BlockPos pos, Block block, int meta, int flags) {
        return world.setBlock(pos.getX(), pos.getY(), pos.getZ(), block, meta, flags);
    }
}

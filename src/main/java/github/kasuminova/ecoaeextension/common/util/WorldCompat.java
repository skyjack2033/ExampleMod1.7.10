package github.kasuminova.ecoaeextension.common.util;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;

/**
 * Compatibility layer for 1.12.2 World blockstate operations on 1.7.10.
 */
public class WorldCompat {
    /**
     * 1.7.10 compatible setBlockState. Extracts metadata from IBlockState
     * and uses World.setBlock (x/y/z style).
     */
    public static boolean setBlockState(World world, BlockPos pos, IBlockState state, int flags) {
        Block block = state.getBlock();
        int meta = block.getMetaFromState(state);
        return world.setBlock(pos.getX(), pos.getY(), pos.getZ(), block, meta, flags);
    }
}

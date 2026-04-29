package github.kasuminova.ecoaeextension.common.util;

import net.minecraftforge.common.util.ForgeDirection;

/**
 * Minimal BlockPos compatible class for 1.7.10.
 * Replaces net.minecraft.util.math.BlockPos from 1.12.2+.
 */
public class BlockPos {
    public static final BlockPos ORIGIN = new BlockPos(0, 0, 0);

    private final int x;
    private final int y;
    private final int z;

    public BlockPos(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getZ() { return z; }

    public BlockPos offset(ForgeDirection direction) {
        return new BlockPos(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ);
    }

    public BlockPos offset(ForgeDirection direction, int distance) {
        return new BlockPos(x + direction.offsetX * distance, y + direction.offsetY * distance, z + direction.offsetZ * distance);
    }

    public BlockPos add(int dx, int dy, int dz) {
        return new BlockPos(x + dx, y + dy, z + dz);
    }

    public BlockPos add(BlockPos other) {
        return new BlockPos(x + other.x, y + other.y, z + other.z);
    }

    public BlockPos up() { return add(0, 1, 0); }
    public BlockPos down() { return add(0, -1, 0); }
    public BlockPos north() { return add(0, 0, -1); }
    public BlockPos south() { return add(0, 0, 1); }
    public BlockPos east() { return add(1, 0, 0); }
    public BlockPos west() { return add(-1, 0, 0); }
    public BlockPos up(int n) { return add(0, n, 0); }
    public BlockPos down(int n) { return add(0, -n, 0); }
    public BlockPos north(int n) { return add(0, 0, -n); }
    public BlockPos south(int n) { return add(0, 0, n); }
    public BlockPos east(int n) { return add(n, 0, 0); }
    public BlockPos west(int n) { return add(-n, 0, 0); }

    public long toLong() {
        return ((long) x & 0x3FFFFFFL) | (((long) z & 0x3FFFFFFL) << 26) | (((long) y & 0xFFFL) << 52);
    }

    public static BlockPos fromLong(long serialized) {
        int x = (int) (serialized & 0x3FFFFFFL);
        int z = (int) ((serialized >> 26) & 0x3FFFFFFL);
        int y = (int) ((serialized >> 52) & 0xFFFL);
        return new BlockPos(x, y, z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockPos)) return false;
        BlockPos blockPos = (BlockPos) o;
        return x == blockPos.x && y == blockPos.y && z == blockPos.z;
    }

    @Override
    public int hashCode() {
        return (y + z * 31) * 31 + x;
    }

    @Override
    public String toString() {
        return "BlockPos{x=" + x + ", y=" + y + ", z=" + z + '}';
    }
}

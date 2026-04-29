package net.minecraft.util.math;

/**
 * Minimal Vec3i compatibility class for 1.7.10.
 * 1.7.10 has no Vec3i; we use a simple int-vector implementation.
 */
public class Vec3i {
    private final int x, y, z;

    public Vec3i(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getZ() { return z; }
}

package net.minecraft.util.math;

/**
 * Compatibility redirect - 1.7.10 has AxisAlignedBB at net.minecraft.util.AxisAlignedBB.
 * This is a minimal stub for code that imports from net.minecraft.util.math.
 */
public class AxisAlignedBB extends net.minecraft.util.AxisAlignedBB {
    public AxisAlignedBB(double x1, double y1, double z1, double x2, double y2, double z2) {
        super(x1, y1, z1, x2, y2, z2);
    }
}

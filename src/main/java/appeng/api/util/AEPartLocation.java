package appeng.api.util;

import net.minecraftforge.common.util.ForgeDirection;

public enum AEPartLocation {
    DOWN(ForgeDirection.DOWN),
    UP(ForgeDirection.UP),
    NORTH(ForgeDirection.NORTH),
    SOUTH(ForgeDirection.SOUTH),
    WEST(ForgeDirection.WEST),
    EAST(ForgeDirection.EAST),
    INTERNAL(null);

    public final ForgeDirection facing;
    public final int xOffset, yOffset, zOffset;

    AEPartLocation(ForgeDirection facing) {
        this.facing = facing;
        this.xOffset = facing != null ? facing.offsetX : 0;
        this.yOffset = facing != null ? facing.offsetY : 0;
        this.zOffset = facing != null ? facing.offsetZ : 0;
    }

    public static AEPartLocation fromFacing(ForgeDirection facing) {
        switch (facing) {
            case DOWN: return DOWN;
            case UP: return UP;
            case NORTH: return NORTH;
            case SOUTH: return SOUTH;
            case WEST: return WEST;
            case EAST: return EAST;
            default: return INTERNAL;
        }
    }

    public AEPartLocation getOpposite() {
        switch (this) {
            case DOWN: return UP;
            case UP: return DOWN;
            case NORTH: return SOUTH;
            case SOUTH: return NORTH;
            case WEST: return EAST;
            case EAST: return WEST;
            default: return INTERNAL;
        }
    }
}

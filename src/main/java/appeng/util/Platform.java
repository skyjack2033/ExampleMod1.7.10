package appeng.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class Platform {
    public static boolean canAccess(EntityPlayer player, Object security) { return true; }
    public static boolean isClient() { return false; }
    public static boolean isServer() { return true; }
    public static boolean hasPermissions(Object security, EntityPlayer player) { return true; }
    public static ItemStack extractItemsByRecipe(Object o1, Object o2, Object o3, Object o4, boolean b) { return null; }
}

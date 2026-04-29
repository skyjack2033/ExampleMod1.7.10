package appeng.helpers;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemStackHelper {

    public static ItemStack stackFromNBT(NBTTagCompound nbt) {
        return ItemStack.loadItemStackFromNBT(nbt);
    }

    public static NBTTagCompound stackWriteToNBT(ItemStack stack) {
        NBTTagCompound nbt = new NBTTagCompound();
        if (stack != null) stack.writeToNBT(nbt);
        return nbt;
    }

    public static void stackWriteToNBT(ItemStack stack, NBTTagCompound nbt) {
        if (stack != null) stack.writeToNBT(nbt);
    }
}

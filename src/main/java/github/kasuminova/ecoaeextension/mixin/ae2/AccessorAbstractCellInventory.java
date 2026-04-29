package github.kasuminova.ecoaeextension.mixin.ae2;

import appeng.me.storage.CellInventory;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = CellInventory.class, remap = false)
public interface AccessorAbstractCellInventory {

    @Accessor("tagCompound")
    NBTTagCompound getTagCompound();

    @Accessor("maxTypes")
    int getMaxItemTypes();

    @Accessor("storedTypes")
    short getStoredItemTypes();

    @Accessor("storedTypes")
    void setStoredItemTypes(short storedItemTypes);

    @Accessor("storedCount")
    long getStoredItemCount();

    @Accessor("storedCount")
    void setStoredItemCount(long storedItemCount);

}

package github.kasuminova.ecoaeextension.client.renderer;

import github.kasuminova.ecoaeextension.ECOAEExtension;
import github.kasuminova.ecoaeextension.common.block.ecotech.estorage.prop.EnergyCellStatus;
import github.kasuminova.ecoaeextension.common.item.estorage.ItemBlockEStorageEnergyCell;
import github.kasuminova.ecoaeextension.common.tile.ecotech.estorage.EStorageEnergyCell;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class EStorageEnergyCellItemRenderer {

    protected final ResourceLocation modelLocation;

    public EStorageEnergyCellItemRenderer(ItemBlockEStorageEnergyCell item, String level) {
        this.modelLocation = new ResourceLocation(ECOAEExtension.MOD_ID, "estorage_energy_cell_" + level);
    }

    private static double getFillFactor(ItemStack stack) {
        NBTTagCompound tag = stack.getTagCompound();
        if (tag == null || tag.stackSize <= 0) {
            return 0;
        }

        double maxEnergyStore = tag.getDouble("maxEnergyStore");
        if (maxEnergyStore <= 0) {
            return 0;
        }
        double energyStored = tag.getDouble("energyStored");
        if (energyStored <= 0) {
            return 0;
        }
        return energyStored / maxEnergyStore;
    }

}

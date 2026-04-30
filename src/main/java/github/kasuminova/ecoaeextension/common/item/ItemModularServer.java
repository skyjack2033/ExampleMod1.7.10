package github.kasuminova.ecoaeextension.common.item;

import github.kasuminova.ecoaeextension.ECOAEExtension;
import github.kasuminova.ecoaeextension.common.core.CreativeTabNovaEng;
import net.minecraft.item.Item;

public class ItemModularServer extends Item {

    public ItemModularServer(final String registryName) {
        setMaxStackSize(1);
        setCreativeTab(CreativeTabNovaEng.INSTANCE);
        setUnlocalizedName(ECOAEExtension.MOD_ID + '.' + registryName);
        setTextureName(ECOAEExtension.MOD_ID + ":crystal_matrix");
    }

}

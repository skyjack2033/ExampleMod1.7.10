package github.kasuminova.ecoaeextension.common.core;

import github.kasuminova.ecoaeextension.common.block.ecotech.estorage.BlockEStorageController;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabNovaEng extends CreativeTabs {
    public static final CreativeTabNovaEng INSTANCE = new CreativeTabNovaEng();

    private CreativeTabNovaEng() {
        super("ecoaeextension");
    }

    @Override
    public Item getTabIconItem() {
        return Item.getItemFromBlock(BlockEStorageController.L9);
    }

}

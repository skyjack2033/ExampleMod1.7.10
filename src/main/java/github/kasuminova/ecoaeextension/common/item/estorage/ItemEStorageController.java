package github.kasuminova.ecoaeextension.common.item.estorage;

import hellfirepvp.modularmachinery.common.item.ItemBlockController;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemEStorageController extends ItemBlockController {

    public ItemEStorageController(final Block block) {
        super(block);
    }

    @Override
    public void addInformation(final ItemStack stack, @Nullable final EntityPlayer player, final List<String> tooltip, final boolean advanced) {
        tooltip.add(I18n.format("novaeng.extendable_storage_subsystem.info.0"));
        tooltip.add(I18n.format("novaeng.extendable_storage_subsystem.info.1"));
        super.addInformation(stack, player, tooltip, advanced);
    }

    @Nonnull
    @Override
    public String getItemStackDisplayName(@Nonnull final ItemStack stack) {
        return StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name").trim();
    }

}

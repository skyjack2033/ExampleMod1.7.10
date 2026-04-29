package github.kasuminova.ecoaeextension.common.item.efabriactor;

import github.kasuminova.ecoaeextension.common.item.ItemBlockME;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemEFabricatorMEChannel extends ItemBlockME {

    public ItemEFabricatorMEChannel(final Block block) {
        super(block);
    }

    @Override
    public void addInformation(@Nonnull final ItemStack stack, @Nullable final EntityPlayer player, final List<String> tooltip, @Nonnull final boolean advanced) {
        tooltip.add(I18n.format("novaeng.efabricator_me_channel.info.0"));
        tooltip.add(I18n.format("novaeng.efabricator_me_channel.info.1"));
        tooltip.add(I18n.format("novaeng.efabricator_me_channel.info.2"));
        tooltip.add(I18n.format("novaeng.efabricator_me_channel.info.3"));
        super.addInformation(stack, player, tooltip, advanced);
    }

}

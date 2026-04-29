package github.kasuminova.ecoaeextension.common.item.efabriactor;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemEFabricatorWorker extends ItemBlock {

    public ItemEFabricatorWorker(final Block block) {
        super(block);
    }

    @Override
    public void addInformation(final @NotNull ItemStack stack, @Nullable final EntityPlayer player, final List<String> tooltip, final boolean advanced) {
        tooltip.add(I18n.format("novaeng.efabricator_worker.info.0"));
        tooltip.add(I18n.format("novaeng.efabricator_worker.info.1"));
        tooltip.add(I18n.format("novaeng.efabricator_worker.info.2"));
        tooltip.add(I18n.format("novaeng.efabricator_worker.info.3"));
        tooltip.add(I18n.format("novaeng.efabricator_worker.info.4"));
        tooltip.add(I18n.format("novaeng.efabricator_worker.info.5"));
        tooltip.add(I18n.format("novaeng.efabricator_worker.info.6"));
        tooltip.add(I18n.format("novaeng.efabricator_worker.info.7"));
        tooltip.add(I18n.format("novaeng.efabricator_worker.info.8"));
        tooltip.add(I18n.format("novaeng.efabricator_worker.info.9"));
        tooltip.add(I18n.format("novaeng.efabricator_worker.info.10"));
        tooltip.add(I18n.format("novaeng.efabricator_worker.info.11"));
        tooltip.add(I18n.format("novaeng.efabricator_worker.info.12"));
        tooltip.add(I18n.format("novaeng.efabricator_worker.info.13"));
        super.addInformation(stack, player, tooltip, advanced);
    }

}

package github.kasuminova.ecoaeextension.common.item.efabriactor;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemEFabricatorPatternBus extends ItemBlock {

    public ItemEFabricatorPatternBus(final Block block) {
        super(block);
    }

    @Override
    public void addInformation(@Nonnull final ItemStack stack, @Nullable final EntityPlayer player, final List<String> tooltip, @Nonnull final boolean advanced) {
        tooltip.add(I18n.format("novaeng.efabricator_pattern_bus.info.0"));
        tooltip.add(I18n.format("novaeng.efabricator_pattern_bus.info.1"));
        tooltip.add(I18n.format("novaeng.efabricator_pattern_bus.info.2"));
        tooltip.add(I18n.format("novaeng.efabricator_pattern_bus.info.3"));
        tooltip.add(I18n.format("novaeng.efabricator_pattern_bus.info.4"));
        tooltip.add(I18n.format("novaeng.efabricator_pattern_bus.info.5"));
        super.addInformation(stack, player, tooltip, advanced);
    }

}

package github.kasuminova.ecoaeextension.common.item.ecalculator;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemECalculatorController extends ItemBlock {

    public ItemECalculatorController(final Block block) {
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public void addInformation(final @NotNull ItemStack stack, @Nullable final EntityPlayer player, final List<String> tooltip, final boolean advanced) {
        tooltip.add(I18n.format("novaeng.extendable_calculate_subsystem.info.0"));
        tooltip.add(I18n.format("novaeng.extendable_calculate_subsystem.info.1"));
        tooltip.add(I18n.format("novaeng.extendable_calculate_subsystem.info.2"));
        tooltip.add(I18n.format("novaeng.extendable_calculate_subsystem.info.3"));
        tooltip.add(I18n.format("novaeng.extendable_calculate_subsystem.info.4"));
        tooltip.add(I18n.format("novaeng.extendable_calculate_subsystem.info.5"));
        tooltip.add(I18n.format("novaeng.extendable_calculate_subsystem.info.6"));
        tooltip.add(I18n.format("novaeng.extendable_calculate_subsystem.info.7"));
        tooltip.add(I18n.format("novaeng.extendable_calculate_subsystem.info.8"));
    }

    @Nonnull
    @Override
    public String getItemStackDisplayName(@Nonnull final ItemStack stack) {
        return StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name").trim();
    }

    @Override
    public int getMetadata(final int damage) {
        return damage;
    }
}

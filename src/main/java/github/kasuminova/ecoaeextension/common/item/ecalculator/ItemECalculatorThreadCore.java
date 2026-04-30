package github.kasuminova.ecoaeextension.common.item.ecalculator;

import github.kasuminova.ecoaeextension.common.block.ecotech.ecalculator.BlockECalculatorThreadCore;
import github.kasuminova.ecoaeextension.common.block.ecotech.ecalculator.BlockECalculatorThreadCoreHyper;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemECalculatorThreadCore extends ItemBlock {

    private final BlockECalculatorThreadCore myBlock;

    public ItemECalculatorThreadCore(final Block block) {
        super(block);
        this.myBlock = (BlockECalculatorThreadCore) block;
        ((BlockECalculatorThreadCore) block).setItem(this);
    }

    @Override
    public void addInformation(final @NotNull ItemStack stack, @Nullable final EntityPlayer player, final @NotNull List<String> tooltip, final boolean advanced) {
        BlockECalculatorThreadCore threadCore = myBlock;
        if (threadCore instanceof BlockECalculatorThreadCoreHyper) {
            tooltip.add(I18n.format("novaeng.ecalculator_thread_core_hyper.info.0"));
            tooltip.add(I18n.format("novaeng.ecalculator_thread_core_hyper.info.1"));
            tooltip.add(I18n.format("novaeng.ecalculator_thread_core_hyper.info.2"));
            tooltip.add(I18n.format("novaeng.ecalculator_thread_core.modifiers"));
            tooltip.add(I18n.format("novaeng.ecalculator_thread_core.modifier.add", threadCore.getThreads()));
            tooltip.add(I18n.format("novaeng.ecalculator_thread_core_hyper.modifier.add", threadCore.getHyperThreads()));
        } else {
            tooltip.add(I18n.format("novaeng.ecalculator_thread_core.info.0"));
            tooltip.add(I18n.format("novaeng.ecalculator_thread_core.info.1"));
            tooltip.add(I18n.format("novaeng.ecalculator_thread_core.info.2"));
            tooltip.add(I18n.format("novaeng.ecalculator_thread_core.modifiers"));
            tooltip.add(I18n.format("novaeng.ecalculator_thread_core.modifier.add", threadCore.getThreads()));
        }
        super.addInformation(stack, player, tooltip, advanced);
    }

}

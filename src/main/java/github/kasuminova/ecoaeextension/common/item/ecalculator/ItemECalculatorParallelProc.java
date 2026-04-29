package github.kasuminova.ecoaeextension.common.item.ecalculator;

import github.kasuminova.ecoaeextension.common.block.ecotech.ecalculator.BlockECalculatorParallelProc;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemECalculatorParallelProc extends ItemBlock {

    private final BlockECalculatorParallelProc myBlock;

    public ItemECalculatorParallelProc(final Block block) {
        super(block);
        this.myBlock = (BlockECalculatorParallelProc) block;
    }

    @Override
    public void addInformation(@Nonnull final ItemStack stack, @Nullable final EntityPlayer player, final List<String> tooltip, @Nonnull final boolean advanced) {
        tooltip.add(I18n.format("novaeng.ecalculator_parallel_proc.info.0"));
        tooltip.add(I18n.format("novaeng.ecalculator_parallel_proc.info.1"));
        tooltip.add(I18n.format("novaeng.ecalculator_parallel_proc.modifiers"));
        tooltip.add(I18n.format("novaeng.ecalculator_parallel_proc.modifier.add", myBlock.getParallelism()));
        super.addInformation(stack, player, tooltip, advanced);
    }

}

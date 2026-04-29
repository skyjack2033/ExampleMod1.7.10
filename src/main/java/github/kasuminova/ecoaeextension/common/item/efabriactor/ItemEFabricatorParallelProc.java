package github.kasuminova.ecoaeextension.common.item.efabriactor;

import github.kasuminova.ecoaeextension.common.block.ecotech.efabricator.BlockEFabricatorParallelProc;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemEFabricatorParallelProc extends ItemBlock {

    private final BlockEFabricatorParallelProc myBlock;

    public ItemEFabricatorParallelProc(final BlockEFabricatorParallelProc block) {
        super(block);
        this.myBlock = block;
    }

    @Override
    public void addInformation(final @NotNull ItemStack stack, @Nullable final EntityPlayer player, final List<String> tooltip, final boolean advanced) {
        tooltip.add(I18n.format("novaeng.efabricator_parallel_proc.info.0"));
        tooltip.add(I18n.format("novaeng.efabricator_parallel_proc.info.1"));

        tooltip.add(I18n.format("novaeng.efabricator_parallel_proc.modifiers"));
        myBlock.getModifiers()
                .forEach(modifier -> tooltip.add("  " + modifier.getDesc()));

        tooltip.add(I18n.format("novaeng.efabricator_parallel_proc.overclock_modifiers"));
        myBlock.getOverclockModifiers()
                .forEach(modifier -> tooltip.add("  " + modifier.getDesc()));

        tooltip.add(I18n.format("novaeng.efabricator_parallel_proc.info.2"));
        tooltip.add(I18n.format("novaeng.efabricator_parallel_proc.info.3"));
        tooltip.add(I18n.format("novaeng.efabricator_parallel_proc.info.4"));
        tooltip.add(I18n.format("novaeng.efabricator_parallel_proc.info.5"));
        tooltip.add(I18n.format("novaeng.efabricator_parallel_proc.info.6"));
        super.addInformation(stack, player, tooltip, advanced);
    }

}

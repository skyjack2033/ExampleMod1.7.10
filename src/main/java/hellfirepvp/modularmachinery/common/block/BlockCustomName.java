package hellfirepvp.modularmachinery.common.block;

import net.minecraft.block.Block;

public class BlockCustomName extends BlockMachineComponent {

    public BlockCustomName() {
        super("custom_name");
    }

    @Override
    public Block setBlockName(String name) {
        return super.setBlockName(name);
    }
}

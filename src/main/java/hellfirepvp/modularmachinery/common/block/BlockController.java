package hellfirepvp.modularmachinery.common.block;

import net.minecraft.block.properties.PropertyBool;

public class BlockController extends BlockMachineComponent {

    public static final PropertyBool FORMED = PropertyBool.create("formed");

    public BlockController() {
        super("controller");
    }
}

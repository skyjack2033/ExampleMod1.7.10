package hellfirepvp.modularmachinery.common.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMachineComponent extends BlockContainer {

    protected final String machineName;

    public BlockMachineComponent(String machineName) {
        super(Material.iron);
        this.machineName = machineName;
    }

    public String getMachineName() {
        return machineName;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return null;
    }
}

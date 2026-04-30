package hellfirepvp.modularmachinery.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import github.kasuminova.ecoaeextension.ECOAEExtension;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockMachineComponent extends BlockContainer {

    protected final String machineName;
    protected IIcon blockIcon;

    public BlockMachineComponent(String machineName) {
        super(Material.iron);
        this.machineName = machineName;
        setBlockTextureName(ECOAEExtension.MOD_ID + ":carbon_fiber_chassis");
    }

    public String getMachineName() {
        return machineName;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        this.blockIcon = register.registerIcon(getTextureName());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return this.blockIcon;
    }
}

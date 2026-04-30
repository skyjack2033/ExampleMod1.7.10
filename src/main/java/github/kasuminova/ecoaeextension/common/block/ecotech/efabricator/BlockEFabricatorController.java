package github.kasuminova.ecoaeextension.common.block.ecotech.efabricator;

import github.kasuminova.ecoaeextension.ECOAEExtension;
import github.kasuminova.ecoaeextension.common.CommonProxy;
import github.kasuminova.ecoaeextension.common.core.CreativeTabNovaEng;
import github.kasuminova.ecoaeextension.common.tile.ecotech.efabricator.EFabricatorController;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("deprecation")
public class BlockEFabricatorController extends BlockContainer {
    public static final Map<ResourceLocation, BlockEFabricatorController> REGISTRY = new LinkedHashMap<>();
    public static final BlockEFabricatorController L4;
    public static final BlockEFabricatorController L6;
    public static final BlockEFabricatorController L9;

    static {
        L4 = new BlockEFabricatorController("l4");
        REGISTRY.put(L4.registryName, L4);
        L6 = new BlockEFabricatorController("l6");
        REGISTRY.put(L6.registryName, L6);
        L9 = new BlockEFabricatorController("l9");
        REGISTRY.put(L9.registryName, L9);
    }

    protected final ResourceLocation registryName;
    protected final String machineName;

    public BlockEFabricatorController(final String level) {
        super(Material.iron);
        this.setHardness(20.0F);
        this.setResistance(2000.0F);
        this.setHarvestLevel("pickaxe", 2);
        this.setCreativeTab(CreativeTabNovaEng.INSTANCE);
        registryName = new ResourceLocation(ECOAEExtension.MOD_ID, "extendable_fabricator_subsystem_" + level);
        machineName = registryName.getResourcePath();
        setBlockName(ECOAEExtension.MOD_ID + '.' + machineName);
    }

    public ResourceLocation getRegistryName() {
        return registryName;
    }

    public String getMachineName() {
        return machineName;
    }

    @Override
    public boolean onBlockActivated(final World worldIn, final int x, final int y, final int z,
                                    final EntityPlayer playerIn, final int side,
                                    final float hitX, final float hitY, final float hitZ) {
        if (!worldIn.isRemote) {
            TileEntity te = worldIn.getTileEntity(x, y, z);
            if (te instanceof EFabricatorController && ((EFabricatorController) te).isStructureFormed()) {
                playerIn.openGui(ECOAEExtension.MOD_ID, CommonProxy.GuiType.EFABRICATOR_CONTROLLER.ordinal(),
                        worldIn, x, y, z);
            }
        }
        return true;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(final World worldIn, final int meta) {
        return new EFabricatorController(new ResourceLocation(ECOAEExtension.MOD_ID, machineName));
    }
}

package github.kasuminova.ecoaeextension.common.block.ecotech.efabricator;

import github.kasuminova.ecoaeextension.ECOAEExtension;
import github.kasuminova.ecoaeextension.common.block.ecotech.efabricator.prop.WorkerStatus;
import github.kasuminova.ecoaeextension.common.block.prop.FacingProp;
import github.kasuminova.ecoaeextension.common.core.CreativeTabNovaEng;
import github.kasuminova.ecoaeextension.common.tile.ecotech.efabricator.EFabricatorWorker;
import github.kasuminova.ecoaeextension.common.util.EnumFacingCompat;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings({"deprecation", "NullableProblems"})
public class BlockEFabricatorWorker extends BlockEFabricatorPart {

    public static final BlockEFabricatorWorker INSTANCE = new BlockEFabricatorWorker();

    protected BlockEFabricatorWorker() {
        super(Material.iron);
        this.setHardness(20.0F);
        this.setResistance(2000.0F);
        this.setStepSound(Block.soundTypeMetal);
        this.setHarvestLevel("pickaxe", 2);
        this.setCreativeTab(CreativeTabNovaEng.INSTANCE);
        this.setDefaultState(this.stateContainer.getBaseState()
                .withProperty(FacingProp.HORIZONTALS, ForgeDirection.NORTH)
                .withProperty(WorkerStatus.STATUS, WorkerStatus.OFF)
        );
        this.setBlockName(ECOAEExtension.MOD_ID + '.' + "efabricator_worker");
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(@Nonnull final World world, final int meta) {
        return new EFabricatorWorker();
    }

    @Override
    public int getLightValue(@Nonnull final IBlockAccess world, final int x, final int y, final int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof EFabricatorWorker) {
            EFabricatorWorker worker = (EFabricatorWorker) te;
            return worker.getStatus() != WorkerStatus.OFF ? 10 : 0;
        }
        return 0;
    }

    @Override
    public void onBlockPlacedBy(@Nonnull final World world,
                                final int x, final int y, final int z,
                                @Nonnull final EntityLivingBase placer,
                                @Nonnull final ItemStack stack)
    {
        int dir = MathHelper.floor_double((double) (placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        dir = (dir + 2) & 3;
        world.setBlockMetadataWithNotify(x, y, z, dir, 2);
    }

    public IBlockState getStateFromMeta(final int meta) {
        return getDefaultState().withProperty(FacingProp.HORIZONTALS, EnumFacingCompat.byHorizontalIndex(meta));
    }

    public int getMetaFromState(@Nonnull final IBlockState state) {
        return EnumFacingCompat.toHorizontalIndex(state.getValue(FacingProp.HORIZONTALS));
    }

    @Nonnull
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FacingProp.HORIZONTALS, WorkerStatus.STATUS);
    }

}

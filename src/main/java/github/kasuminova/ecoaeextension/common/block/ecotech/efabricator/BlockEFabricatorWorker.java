package github.kasuminova.ecoaeextension.common.block.ecotech.efabricator;

import github.kasuminova.ecoaeextension.ECOAEExtension;
import github.kasuminova.ecoaeextension.common.block.ecotech.efabricator.prop.WorkerStatus;
import github.kasuminova.ecoaeextension.common.block.prop.FacingProp;
import github.kasuminova.ecoaeextension.common.core.CreativeTabNovaEng;
import github.kasuminova.ecoaeextension.common.tile.ecotech.efabricator.EFabricatorWorker;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import github.kasuminova.ecoaeextension.common.util.EnumFacingCompat;
import net.minecraft.util.ResourceLocation;

import github.kasuminova.ecoaeextension.common.util.BlockPos;
import github.kasuminova.ecoaeextension.common.util.EnumFacingCompat;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings({"deprecation", "NullableProblems"})
public class BlockEFabricatorWorker extends BlockEFabricatorPart {

    public static final BlockEFabricatorWorker INSTANCE = new BlockEFabricatorWorker();

    protected BlockEFabricatorWorker() {
        super(Material.IRON);
        this.setHardness(20.0F);
        this.setResistance(2000.0F);
        this.setSoundType(SoundType.METAL);
        this.setHarvestLevel("pickaxe", 2);
        this.setCreativeTab(CreativeTabNovaEng.INSTANCE);
        this.setDefaultState(this.blockState.getBaseState()
                .withProperty(FacingProp.HORIZONTALS, ForgeDirection.NORTH)
                .withProperty(WorkerStatus.STATUS, WorkerStatus.OFF)
        );
        this.setRegistryName(new ResourceLocation(ECOAEExtension.MOD_ID, "efabricator_worker"));
        this.setTranslationKey(ECOAEExtension.MOD_ID + '.' + "efabricator_worker");
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(@Nonnull final World world, @Nonnull final IBlockState state) {
        return new EFabricatorWorker();
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(@Nonnull final World world, final int meta) {
        return new EFabricatorWorker();
    }

    @Override
    public int getLightValue(@Nonnull final IBlockState state) {
        return state.getValue(WorkerStatus.STATUS) != WorkerStatus.OFF ? 10 : 0;
    }

    @Override
    public IBlockState getActualState(final IBlockState state, final IBlockAccess worldIn, final BlockPos pos) {
        if (worldIn.getTileEntity(pos.getX(), pos.getY(), pos.getZ()) instanceof EFabricatorWorker worker) {
            return state.withProperty(WorkerStatus.STATUS, worker.getStatus());
        }
        return super.getActualState(state, worldIn, pos);
    }

    @Nonnull
    @Override
    public IBlockState getStateFromMeta(final int meta) {
        return getDefaultState().withProperty(FacingProp.HORIZONTALS, EnumFacingCompat.byHorizontalIndex(meta));
    }

    @Override
    public int getMetaFromState(@Nonnull final IBlockState state) {
        return state.getValue(FacingProp.HORIZONTALS).getHorizontalIndex();
    }

    @Nonnull
    public IBlockState getStateForPlacement(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull ForgeDirection facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FacingProp.HORIZONTALS, placer.getHorizontalFacing().getOpposite());
    }

    @Nonnull
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FacingProp.HORIZONTALS, WorkerStatus.STATUS);
    }

}


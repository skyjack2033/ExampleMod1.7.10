package github.kasuminova.ecoaeextension.common.block.ecotech.efabricator;

import github.kasuminova.ecoaeextension.ECOAEExtension;
import github.kasuminova.ecoaeextension.common.block.prop.FacingProp;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.util.ForgeDirection;
import github.kasuminova.ecoaeextension.common.util.EnumFacingCompat;
import net.minecraft.util.ResourceLocation;

import github.kasuminova.ecoaeextension.common.util.BlockPos;
import github.kasuminova.ecoaeextension.common.util.EnumFacingCompat;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * TODO: 朝向烂了。四向模型，定制给了个顶部朝向的模型。
 */
@SuppressWarnings("deprecation")
public class BlockEFabricatorVent extends BlockEFabricator {

    public static final BlockEFabricatorVent INSTANCE = new BlockEFabricatorVent();

    protected BlockEFabricatorVent() {
        this.setDefaultState(this.blockState.getBaseState()
                .withProperty(FacingProp.HORIZONTALS, ForgeDirection.NORTH)
        );
        this.setRegistryName(new ResourceLocation(ECOAEExtension.MOD_ID, "efabricator_vent"));
        this.setTranslationKey(ECOAEExtension.MOD_ID + '.' + "efabricator_vent");
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
        // 鉴于某人把模型做反了，所以这里不反向。
        ForgeDirection placerFacing = placer.getHorizontalFacing();
        return this.getDefaultState().withProperty(FacingProp.HORIZONTALS, placerFacing);
    }

    @Nonnull
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FacingProp.HORIZONTALS);
    }

}

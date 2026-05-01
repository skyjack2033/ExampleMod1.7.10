package github.kasuminova.ecoaeextension.common.block.ecotech.estorage;

import github.kasuminova.ecoaeextension.ECOAEExtension;
import github.kasuminova.ecoaeextension.common.block.prop.FacingProp;
import github.kasuminova.ecoaeextension.common.util.EnumFacingCompat;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import javax.annotation.Nonnull;

@SuppressWarnings("deprecation")
public class BlockEStorageTail extends BlockEStorage {

    public static final BlockEStorageTail L4 = new BlockEStorageTail("l4");
    public static final BlockEStorageTail L6 = new BlockEStorageTail("l6");
    public static final BlockEStorageTail L9 = new BlockEStorageTail("l9");

    protected static final ForgeDirection FORMED = ForgeDirection.UNKNOWN; // Placeholder

    protected BlockEStorageTail(final String level) {
        this.setDefaultState(this.stateContainer.getBaseState()
                .withProperty(FacingProp.HORIZONTALS, ForgeDirection.NORTH)
        );
        this.setBlockName(ECOAEExtension.MOD_ID + '.' + "estorage_tail_" + level);
    }

    public IBlockState getStateFromMeta(final int meta) {
        return getDefaultState().withProperty(FacingProp.HORIZONTALS, EnumFacingCompat.byHorizontalIndex(meta));
    }

    public int getMetaFromState(@Nonnull final IBlockState state) {
        return EnumFacingCompat.toHorizontalIndex(state.getValue(FacingProp.HORIZONTALS));
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

    @Nonnull
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FacingProp.HORIZONTALS);
    }

}

package github.kasuminova.ecoaeextension.common.block.ecotech.estorage;

import github.kasuminova.ecoaeextension.ECOAEExtension;
import github.kasuminova.ecoaeextension.common.core.CreativeTabNovaEng;
import github.kasuminova.ecoaeextension.common.tile.ecotech.estorage.EStorageMEChannel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockEStorageMEChannel extends BlockEStoragePart {

    public static final BlockEStorageMEChannel INSTANCE = new BlockEStorageMEChannel();

    public BlockEStorageMEChannel() {
        super(Material.iron);
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setStepSound(Block.soundTypeMetal);
        this.setHarvestLevel("pickaxe", 1);
        this.setCreativeTab(CreativeTabNovaEng.INSTANCE);
        this.setDefaultState(this.stateContainer.getBaseState());
        this.setBlockName(ECOAEExtension.MOD_ID + '.' + "estorage_me_channel");
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(@Nonnull final World world, final int meta) {
        return new EStorageMEChannel();
    }

}

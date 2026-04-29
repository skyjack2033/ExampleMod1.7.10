package github.kasuminova.ecoaeextension.common.block.ecotech.ecalculator;

import github.kasuminova.ecoaeextension.common.core.CreativeTabNovaEng;
import net.minecraft.block.Block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;



import javax.annotation.Nonnull;

@SuppressWarnings("deprecation")
public abstract class BlockECalculator extends Block {

    protected BlockECalculator() {
        super(Material.iron);
        this.translucent = true;
        this.fullBlock = false;
        this.lightOpacity = 0;
        this.setHardness(20.0F);
        this.setResistance(2000.0F);
        this.setSoundType(SoundType.METAL);
        this.setHarvestLevel("pickaxe", 2);
        this.setCreativeTab(CreativeTabNovaEng.INSTANCE);
    }

    @Override
    public boolean isOpaqueCube(@Nonnull final IBlockState state) {
        return false;
    }

    @Override
    public boolean canEntitySpawn(@Nonnull final IBlockState state, @Nonnull final Entity entityIn) {
        return false;
    }

}

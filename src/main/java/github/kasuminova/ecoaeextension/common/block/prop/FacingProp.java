package github.kasuminova.ecoaeextension.common.block.prop;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraftforge.common.util.ForgeDirection;
import github.kasuminova.ecoaeextension.common.util.EnumFacingCompat;

public class FacingProp {

    public static final PropertyEnum<ForgeDirection> HORIZONTALS = PropertyEnum.create("facing", ForgeDirection.class, EnumFacingCompat.HORIZONTALS);

}

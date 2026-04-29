package github.kasuminova.ecoaeextension.common.item;

import appeng.me.helpers.AENetworkProxy;
import appeng.me.helpers.IGridProxyable;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemBlockME extends ItemBlock {

    public ItemBlockME(final Block block) {
        super(block);
    }

    @Override
    public boolean onItemUse(@Nonnull final ItemStack stack,
                             @Nonnull final EntityPlayer player,
                             @Nonnull final World world,
                             int x, int y, int z,
                             int side,
                             float hitX, float hitY, float hitZ)
    {
        if (super.onItemUse(stack, player, world, x, y, z, side, hitX, hitY, hitZ)) {
            TileEntity tile = world.getTileEntity(x, y, z);
            if (tile == null) {
                // Try the adjacent block (the one just placed)
                switch (side) {
                    case 0: y--; break;
                    case 1: y++; break;
                    case 2: z--; break;
                    case 3: z++; break;
                    case 4: x--; break;
                    case 5: x++; break;
                }
                tile = world.getTileEntity(x, y, z);
            }
            if (tile instanceof IGridProxyable) {
                AENetworkProxy proxy = ((IGridProxyable) tile).getProxy();
                proxy.setOwner(player.getCommandSenderName());
            }
            return true;
        }
        return false;
    }

}

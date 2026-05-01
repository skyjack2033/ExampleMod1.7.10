package github.kasuminova.ecoaeextension.common.network;

import appeng.tile.inventory.InventoryAdapter;
import github.kasuminova.ecoaeextension.ECOAEExtension;
import github.kasuminova.ecoaeextension.common.container.ContainerEFabricatorPatternSearch;
import github.kasuminova.ecoaeextension.common.tile.ecotech.efabricator.EFabricatorController;
import github.kasuminova.ecoaeextension.common.tile.ecotech.efabricator.EFabricatorPatternBus;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import github.kasuminova.ecoaeextension.common.util.BlockPos;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

import java.util.List;

public class PktEFabricatorPatternSearchGUIAction implements IMessage, IMessageHandler<PktEFabricatorPatternSearchGUIAction, IMessage> {

    private Action action = null;
    private BlockPos pos = null;
    private int slot;

    public PktEFabricatorPatternSearchGUIAction() {
    }

    public PktEFabricatorPatternSearchGUIAction(final Action action) {
        this.action = action;
    }

    public PktEFabricatorPatternSearchGUIAction(final Action action, final BlockPos pos, final int slot) {
        this.action = action;
        this.pos = pos;
        this.slot = slot;
    }

    @Override
    public void fromBytes(final ByteBuf buf) {
        action = Action.values()[buf.readByte()];
        if (action == Action.PICKUP_PATTERN) {
            pos = BlockPos.fromLong(buf.readLong());
            slot = buf.readByte();
        }
    }

    @Override
    public void toBytes(final ByteBuf buf) {
        buf.writeByte(action.ordinal());
        if (action == Action.PICKUP_PATTERN) {
            buf.writeLong(pos.toLong());
            buf.writeByte(slot);
        }
    }

    @Override
    public IMessage onMessage(final PktEFabricatorPatternSearchGUIAction message, final MessageContext ctx) {
        final EntityPlayerMP player = ctx.getServerHandler().playerEntity;
        if (!(player.openContainer instanceof ContainerEFabricatorPatternSearch)) {
            return null;
        }

        ContainerEFabricatorPatternSearch efGUIContainer = (ContainerEFabricatorPatternSearch) player.openContainer;
        final EFabricatorController owner = efGUIContainer.getController();

        if (message.action == Action.PUT_PATTERN) {
            final ItemStack stackInMouse = player.inventory.getItemStack();
            if (stackInMouse.stackSize <= 0) {
                return null;
            }

            if (owner.insertPattern(stackInMouse)) {
                stackInMouse.stackSize--;
                if (stackInMouse.stackSize <= 0) {
                    player.inventory.setItemStack(null);
                    ECOAEExtension.NET_CHANNEL.sendTo(new PktMouseItemUpdate(null), player);
                } else {
                    ECOAEExtension.NET_CHANNEL.sendTo(new PktMouseItemUpdate(stackInMouse), player);
                }
            }

            return null;
        }

        final List<EFabricatorPatternBus> patternBuses = owner.getPatternBuses();
        for (final EFabricatorPatternBus patternBus : patternBuses) {
            final BlockPos pos = message.pos;
            if (!patternBus.getPos().equals(pos)) {
                continue;
            }

            final InventoryAdapter patterns = patternBus.getPatterns();
            final int slot = message.slot;
            final ItemStack stackInSlot = patterns.getStackInSlot(slot);

            if (stackInSlot != null && stackInSlot.stackSize > 0 && player.inventory.getItemStack().stackSize <= 0) {
                ItemStack newItemStack = patterns.extractItem(slot, stackInSlot.stackSize, false);
                player.inventory.setItemStack(newItemStack);
                ECOAEExtension.NET_CHANNEL.sendTo(new PktMouseItemUpdate(newItemStack), player);
            }

            break;
        }

        return null;
    }

    public enum Action {
        PICKUP_PATTERN,
        PUT_PATTERN,
    }

}

package github.kasuminova.ecoaeextension.common.network;

import github.kasuminova.ecoaeextension.ECOAEExtension;
import github.kasuminova.ecoaeextension.common.tile.ecotech.estorage.EStorageCellDrive;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.tileentity.TileEntity;
import github.kasuminova.ecoaeextension.common.util.BlockPos;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PktCellDriveStatusUpdate implements IMessage, IMessageHandler<PktCellDriveStatusUpdate, IMessage> {

    private BlockPos pos = null;
    private boolean writing = false;

    public PktCellDriveStatusUpdate() {
    }

    public PktCellDriveStatusUpdate(final BlockPos pos, final boolean writing) {
        this.pos = pos;
        this.writing = writing;
    }

    @Override
    public void fromBytes(final ByteBuf buf) {
        try {
            pos = BlockPos.fromLong(buf.readLong());
            writing = buf.readBoolean();
        } catch (Exception e) {
            ECOAEExtension.log.error("PktCellDriveStatusUpdate read failed.", e);
        }
    }

    @Override
    public void toBytes(final ByteBuf buf) {
        buf.writeLong(pos.toLong());
        buf.writeBoolean(writing);
    }

    @Override
    public IMessage onMessage(final PktCellDriveStatusUpdate message, final MessageContext ctx) {
        if (FMLCommonHandler.instance().getSide().isClient()) {
            processPacket(message);
        }
        return null;
    }

    @SideOnly(Side.CLIENT)
    protected static void processPacket(final PktCellDriveStatusUpdate message) {
        BlockPos pos = message.pos;
        boolean writing = message.writing;
        if (pos == null) {
            return;
        }

        WorldClient world = Minecraft.getMinecraft().theWorld;
        if (world == null) {
            return;
        }
        TileEntity te = world.getTileEntity(pos.getX(), pos.getY(), pos.getZ());
        if (!(te instanceof EStorageCellDrive drive)) {
            return;
        }
        drive.setWriting(writing);
        drive.markForUpdate();
    }

}

package hellfirepvp.modularmachinery.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;

public class ContainerBase<T extends TileEntity> extends Container {

    protected T owner;

    public ContainerBase() {
        this.owner = null;
    }

    public ContainerBase(T owner) {
        this.owner = owner;
    }

    public ContainerBase(T owner, EntityPlayer opening) {
        this.owner = owner;
    }

    public T getOwner() {
        return owner;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return owner != null && !owner.isInvalid()
                && player.getDistanceSq(owner.xCoord + 0.5, owner.yCoord + 0.5, owner.zCoord + 0.5) <= 64.0;
    }

    protected void addPlayerSlots(EntityPlayer opening) {
    }
}

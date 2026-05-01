package github.kasuminova.ecoaeextension.common.container;

import github.kasuminova.ecoaeextension.common.tile.ecotech.estorage.EStorageController;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

@Getter
@Setter
public class ContainerEStorageController extends Container {

    protected final EStorageController controller;
    protected int tickExisted = 0;

    public ContainerEStorageController(final EStorageController controller, final EntityPlayer player) {
        this.controller = controller;
        addPlayerSlots(player);
    }

    @Override
    public boolean canInteractWith(final EntityPlayer player) {
        return controller != null && !controller.isInvalid();
    }

    protected void addPlayerSlots(EntityPlayer opening) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(opening.inventory, j + i * 9 + 9, 8 + j * 18, 125 + i * 18));
            }
        }
        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(opening.inventory, i, 8 + i * 18, 183));
        }
    }

    // Backward compatibility
    public EStorageController getOwner() {
        return controller;
    }

}

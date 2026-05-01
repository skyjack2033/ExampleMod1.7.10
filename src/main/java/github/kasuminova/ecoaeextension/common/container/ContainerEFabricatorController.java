package github.kasuminova.ecoaeextension.common.container;

import github.kasuminova.ecoaeextension.common.tile.ecotech.efabricator.EFabricatorController;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

@Getter
@Setter
public class ContainerEFabricatorController extends Container {

    protected final EFabricatorController controller;
    protected int tickExisted = 0;

    public ContainerEFabricatorController(final EFabricatorController controller, final EntityPlayer player) {
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
                addSlotToContainer(new Slot(opening.inventory, j + i * 9 + 9, 18 + j * 18, 119 + i * 18));
            }
        }
        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(opening.inventory, i, 18 + i * 18, 177));
        }
    }

    // Backward compatibility
    public EFabricatorController getOwner() {
        return controller;
    }

}

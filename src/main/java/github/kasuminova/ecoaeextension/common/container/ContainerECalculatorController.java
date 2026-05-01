package github.kasuminova.ecoaeextension.common.container;

import github.kasuminova.ecoaeextension.common.tile.ecotech.ecalculator.ECalculatorController;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

@Getter
@Setter
public class ContainerECalculatorController extends Container {

    protected final ECalculatorController controller;
    protected int tickExisted = 0;

    public ContainerECalculatorController(final ECalculatorController controller, final EntityPlayer player) {
        this.controller = controller;
        addPlayerSlots(player);
    }

    @Override
    public boolean canInteractWith(final EntityPlayer player) {
        return controller != null && !controller.isInvalid();
    }

    protected void addPlayerSlots(final EntityPlayer opening) {
        // No player slots - calculator has no player inventory
    }

    // Backward compatibility
    public ECalculatorController getOwner() {
        return controller;
    }

}

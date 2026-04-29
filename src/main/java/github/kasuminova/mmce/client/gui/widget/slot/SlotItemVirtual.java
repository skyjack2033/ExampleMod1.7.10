package github.kasuminova.mmce.client.gui.widget.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;

public class SlotItemVirtual extends Slot {

    private static final IInventory EMPTY_INVENTORY = new InventoryBasic("[virtual]", false, 0);

    public SlotItemVirtual() {
        super(EMPTY_INVENTORY, 0, 0, 0);
    }

}

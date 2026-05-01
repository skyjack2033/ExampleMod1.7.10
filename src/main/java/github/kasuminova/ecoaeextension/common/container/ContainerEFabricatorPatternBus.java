package github.kasuminova.ecoaeextension.common.container;

import appeng.container.slot.SlotRestrictedInput;
import appeng.tile.inventory.InventoryAdapter;
import github.kasuminova.ecoaeextension.common.tile.ecotech.efabricator.EFabricatorPatternBus;
import lombok.Getter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerEFabricatorPatternBus extends Container {

    public static final int ROWS = 6;
    public static final int COLS = 12;

    @Getter
    private final EFabricatorPatternBus owner;

    public ContainerEFabricatorPatternBus(final EFabricatorPatternBus owner, final EntityPlayer player) {
        this.owner = owner;

        InventoryAdapter patterns = owner.getPatterns();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                this.addSlotToContainer(new SlotRestrictedInput(SlotRestrictedInput.PlacableItemType.ENCODED_PATTERN, patterns.getInventory(),
                        (row * COLS) + col, 8 + (col * 18), 28 + (row * 18), player.inventory));
            }
        }

        // Player inventory
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 150 + i * 18));
            }
        }
        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 208));
        }
    }

    @Override
    public boolean canInteractWith(final EntityPlayer player) {
        return owner != null && !owner.isInvalid();
    }

}

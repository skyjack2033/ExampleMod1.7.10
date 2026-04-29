package appeng.container.implementations;

import appeng.api.implementations.guiobjects.IGuiItemObject;
import appeng.container.slot.SlotRestrictedInput;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

/**
 * Stub for 1.7.10 port
 */
public class ContainerPatternEncoder extends Container {

    private SlotRestrictedInput patternSlotOUT;
    private IGuiItemObject iGuiItemObject;

    public SlotRestrictedInput getPatternSlotOUT() {
        return patternSlotOUT;
    }

    public IGuiItemObject getIGuiItemObject() {
        return iGuiItemObject;
    }

    public Object getPart() {
        return null;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return false;
    }
}

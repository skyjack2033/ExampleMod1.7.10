package appeng.tile.inventory;

import appeng.util.inv.IAEAppEngInventory;
import appeng.util.inv.filter.IAEItemFilter;

public class AppEngInternalInventory extends AppEngCellInventory {

    public AppEngInternalInventory(IAEAppEngInventory inventory, int slots) {
        super(inventory, slots);
    }

    public void setFilter(IAEItemFilter filter) {
    }
}

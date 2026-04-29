package appeng.me.storage;

import appeng.api.config.IncludeExclude;
import appeng.api.storage.ICellInventory;
import appeng.api.storage.ICellInventoryHandler;
import appeng.api.storage.data.IAEStack;

public class BasicCellInventoryHandler implements ICellInventoryHandler {
    public BasicCellInventoryHandler(Object cellInventory, Object channel) {}

    public IncludeExclude getIncludeExcludeMode() {
        return IncludeExclude.WHITELIST;
    }

    public boolean isFuzzy() {
        return false;
    }

    public boolean isPreformatted() {
        return false;
    }

    public ICellInventory getCellInv() {
        return null;
    }

    public boolean validForPass(int pass) {
        return true;
    }
}

package appeng.api.storage.data;

import appeng.api.storage.IMEInventory;

public interface ISaveProvider {
    void saveChanges(IMEInventory inv);
}

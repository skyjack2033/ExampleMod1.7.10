package appeng.api.storage;

import appeng.api.storage.data.IAEStack;
import appeng.api.storage.data.IItemList;

public interface IStorageChannel {
    <T extends IAEStack> IItemList<T> createList();
}

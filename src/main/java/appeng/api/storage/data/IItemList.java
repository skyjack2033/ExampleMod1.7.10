package appeng.api.storage.data;

import java.util.Iterator;

public interface IItemList<T extends IAEStack> extends Iterable<T> {
    T findPrecise(T stack);
    void add(T stack);
    int size();
    void resetStatus();
    @Override
    Iterator<T> iterator();
}

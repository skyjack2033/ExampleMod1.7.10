package appeng.api.storage.data;

public interface IItemList<T extends IAEStack<T>> extends Iterable<T> {
    void add(T option);
    T findPrecise(T i);
    T getFirstItem();
    void resetStatus();
    int size();
}

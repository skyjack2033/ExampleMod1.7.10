package mezz.jei.search;

import java.util.Collection;
import java.util.Set;

public interface ISearchStorage<T> {
    void put(String key, T value);
    void remove(String key, T value);
    Collection<T> search(String query);
    void getSearchResults(String query, Set<T> results);
    void clear();
}

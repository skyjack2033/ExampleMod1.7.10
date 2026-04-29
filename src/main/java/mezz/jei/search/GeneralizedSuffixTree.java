package mezz.jei.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class GeneralizedSuffixTree<T> implements ISearchStorage<T> {
    @Override public void put(String key, T value) {}
    @Override public void remove(String key, T value) {}
    @Override public Collection<T> search(String query) { return new ArrayList<>(); }
    @Override public void getSearchResults(String query, Set<T> results) {}
    @Override public void clear() {}
}

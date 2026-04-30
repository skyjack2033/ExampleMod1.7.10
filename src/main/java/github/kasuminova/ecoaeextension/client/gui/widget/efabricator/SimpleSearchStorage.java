package github.kasuminova.ecoaeextension.client.gui.widget.efabricator;

import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;

import java.util.Map;
import java.util.Set;

/**
 * Simple HashMap-based search storage replacing JEI's GeneralizedSuffixTree for 1.7.10 NEI compatibility.
 */
public class SimpleSearchStorage<T> {
    private final Map<String, Set<T>> index = new Object2ObjectLinkedOpenHashMap<>();

    public void put(String key, T value) {
        String lower = key.toLowerCase();
        index.computeIfAbsent(lower, k -> new ObjectLinkedOpenHashSet<>()).add(value);
    }

    public void getSearchResults(String query, Set<T> results) {
        if (query == null || query.trim().isEmpty()) {
            results.addAll(getAll());
            return;
        }
        String lower = query.toLowerCase();
        for (Map.Entry<String, Set<T>> entry : index.entrySet()) {
            if (entry.getKey().contains(lower)) {
                results.addAll(entry.getValue());
            }
        }
    }

    public Set<T> getAll() {
        Set<T> all = new ObjectLinkedOpenHashSet<>();
        for (Set<T> values : index.values()) {
            all.addAll(values);
        }
        return all;
    }

    public void clear() {
        index.clear();
    }
}

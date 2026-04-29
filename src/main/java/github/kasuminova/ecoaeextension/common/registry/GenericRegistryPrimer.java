package github.kasuminova.ecoaeextension.common.registry;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GenericRegistryPrimer {
    public static final GenericRegistryPrimer INSTANCE = new GenericRegistryPrimer();

    private GenericRegistryPrimer() {
    }

    private final Map<Class<?>, List<Object>> primed = new Object2ObjectOpenHashMap<>();

    public <V> V register(V entry) {
        Class<?> type = entry.getClass();
        List<Object> entries = primed.computeIfAbsent(type, k -> new LinkedList<>());
        entries.add(entry);
        return entry;
    }

    public List<?> getEntries(Class<?> type) {
        return primed.get(type);
    }

    public void wipe(Class<?> type) {
        primed.remove(type);
    }
}

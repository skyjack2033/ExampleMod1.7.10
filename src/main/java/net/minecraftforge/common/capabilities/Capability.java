package net.minecraftforge.common.capabilities;

public class Capability<T> {
    private final String name;

    public Capability(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public T cast(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        return t;
    }
}

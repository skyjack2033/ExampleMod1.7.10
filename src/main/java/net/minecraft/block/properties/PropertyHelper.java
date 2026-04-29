package net.minecraft.block.properties;

import com.google.common.base.Objects;

public abstract class PropertyHelper<T extends Comparable<T>> implements IProperty<T> {
    private final Class<T> valueClass;
    private final String name;

    protected PropertyHelper(String name, Class<T> valueClass) {
        this.name = name;
        this.valueClass = valueClass;
    }

    @Override
    public String getName() { return name; }

    @Override
    public Class<T> getValueClass() { return valueClass; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PropertyHelper)) return false;
        PropertyHelper<?> that = (PropertyHelper<?>) o;
        return Objects.equal(name, that.name) && Objects.equal(valueClass, that.valueClass);
    }

    @Override
    public int hashCode() {
        return 31 * valueClass.hashCode() + name.hashCode();
    }
}

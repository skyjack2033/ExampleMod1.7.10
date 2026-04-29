package net.minecraft.block.properties;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Map;

public class PropertyEnum<T extends Enum<T>> extends PropertyHelper<T> {
    private final ImmutableSet<T> allowedValues;
    private final Map<String, T> nameToValue = Maps.newHashMap();

    protected PropertyEnum(String name, Class<T> valueClass, Collection<T> allowedValues) {
        super(name, valueClass);
        this.allowedValues = ImmutableSet.copyOf(allowedValues);
        for (T t : allowedValues) {
            String s = ((Enum<?>) t).name().toLowerCase();
            if (nameToValue.containsKey(s)) {
                throw new IllegalArgumentException("Multiple values have the same name '" + s + "'");
            }
            nameToValue.put(s, t);
        }
    }

    public static <T extends Enum<T>> PropertyEnum<T> create(String name, Class<T> clazz) {
        return new PropertyEnum<>(name, clazz, Lists.newArrayList(clazz.getEnumConstants()));
    }

    public static <T extends Enum<T>> PropertyEnum<T> create(String name, Class<T> clazz, Collection<T> values) {
        return new PropertyEnum<>(name, clazz, values);
    }

    public Collection<T> getAllowedValues() {
        return allowedValues;
    }

    public Optional<T> parseValue(String value) {
        return Optional.fromNullable(nameToValue.get(value));
    }

    public String getName(T value) {
        return ((Enum<?>) value).name().toLowerCase();
    }

    @Override
    public String toString() {
        return "PropertyEnum{name=" + getName() + ", clazz=" + getValueClass() + ", values=" + allowedValues + "}";
    }
}

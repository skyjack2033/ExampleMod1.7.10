package net.minecraft.block.state;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import java.util.Collection;
import java.util.Map;

public class BlockStateContainer {
    private final Block block;
    private final ImmutableMap<IProperty<?>, Comparable<?>> properties;

    public BlockStateContainer(Block block, IProperty<?>... properties) {
        this.block = block;
        ImmutableMap.Builder<IProperty<?>, Comparable<?>> builder = ImmutableMap.builder();
        for (IProperty<?> prop : properties) {
            builder.put(prop, prop.getAllowedValues().iterator().next());
        }
        this.properties = builder.build();
    }

    public Block getBlock() { return block; }
    public Collection<IProperty<?>> getProperties() { return properties.keySet(); }

    public <T extends Comparable<T>> T getValue(IProperty<T> property) {
        @SuppressWarnings("unchecked")
        T result = (T) properties.get(property);
        return result;
    }
}

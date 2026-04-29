package net.minecraft.block.state;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import java.util.Collection;

public interface IBlockState {
    Collection<IProperty<?>> getPropertyNames();
    <T extends Comparable<T>> T getValue(IProperty<T> property);
    <T extends Comparable<T>, V extends T> IBlockState withProperty(IProperty<T> property, V value);
    ImmutableMap<IProperty<?>, Comparable<?>> getProperties();
    Block getBlock();
}

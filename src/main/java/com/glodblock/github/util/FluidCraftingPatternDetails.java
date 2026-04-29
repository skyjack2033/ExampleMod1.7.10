package com.glodblock.github.util;

import appeng.api.networking.crafting.ICraftingPatternDetails;
import appeng.api.storage.data.IAEItemStack;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

public class FluidCraftingPatternDetails implements ICraftingPatternDetails {

    
    public ItemStack getOutput(InventoryCrafting crafting, World world) {
        return null;
    }

    @Nonnull
    
    public IAEItemStack[] getOutputs() {
        return new IAEItemStack[0];
    }

    
    public IAEItemStack[] getInputs() {
        return new IAEItemStack[0];
    }

    
    public IAEItemStack[] getCondensedOutputs() {
        return new IAEItemStack[0];
    }

    
    public IAEItemStack[] getCondensedInputs() {
        return new IAEItemStack[0];
    }

    
    public ItemStack getPattern() {
        return null;
    }

    
    public boolean isCraftable() {
        return false;
    }

    
    public boolean canSubstitute() {
        return false;
    }

    
    public List<IAEItemStack> getSubstituteInputs(int slot) {
        return Collections.emptyList();
    }

    
    public boolean isValidItemForSlot(int slot, ItemStack stack, World world) {
        return false;
    }

    
    public int getPriority() {
        return 0;
    }

    
    public void setPriority(int priority) {
    }

}

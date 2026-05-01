package github.kasuminova.ecoaeextension.common.tile.ecotech.efabricator;

import appeng.api.implementations.ICraftingPatternItem;
import appeng.api.networking.GridFlags;
import appeng.api.networking.IGridNode;
import appeng.api.networking.crafting.ICraftingPatternDetails;
import appeng.api.networking.crafting.ICraftingProvider;
import appeng.api.networking.crafting.ICraftingProviderHelper;
import appeng.api.networking.events.MENetworkChannelsChanged;
import appeng.api.networking.events.MENetworkCraftingPatternChange;
import appeng.api.networking.events.MENetworkEventSubscribe;
import appeng.api.networking.events.MENetworkPowerStatusChange;
import appeng.api.networking.security.IActionHost;
import appeng.api.networking.security.IActionSource;
import appeng.api.storage.data.IAEItemStack;
import appeng.api.util.AECableType;
import appeng.api.util.AEPartLocation;
import appeng.api.util.DimensionalCoord;
import net.minecraftforge.common.util.ForgeDirection;
import appeng.me.GridAccessException;
import appeng.me.helpers.AENetworkProxy;
import appeng.me.helpers.IGridProxyable;
import appeng.me.helpers.MachineSource;
import github.kasuminova.ecoaeextension.common.block.ecotech.efabricator.BlockEFabricatorMEChannel;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class EFabricatorMEChannel extends EFabricatorPart implements ICraftingProvider, IActionHost, IGridProxyable {

    protected final AENetworkProxy proxy = new AENetworkProxy(this, "channel", getVisualItemStack(), true);

    public AECableType getCableConnectionType(ForgeDirection dir) { return AECableType.SMART; }
    protected final IActionSource source = new MachineSource(this);

    private boolean wasActive = false;

    public EFabricatorMEChannel() {
        this.proxy.setIdlePowerUsage(1.0D);
        this.proxy.setFlags(GridFlags.REQUIRE_CHANNEL, GridFlags.DENSE_CAPACITY);
    }

    public IActionSource getSource() {
        return source;
    }

    public ItemStack getVisualItemStack() {
        EFabricatorController controller = getController();
        return new ItemStack(Item.getItemFromBlock(controller == null ? BlockEFabricatorMEChannel.INSTANCE : controller.getParentController()), 1, 0);
    }

    @MENetworkEventSubscribe
    public void stateChange(final MENetworkPowerStatusChange c) {
        postPatternChangeEvent();
    }

    @MENetworkEventSubscribe
    public void stateChange(final MENetworkChannelsChanged c) {
        postPatternChangeEvent();
    }

    protected void postPatternChangeEvent() {
        final boolean currentActive = this.proxy.isActive();
        if (this.wasActive != currentActive) {
            this.wasActive = currentActive;
            try {
                this.proxy.getGrid().postEvent(new MENetworkCraftingPatternChange(this, proxy.getNode()));
            } catch (final Exception ignored) {
            }
        }
    }

    // Crafting Provider

    @Override
    public void provideCrafting(final ICraftingProviderHelper craftingTracker) {
        EFabricatorController controller = getController();
        if (controller == null) {
            return;
        }

        List<EFabricatorPatternBus> patternBuses = controller.getPatternBuses();
        patternBuses.stream()
                .flatMap(patternBus -> patternBus.getDetails().stream())
                .filter(details -> details.isCraftable() || FluidCraftUtil.isFluidPattern(details))
                .forEach(details -> craftingTracker.addCraftingOption(this, details));
    }

    @Override
    public boolean pushPattern(final ICraftingPatternDetails pattern, final InventoryCrafting table) {
        if (isBusy()) {
            return false;
        }

        if (!pattern.isCraftable()) {
            if (FluidCraftUtil.isFluidPattern(pattern)) {
                return pushFluidPattern(pattern, table);
            }
            return false;
        }

        ItemStack output = pattern.getOutput(table, this.getWorld());
        if (output == null || output.stackSize <= 0) {
            return false;
        }

        ItemStack[] remaining = new ItemStack[9];
        int size = 0;
        for (int i = 0; i < Math.min(table.getSizeInventory(), 9); ++i) {
            ItemStack item = table.getStackInSlot(i);
            if (item == null || item.stackSize <= 0){
                remaining[i] = null;
            } else {
                if (size == 0) {
                    size = item.stackSize;
                }
                remaining[i] = getContainerItem(item);
            }
        }

        output.stackSize = output.stackSize * size;

        return partController.offerWork(new EFabricatorWorker.CraftWork(remaining, output, size));
    }

    protected boolean pushFluidPattern(final ICraftingPatternDetails pattern, final InventoryCrafting table) {
        return FluidCraftUtil.pushFluidPattern(pattern, table, partController);
    }

    private static ItemStack getContainerItem(ItemStack stackInSlot) {
        if (stackInSlot == null) {
            return null;
        } else {
            Item i = stackInSlot.getItem();
            if (i != null && i.hasContainerItem(stackInSlot)) {
                ItemStack ci = i.getContainerItem(stackInSlot);
                if (ci != null && ci.stackSize > 0 && ci.isItemStackDamageable() && ci.getItemDamage() == ci.getMaxDamage()) {
                    ci = null;
                }

                ci.stackSize = stackInSlot.stackSize;
                return ci;
            } else if (stackInSlot != null && stackInSlot.stackSize > 0) {
                stackInSlot.stackSize = 0;
                return stackInSlot;
            } else return null;
        }
    }

    public boolean insertPattern(final ItemStack patternStack) {
        if (patternStack == null || patternStack.stackSize <= 0 || !(patternStack.getItem() instanceof ICraftingPatternItem)) {
            return false;
        }
        if (partController != null) {
            return partController.insertPattern(patternStack);
        }
        return false;
    }

    @Override
    public boolean isBusy() {
        if (partController != null) {
            return partController.isQueueFull();
        }
        return false;
    }

    // IActionHost

    @Override
    public IGridNode getActionableNode() {
        return proxy.getNode();
    }

    // IGridProxyable

    @Override
    public IGridNode getGridNode(ForgeDirection dir) {
        return proxy.getNode();
    }

    @Override
    public AENetworkProxy getProxy() {
        return proxy;
    }

    @Override
    public void gridChanged() {
    }

    public void securityBreak() {
    }

    // NBT

    @Override
    public void readCustomNBT(NBTTagCompound tag) {
        super.readCustomNBT(tag);
        proxy.readFromNBT(tag);
    }

    @Override
    public void writeCustomNBT(NBTTagCompound tag) {
        super.writeCustomNBT(tag);
        proxy.writeToNBT(tag);
    }

    @Override
    public void onAssembled() {
        super.onAssembled();
        proxy.onReady();
    }

    @Override
    public void onDisassembled() {
        super.onDisassembled();
        proxy.invalidate();
    }

    @Override
    public DimensionalCoord getLocation() {
        return new DimensionalCoord(this);
    }

    // Helper class for FluidCraft integration via reflection
    private static class FluidCraftUtil {
        private static final String FLUID_PATTERN_CLASS = "com.glodblock.github.util.FluidCraftingPatternDetails";
        private static final String ITEM_FLUID_PACKET_CLASS = "com.glodblock.github.common.item.ItemFluidPacket";
        private static final String FAKE_ITEM_REGISTER_CLASS = "com.glodblock.github.common.item.fake.FakeItemRegister";

        private static Boolean fluidCraftAvailable = null;

        private static boolean isFluidCraftAvailable() {
            if (fluidCraftAvailable == null) {
                try {
                    Class.forName(FLUID_PATTERN_CLASS);
                    fluidCraftAvailable = true;
                } catch (ClassNotFoundException e) {
                    fluidCraftAvailable = false;
                }
            }
            return fluidCraftAvailable;
        }

        static boolean isFluidPattern(ICraftingPatternDetails detail) {
            if (!isFluidCraftAvailable()) return false;
            try {
                Class<?> fluidPatternCls = Class.forName(FLUID_PATTERN_CLASS);
                return fluidPatternCls.isInstance(detail);
            } catch (ClassNotFoundException e) {
                return false;
            }
        }

        static boolean pushFluidPattern(ICraftingPatternDetails pattern, InventoryCrafting table, EFabricatorController controller) {
            if (!isFluidCraftAvailable()) return false;

            try {
                // Get outputs using reflection
                Class<?> fluidPatternCls = Class.forName(FLUID_PATTERN_CLASS);
                Method getOutputsMethod = fluidPatternCls.getMethod("getOutputs");
                IAEItemStack[] outputs = (IAEItemStack[]) getOutputsMethod.invoke(pattern);
                ItemStack output = outputs[0] != null ? outputs[0].getItemStack() : null;

                if (output == null || output.stackSize <= 0) return false;

                // Get inputs using reflection
                Method getInputsMethod = fluidPatternCls.getMethod("getInputs");
                Object[] inputs = (Object[]) getInputsMethod.invoke(pattern);

                Class<?> itemFluidPacketCls = Class.forName(ITEM_FLUID_PACKET_CLASS);
                Class<?> fakeItemRegisterCls = Class.forName(FAKE_ITEM_REGISTER_CLASS);
                Method getStackMethod = fakeItemRegisterCls.getMethod("getStack", net.minecraft.item.ItemStack.class);

                ItemStack[] remaining = new ItemStack[9];
                int size = 0;
                for (int i = 0; i < Math.min(table.getSizeInventory(), 9); ++i) {
                    ItemStack item = table.getStackInSlot(i);
                    if (item == null || item.stackSize <= 0) {
                        remaining[i] = null;
                    } else {
                        if (size == 0) {
                            size = item.stackSize;
                            if (itemFluidPacketCls.isInstance(item)) {
                                FluidStack fluidStack = (FluidStack) getStackMethod.invoke(null, item);
                                FluidStack inputFluid = (FluidStack) getStackMethod.invoke(null, getInputItemStack(inputs[i]));
                                if (fluidStack != null && inputFluid != null && inputFluid.amount > 0) {
                                    size = fluidStack.amount / inputFluid.amount;
                                }
                            }
                        }
                        remaining[i] = getContainerItemStatic(item);
                    }
                }

                output.stackSize = output.stackSize * size;

                return controller.offerWork(new EFabricatorWorker.CraftWork(remaining, output, size));
            } catch (Exception e) {
                return false;
            }
        }

        private static ItemStack getInputItemStack(Object input) {
            try {
                Method getItemStackMethod = input.getClass().getMethod("getItemStack");
                return (ItemStack) getItemStackMethod.invoke(input);
            } catch (Exception e) {
                return null;
            }
        }

        private static ItemStack getContainerItemStatic(ItemStack stackInSlot) {
            if (stackInSlot == null) {
                return null;
            } else {
                Item i = stackInSlot.getItem();
                if (i != null && i.hasContainerItem(stackInSlot)) {
                    ItemStack ci = i.getContainerItem(stackInSlot);
                    if (ci != null && ci.stackSize > 0 && ci.isItemStackDamageable() && ci.getItemDamage() == ci.getMaxDamage()) {
                        ci = null;
                    }
                    if (ci != null) {
                        ci.stackSize = stackInSlot.stackSize;
                    }
                    return ci;
                } else if (stackInSlot != null && stackInSlot.stackSize > 0) {
                    stackInSlot.stackSize = 0;
                    return stackInSlot;
                } else return null;
            }
        }
    }
}

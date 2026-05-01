package github.kasuminova.ecoaeextension.common.tile.ecotech.efabricator;

import appeng.api.implementations.ICraftingPatternItem;
import appeng.api.networking.crafting.ICraftingPatternDetails;
import appeng.api.networking.events.MENetworkCraftingPatternChange;
import appeng.tile.inventory.AppEngInternalInventory;
import appeng.tile.inventory.IAEAppEngInventory;
import appeng.tile.inventory.InvOperation;
import appeng.tile.inventory.InventoryAdapter;
import github.kasuminova.ecoaeextension.ECOAEExtension;
import github.kasuminova.ecoaeextension.common.container.ContainerEFabricatorPatternSearch;
import github.kasuminova.ecoaeextension.common.container.data.EFabricatorPatternData;
import github.kasuminova.ecoaeextension.common.network.PktEFabricatorPatternSearchGUIUpdate;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EFabricatorPatternBus extends EFabricatorPart implements IAEAppEngInventory {

    public static final int PATTERN_SLOTS = 12 * 6;

    protected final InventoryAdapter patterns = new InventoryAdapter(new AppEngInternalInventory(this, PATTERN_SLOTS));
    protected final List<ICraftingPatternDetails> details = new ObjectArrayList<>(PATTERN_SLOTS);

    public EFabricatorPatternBus() {
        // Initialize details...
        IntStream.range(0, PATTERN_SLOTS).<ICraftingPatternDetails>mapToObj(i -> null).forEach(details::add);
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    protected void refreshPatterns() {
        for (int i = 0; i < PATTERN_SLOTS; i++) {
            refreshPattern(i);
        }
        notifyPatternChanged();
    }

    protected void refreshPattern(final int slot) {
        details.set(slot, null);

        ItemStack pattern = patterns.getStackInSlot(slot);
        Item item = pattern.getItem();
        if (pattern.stackSize <= 0 || !(item instanceof ICraftingPatternItem)) {
            return;
        }
        ICraftingPatternItem patternItem = (ICraftingPatternItem) item;

        ICraftingPatternDetails detail = patternItem.getPatternForItem(pattern, getWorld());
        // Accept item patterns or fluid patterns (if FluidCraft is available)
        if (detail != null && (detail.isCraftable() || isFluidPattern(detail))) {
            details.set(slot, detail);
        }
    }

    private static final String FLUID_PATTERN_CLASS = "com.glodblock.github.util.FluidCraftingPatternDetails";
    private static Boolean fluidPatternAvailable = null;

    private static boolean isFluidPattern(ICraftingPatternDetails detail) {
        if (fluidPatternAvailable == null) {
            try {
                Class.forName(FLUID_PATTERN_CLASS);
                fluidPatternAvailable = true;
            } catch (ClassNotFoundException e) {
                fluidPatternAvailable = false;
            }
        }
        if (!fluidPatternAvailable) return false;
        try {
            Class<?> fluidPatternCls = Class.forName(FLUID_PATTERN_CLASS);
            return fluidPatternCls.isInstance(detail);
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public InventoryAdapter getPatterns() {
        return patterns;
    }

    public List<ICraftingPatternDetails> getDetails() {
        return details.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public int getValidPatterns() {
        return (int) details.stream().filter(Objects::nonNull).count();
    }

    public void saveChanges() {
        markNoUpdateSync();
    }

    @Override
    public void onChangeInventory(final IInventory inv, final int slot, final InvOperation mc, final ItemStack removedStack, final ItemStack newStack) {
        refreshPattern(slot);
        notifyPatternChanged();
        sendPatternSearchGUIUpdateToClient(slot);
    }

    private void notifyPatternChanged() {
        if (this.partController == null) {
            return;
        }
        try {
            EFabricatorMEChannel channel = this.partController.getChannel();
            if (channel != null && channel.getProxy().isActive()) {
                channel.getProxy().getGrid().postEvent(new MENetworkCraftingPatternChange(channel, channel.getProxy().getNode()));
            }
        } catch (Exception ignored) {
        }
        this.partController.recalculateEnergyUsage();
    }

    private void sendPatternSearchGUIUpdateToClient(final int slot) {
        if (this.partController == null) {
            return;
        }

        List<EntityPlayerMP> players = new ArrayList<>();
        getWorld().playerEntities.stream()
                .filter(EntityPlayerMP.class::isInstance)
                .map(EntityPlayerMP.class::cast)
                .forEach(playerMP -> {
                    if (playerMP.openContainer instanceof ContainerEFabricatorPatternSearch) {
                        ContainerEFabricatorPatternSearch efPatternSearch = (ContainerEFabricatorPatternSearch) playerMP.openContainer;
                        if (efPatternSearch.getOwner() == this.partController) {
                            players.add(playerMP);
                        }
                    }
                });

        if (players != null && players.size() > 0) {
            PktEFabricatorPatternSearchGUIUpdate pktUpdate = new PktEFabricatorPatternSearchGUIUpdate(
                    PktEFabricatorPatternSearchGUIUpdate.UpdateType.SINGLE,
                    EFabricatorPatternData.of(
                            new EFabricatorPatternData.PatternData(getPos(), slot, patterns.getStackInSlot(slot))
                    )
            );
            players.forEach(player -> ECOAEExtension.NET_CHANNEL.sendTo(pktUpdate, player));
        }
    }

    @Override
    public void validate() {
        super.validate();
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            refreshPatterns();
        }
    }


    @Override
    public void readCustomNBT(final NBTTagCompound compound) {
        super.readCustomNBT(compound);
        ((AppEngInternalInventory) patterns.getInventory()).readFromNBT(compound.getCompoundTag("patterns"));
    }

    @Override
    public void writeCustomNBT(final NBTTagCompound compound) {
        super.writeCustomNBT(compound);
        ((AppEngInternalInventory) patterns.getInventory()).writeToNBT(compound, "patterns");
    }

}

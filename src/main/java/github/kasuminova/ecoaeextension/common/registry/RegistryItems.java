package github.kasuminova.ecoaeextension.common.registry;

import github.kasuminova.ecoaeextension.ECOAEExtension;
import github.kasuminova.ecoaeextension.client.renderer.EStorageEnergyCellItemRenderer;
import github.kasuminova.ecoaeextension.common.item.ecalculator.ECalculatorCell;
import github.kasuminova.ecoaeextension.common.item.estorage.EStorageCellFluid;
import github.kasuminova.ecoaeextension.common.item.estorage.EStorageCellGas;
import github.kasuminova.ecoaeextension.common.item.estorage.EStorageCellItem;
import github.kasuminova.ecoaeextension.common.item.estorage.ItemBlockEStorageEnergyCell;
import hellfirepvp.modularmachinery.common.base.Mods;
import hellfirepvp.modularmachinery.common.item.ItemDynamicColor;
import hellfirepvp.modularmachinery.common.registry.RegistryEvent;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.*;

@SuppressWarnings({"MethodMayBeStatic", "UnusedReturnValue"})
public class RegistryItems {
    public static final List<Item> ITEMS_TO_REGISTER = new LinkedList<>();
    public static final List<Item> ITEMS_TO_REGISTER_CT = new LinkedList<>();
    public static final Map<String, Item> CUSTOM_MODEL_ITEMS_TO_REGISTER_CT = new Object2ObjectLinkedOpenHashMap<>();

    public static final List<Item> ITEM_MODELS_TO_REGISTER = new LinkedList<>();
    public static final Map<String, Item> ITEM_CUSTOM_MODELS_TO_REGISTER = new Object2ObjectLinkedOpenHashMap<>();

    @SubscribeEvent
    public void registerItems(RegistryEvent.RegisterItems event) {
        ITEMS_TO_REGISTER.add(EStorageCellItem.LEVEL_A);
        ITEMS_TO_REGISTER.add(EStorageCellItem.LEVEL_B);
        ITEMS_TO_REGISTER.add(EStorageCellItem.LEVEL_C);
        ITEMS_TO_REGISTER.add(EStorageCellFluid.LEVEL_A);
        ITEMS_TO_REGISTER.add(EStorageCellFluid.LEVEL_B);
        ITEMS_TO_REGISTER.add(EStorageCellFluid.LEVEL_C);
        if (Mods.MEKENG.isPresent()) {
            ITEMS_TO_REGISTER.add(EStorageCellGas.LEVEL_A);
            ITEMS_TO_REGISTER.add(EStorageCellGas.LEVEL_B);
            ITEMS_TO_REGISTER.add(EStorageCellGas.LEVEL_C);
        }
        ITEMS_TO_REGISTER.add(ECalculatorCell.L4);
        ITEMS_TO_REGISTER.add(ECalculatorCell.L6);
        ITEMS_TO_REGISTER.add(ECalculatorCell.L9);

        registerItems();
    }

    public static void registerItems() {
        ITEMS_TO_REGISTER.forEach(RegistryItems::registerItem);
        ITEMS_TO_REGISTER.clear();
        ITEMS_TO_REGISTER_CT.forEach(RegistryItems::registerItem);
        ITEMS_TO_REGISTER_CT.clear();
        CUSTOM_MODEL_ITEMS_TO_REGISTER_CT.forEach((path, item) -> registerItem(item, path));
        CUSTOM_MODEL_ITEMS_TO_REGISTER_CT.clear();
    }

    public static void registerItemModels() {
        if (FMLCommonHandler.instance().getSide().isServer()) {
            ITEM_MODELS_TO_REGISTER.clear();
            ITEM_CUSTOM_MODELS_TO_REGISTER.clear();
            return;
        }
        ITEM_MODELS_TO_REGISTER.forEach(RegistryItems::registerItemModel);
        ITEM_MODELS_TO_REGISTER.clear();
        ITEM_CUSTOM_MODELS_TO_REGISTER.forEach((path, item) -> registerItemModel(item, path));
        ITEM_CUSTOM_MODELS_TO_REGISTER.clear();
        setMeshDef();
    }

    @SideOnly(Side.CLIENT)
    private static void setMeshDef() {
        // Item mesh definition registration - stubbed for 1.7.10 port
    }

    public static <T extends Item> T registerItem(T item) {
        ITEM_MODELS_TO_REGISTER.add(item);
        GenericRegistryPrimer.INSTANCE.register(item);

        // Register with GameRegistry using unlocalized name (1.7.10 style)
        String name = item.getUnlocalizedName();
        if (name != null && name.startsWith("item.")) {
            name = name.substring(5);
        }
        GameRegistry.registerItem(item, name);

        // pendingDynamicColorItems not available in this MMCE version
        // if (item instanceof ItemDynamicColor) {
        //     hellfirepvp.modularmachinery.common.registry.RegistryItems.pendingDynamicColorItems.add((ItemDynamicColor) item);
        // }
        return item;
    }

    public static <T extends Item> T registerItem(T item, String modelPath) {
        ITEM_CUSTOM_MODELS_TO_REGISTER.put(modelPath, item);
        GenericRegistryPrimer.INSTANCE.register(item);

        // Register with GameRegistry using unlocalized name (1.7.10 style)
        String name = item.getUnlocalizedName();
        if (name != null && name.startsWith("item.")) {
            name = name.substring(5);
        }
        GameRegistry.registerItem(item, name);

        // pendingDynamicColorItems not available in this MMCE version
        // if (item instanceof ItemDynamicColor) {
        //     hellfirepvp.modularmachinery.common.registry.RegistryItems.pendingDynamicColorItems.add((ItemDynamicColor) item);
        // }
        return item;
    }

    public static void registerItemModel(final Item item) {
        // Item model registration - stubbed for 1.7.10 port
    }

    public static void registerItemModel(final Item item, final String modelPath) {
        // Item model registration - stubbed for 1.7.10 port
    }
}

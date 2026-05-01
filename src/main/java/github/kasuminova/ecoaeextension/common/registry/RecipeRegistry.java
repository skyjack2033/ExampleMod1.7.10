package github.kasuminova.ecoaeextension.common.registry;

import github.kasuminova.ecoaeextension.common.block.ecotech.ecalculator.*;
import github.kasuminova.ecoaeextension.common.block.ecotech.efabricator.*;
import github.kasuminova.ecoaeextension.common.block.ecotech.estorage.*;
import github.kasuminova.ecoaeextension.common.item.ecalculator.ECalculatorCell;
import github.kasuminova.ecoaeextension.common.item.estorage.EStorageCellFluid;
import github.kasuminova.ecoaeextension.common.item.estorage.EStorageCellItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

@SuppressWarnings("MethodMayBeStatic")
public class RecipeRegistry {

    public static void registerRecipes() {
        // --- Helper for getting GT materials via OreDictionary ---
        // These helper methods will try GT materials first, falling back to vanilla

        // --- EStorage recipes ---
        // Controller L4
        GameRegistry.addShapedRecipe(new ItemStack(BlockEStorageController.L4),
                "CMC", "MFM", "CMC",
                'C', getOreIngot("Iron"),
                'M', getOreDust("Redstone"),
                'F', new ItemStack(Blocks.glass));
        // Controller L6
        GameRegistry.addShapedRecipe(new ItemStack(BlockEStorageController.L6),
                "CMC", "MFM", "CMC",
                'C', getOreGem("Diamond"),
                'M', getOreDust("Redstone"),
                'F', new ItemStack(BlockEStorageController.L4));
        // Controller L9
        GameRegistry.addShapedRecipe(new ItemStack(BlockEStorageController.L9),
                "CMC", "MFM", "CMC",
                'C', getOreGem("Emerald"),
                'M', getOreDust("Redstone"),
                'F', new ItemStack(BlockEStorageController.L6));
        // Casing
        GameRegistry.addShapedRecipe(new ItemStack(BlockEStorageCasing.INSTANCE, 4),
                "ICI", "C C", "ICI",
                'I', getOreIngot("Iron"),
                'C', new ItemStack(Blocks.iron_block));
        // Vent
        GameRegistry.addShapedRecipe(new ItemStack(BlockEStorageVent.INSTANCE, 2),
                " I ", "IGI", " I ",
                'I', getOreIngot("Iron"),
                'G', new ItemStack(Blocks.iron_bars));
        // Cell Drive
        GameRegistry.addShapedRecipe(new ItemStack(BlockEStorageCellDrive.INSTANCE),
                "IGI", "CEC", "IGI",
                'I', getOreIngot("Iron"),
                'G', new ItemStack(Blocks.glass),
                'C', new ItemStack(BlockEStorageCasing.INSTANCE),
                'E', getOreGem("EnderPearl"));
        // ME Channel
        GameRegistry.addShapedRecipe(new ItemStack(BlockEStorageMEChannel.INSTANCE),
                "IGI", "GFG", "IGI",
                'I', getOreIngot("Iron"),
                'G', new ItemStack(Blocks.glass),
                'F', getOreGem("CertusQuartz"));
        // Energy Cell L4
        GameRegistry.addShapedRecipe(new ItemStack(BlockEStorageEnergyCell.L4),
                "IRI", "RER", "IRI",
                'I', getOreIngot("Iron"),
                'R', getOreDust("Redstone"),
                'E', getOreGem("EnderPearl"));
        // Energy Cell L6
        GameRegistry.addShapedRecipe(new ItemStack(BlockEStorageEnergyCell.L6),
                "IRI", "RER", "IRI",
                'I', getOreGem("Diamond"),
                'R', getOreDust("Redstone"),
                'E', new ItemStack(BlockEStorageEnergyCell.L4));
        // Energy Cell L9
        GameRegistry.addShapedRecipe(new ItemStack(BlockEStorageEnergyCell.L9),
                "IRI", "RER", "IRI",
                'I', getOreGem("Emerald"),
                'R', getOreDust("Redstone"),
                'E', new ItemStack(BlockEStorageEnergyCell.L6));

        // --- EFabricator recipes ---
        GameRegistry.addShapedRecipe(new ItemStack(BlockEFabricatorController.L4),
                "CMC", "MFM", "CMC",
                'C', new ItemStack(Blocks.iron_block),
                'M', getOreIngot("Gold"),
                'F', getOreDust("Redstone"));
        GameRegistry.addShapedRecipe(new ItemStack(BlockEFabricatorController.L6),
                "CMC", "MFM", "CMC",
                'C', getOreGem("Diamond"),
                'M', getOreIngot("Gold"),
                'F', new ItemStack(BlockEFabricatorController.L4));
        GameRegistry.addShapedRecipe(new ItemStack(BlockEFabricatorController.L9),
                "CMC", "MFM", "CMC",
                'C', getOreGem("Emerald"),
                'M', getOreIngot("Gold"),
                'F', new ItemStack(BlockEFabricatorController.L6));
        GameRegistry.addShapedRecipe(new ItemStack(BlockEFabricatorCasing.INSTANCE, 4),
                "IGI", "GCG", "IGI",
                'I', getOreIngot("Iron"),
                'G', getOreIngot("Gold"),
                'C', new ItemStack(Blocks.iron_block));
        GameRegistry.addShapedRecipe(new ItemStack(BlockEFabricatorVent.INSTANCE, 2),
                " G ", "GIG", " G ",
                'G', getOreIngot("Gold"),
                'I', new ItemStack(Blocks.iron_bars));
        GameRegistry.addShapedRecipe(new ItemStack(BlockEFabricatorMEChannel.INSTANCE),
                "IGI", "GFG", "IGI",
                'I', getOreIngot("Gold"),
                'G', new ItemStack(Blocks.glass),
                'F', getOreGem("CertusQuartz"));
        GameRegistry.addShapedRecipe(new ItemStack(BlockEFabricatorPatternBus.INSTANCE),
                "IGI", "GFG", "IGI",
                'I', getOreIngot("Gold"),
                'G', new ItemStack(Blocks.glass),
                'F', getOreGem("Diamond"));
        GameRegistry.addShapedRecipe(new ItemStack(BlockEFabricatorWorker.INSTANCE, 2),
                "IPI", "PCP", "IPI",
                'I', getOreIngot("Iron"),
                'P', new ItemStack(Blocks.piston),
                'C', new ItemStack(BlockEFabricatorCasing.INSTANCE));
        GameRegistry.addShapedRecipe(new ItemStack(BlockEFabricatorParallelProc.L4),
                "CRC", "RPR", "CRC",
                'C', new ItemStack(BlockEFabricatorCasing.INSTANCE),
                'R', getOreDust("Redstone"),
                'P', getOreIngot("Gold"));
        GameRegistry.addShapedRecipe(new ItemStack(BlockEFabricatorParallelProc.L6),
                "CRC", "RPR", "CRC",
                'C', getOreGem("Diamond"),
                'R', getOreDust("Redstone"),
                'P', new ItemStack(BlockEFabricatorParallelProc.L4));
        GameRegistry.addShapedRecipe(new ItemStack(BlockEFabricatorParallelProc.L9),
                "CRC", "RPR", "CRC",
                'C', getOreGem("Emerald"),
                'R', getOreDust("Redstone"),
                'P', new ItemStack(BlockEFabricatorParallelProc.L6));

        // --- ECalculator recipes ---
        GameRegistry.addShapedRecipe(new ItemStack(BlockECalculatorController.L4),
                "CMC", "MFM", "CMC",
                'C', new ItemStack(Blocks.iron_block),
                'M', getOreGem("Diamond"),
                'F', new ItemStack(Items.blaze_rod));
        GameRegistry.addShapedRecipe(new ItemStack(BlockECalculatorController.L6),
                "CMC", "MFM", "CMC",
                'C', getOreGem("Diamond"),
                'M', getOreGem("Diamond"),
                'F', new ItemStack(BlockECalculatorController.L4));
        GameRegistry.addShapedRecipe(new ItemStack(BlockECalculatorController.L9),
                "CMC", "MFM", "CMC",
                'C', getOreGem("Emerald"),
                'M', getOreGem("Diamond"),
                'F', new ItemStack(BlockECalculatorController.L6));
        GameRegistry.addShapedRecipe(new ItemStack(BlockECalculatorCasing.INSTANCE, 4),
                "IDI", "DCD", "IDI",
                'I', getOreIngot("Iron"),
                'D', getOreGem("Diamond"),
                'C', new ItemStack(Blocks.iron_block));
        GameRegistry.addShapedRecipe(new ItemStack(BlockECalculatorTail.L4, 2),
                " I ", "ICI", " I ",
                'I', getOreIngot("Iron"),
                'C', new ItemStack(BlockECalculatorCasing.INSTANCE));
        GameRegistry.addShapedRecipe(new ItemStack(BlockECalculatorTail.L6, 2),
                " I ", "ICI", " I ",
                'I', getOreGem("Diamond"),
                'C', new ItemStack(BlockECalculatorCasing.INSTANCE));
        GameRegistry.addShapedRecipe(new ItemStack(BlockECalculatorTail.L9, 2),
                " I ", "ICI", " I ",
                'I', getOreGem("Emerald"),
                'C', new ItemStack(BlockECalculatorCasing.INSTANCE));
        GameRegistry.addShapedRecipe(new ItemStack(BlockECalculatorMEChannel.INSTANCE),
                "IDI", "GFG", "IDI",
                'I', getOreGem("Diamond"),
                'D', getOreGem("Diamond"),
                'G', new ItemStack(Blocks.glass),
                'F', getOreGem("CertusQuartz"));
        GameRegistry.addShapedRecipe(new ItemStack(BlockECalculatorCellDrive.INSTANCE),
                "IDI", "CEC", "IDI",
                'I', getOreGem("Diamond"),
                'D', getOreGem("Diamond"),
                'C', new ItemStack(BlockECalculatorCasing.INSTANCE),
                'E', getOreGem("EnderPearl"));
        GameRegistry.addShapedRecipe(new ItemStack(BlockECalculatorTransmitterBus.INSTANCE),
                "IDI", "DRD", "IDI",
                'I', getOreGem("Diamond"),
                'D', getOreGem("Diamond"),
                'R', getOreDust("Redstone"));
        GameRegistry.addShapedRecipe(new ItemStack(BlockECalculatorThreadCore.L4),
                "DPD", "PCP", "DPD",
                'D', getOreGem("Diamond"),
                'P', getOreDust("Redstone"),
                'C', new ItemStack(BlockECalculatorCasing.INSTANCE));
        GameRegistry.addShapedRecipe(new ItemStack(BlockECalculatorThreadCore.L6),
                "DPD", "PCP", "DPD",
                'D', getOreGem("Diamond"),
                'P', getOreDust("Redstone"),
                'C', new ItemStack(BlockECalculatorThreadCore.L4));
        GameRegistry.addShapedRecipe(new ItemStack(BlockECalculatorThreadCore.L9),
                "DPD", "PCP", "DPD",
                'D', getOreGem("Emerald"),
                'P', getOreDust("Redstone"),
                'C', new ItemStack(BlockECalculatorThreadCore.L6));
        GameRegistry.addShapedRecipe(new ItemStack(BlockECalculatorParallelProc.L4),
                "DCD", "CPC", "DCD",
                'D', getOreGem("Diamond"),
                'C', new ItemStack(BlockECalculatorCasing.INSTANCE),
                'P', getOreDust("Redstone"));
        GameRegistry.addShapedRecipe(new ItemStack(BlockECalculatorParallelProc.L6),
                "DCD", "CPC", "DCD",
                'D', getOreGem("Diamond"),
                'C', getOreGem("Diamond"),
                'P', new ItemStack(BlockECalculatorParallelProc.L4));
        GameRegistry.addShapedRecipe(new ItemStack(BlockECalculatorParallelProc.L9),
                "DCD", "CPC", "DCD",
                'D', getOreGem("Emerald"),
                'C', getOreGem("Diamond"),
                'P', new ItemStack(BlockECalculatorParallelProc.L6));

        // --- Storage Cell recipes ---
        GameRegistry.addShapedRecipe(new ItemStack(EStorageCellItem.LEVEL_A),
                " Q ", "RSR", " Q ",
                'Q', getOreGem("CertusQuartz"),
                'R', getOreDust("Redstone"),
                'S', getOreIngot("Iron"));
        GameRegistry.addShapedRecipe(new ItemStack(EStorageCellItem.LEVEL_B),
                " Q ", "RSR", " Q ",
                'Q', getOreGem("CertusQuartz"),
                'R', getOreDust("Redstone"),
                'S', new ItemStack(EStorageCellItem.LEVEL_A));
        GameRegistry.addShapedRecipe(new ItemStack(EStorageCellItem.LEVEL_C),
                " Q ", "RSR", " Q ",
                'Q', getOreGem("CertusQuartz"),
                'R', getOreDust("Redstone"),
                'S', new ItemStack(EStorageCellItem.LEVEL_B));

        GameRegistry.addShapedRecipe(new ItemStack(EStorageCellFluid.LEVEL_A),
                " Q ", "RSR", " Q ",
                'Q', getOreGem("CertusQuartz"),
                'R', getOreDust("Redstone"),
                'S', new ItemStack(Items.bucket));
        GameRegistry.addShapedRecipe(new ItemStack(EStorageCellFluid.LEVEL_B),
                " Q ", "RSR", " Q ",
                'Q', getOreGem("CertusQuartz"),
                'R', getOreDust("Redstone"),
                'S', new ItemStack(EStorageCellFluid.LEVEL_A));
        GameRegistry.addShapedRecipe(new ItemStack(EStorageCellFluid.LEVEL_C),
                " Q ", "RSR", " Q ",
                'Q', getOreGem("CertusQuartz"),
                'R', getOreDust("Redstone"),
                'S', new ItemStack(EStorageCellFluid.LEVEL_B));

        // --- ECalculator Cell recipes ---
        GameRegistry.addShapedRecipe(new ItemStack(ECalculatorCell.L4),
                " Q ", "RDR", " Q ",
                'Q', getOreGem("CertusQuartz"),
                'R', getOreDust("Redstone"),
                'D', getOreGem("Diamond"));
        GameRegistry.addShapedRecipe(new ItemStack(ECalculatorCell.L6),
                " Q ", "RDR", " Q ",
                'Q', getOreGem("CertusQuartz"),
                'R', getOreDust("Redstone"),
                'D', new ItemStack(ECalculatorCell.L4));
        GameRegistry.addShapedRecipe(new ItemStack(ECalculatorCell.L9),
                " Q ", "RDR", " Q ",
                'Q', getOreGem("CertusQuartz"),
                'R', getOreDust("Redstone"),
                'D', new ItemStack(ECalculatorCell.L6));

        // Hyper thread core
        GameRegistry.addShapedRecipe(new ItemStack(BlockECalculatorThreadCoreHyper.L4),
                "DED", "ECE", "DED",
                'D', getOreGem("Diamond"),
                'E', getOreGem("EnderPearl"),
                'C', new ItemStack(BlockECalculatorThreadCore.L4));
        GameRegistry.addShapedRecipe(new ItemStack(BlockECalculatorThreadCoreHyper.L6),
                "DED", "ECE", "DED",
                'D', getOreGem("Diamond"),
                'E', getOreGem("EnderPearl"),
                'C', new ItemStack(BlockECalculatorThreadCoreHyper.L4));
        GameRegistry.addShapedRecipe(new ItemStack(BlockECalculatorThreadCoreHyper.L9),
                "DED", "ECE", "DED",
                'D', getOreGem("Emerald"),
                'E', getOreGem("EnderPearl"),
                'C', new ItemStack(BlockECalculatorThreadCoreHyper.L6));
    }

    /**
     * Get an ore dictionary ingot, falling back to vanilla if not found.
     * In GTNH, this will return GT materials.
     */
    private static ItemStack getOreIngot(String material) {
        return getOreFirst("ingot" + material, getVanillaIngot(material));
    }

    /**
     * Get an ore dictionary gem, falling back to vanilla if not found.
     * In GTNH, this will return GT materials.
     */
    private static ItemStack getOreGem(String material) {
        return getOreFirst("gem" + material, getVanillaGem(material));
    }

    /**
     * Get an ore dictionary dust, falling back to vanilla redstone if not found.
     * In GTNH, this will return GT materials.
     */
    private static ItemStack getOreDust(String material) {
        return getOreFirst("dust" + material, getVanillaDust(material));
    }

    private static ItemStack getOreFirst(String oreName, ItemStack fallback) {
        java.util.List<ItemStack> ores = OreDictionary.getOres(oreName);
        if (ores != null && !ores.isEmpty()) {
            return ores.get(0).copy();
        }
        return fallback;
    }

    private static ItemStack getVanillaIngot(String material) {
        switch (material) {
            case "Iron": return new ItemStack(Items.iron_ingot);
            case "Gold": return new ItemStack(Items.gold_ingot);
            default: return new ItemStack(Items.iron_ingot);
        }
    }

    private static ItemStack getVanillaGem(String material) {
        switch (material) {
            case "Diamond": return new ItemStack(Items.diamond);
            case "Emerald": return new ItemStack(Items.emerald);
            case "EnderPearl": return new ItemStack(Items.ender_pearl);
            case "CertusQuartz": return new ItemStack(Items.quartz);
            default: return new ItemStack(Items.diamond);
        }
    }

    private static ItemStack getVanillaDust(String material) {
        // GTNH uses dustRedstone, vanilla doesn't have dust redstone
        // Return redstone for compatibility
        if ("Redstone".equals(material)) {
            return new ItemStack(Items.redstone);
        }
        return new ItemStack(Items.redstone);
    }
}

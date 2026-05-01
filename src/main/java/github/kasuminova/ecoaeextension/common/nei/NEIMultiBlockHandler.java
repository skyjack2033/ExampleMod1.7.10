package github.kasuminova.ecoaeextension.common.nei;

import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import github.kasuminova.ecoaeextension.common.block.ecotech.ecalculator.BlockECalculatorController;
import github.kasuminova.ecoaeextension.common.block.ecotech.estorage.BlockEStorageController;
import github.kasuminova.ecoaeextension.common.block.ecotech.efabricator.BlockEFabricatorController;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * NEI Multi-block structure preview handler for GTNH 1.7.10.
 * Shows multi-block structure layout when looking up controller blocks in NEI.
 */
public class NEIMultiBlockHandler extends TemplateRecipeHandler {

    public static void register() {
        codechicken.nei.api.API.registerRecipeHandler(new NEIMultiBlockHandler());
        codechicken.nei.api.API.registerUsageHandler(new NEIMultiBlockHandler());
    }

    @Override
    public String getRecipeName() {
        return "NovaEng Multi-block";
    }

    @Override
    public String getGuiTexture() {
        return "ecoaeextension:textures/gui/nei/structure_preview.png";
    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results) {
        // Load all controller blocks as recipes
        if ("item".equals(outputId)) {
            return;
        }
        // Add recipes for all machine controllers
        addControllerRecipes();
    }

    @Override
    public void loadCraftingRecipes(ItemStack result) {
        addControllerRecipes();
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient) {
        addControllerRecipes();
    }

    private void addControllerRecipes() {
        // ECalculator controllers
        if (BlockECalculatorController.L4 != null) {
            arecipes.add(new CachedMultiBlockRecipe(new ItemStack(BlockECalculatorController.L4), MachineType.ECALCULATOR, 4));
        }
        if (BlockECalculatorController.L6 != null) {
            arecipes.add(new CachedMultiBlockRecipe(new ItemStack(BlockECalculatorController.L6), MachineType.ECALCULATOR, 6));
        }
        if (BlockECalculatorController.L9 != null) {
            arecipes.add(new CachedMultiBlockRecipe(new ItemStack(BlockECalculatorController.L9), MachineType.ECALCULATOR, 9));
        }

        // EStorage controllers
        if (BlockEStorageController.L4 != null) {
            arecipes.add(new CachedMultiBlockRecipe(new ItemStack(BlockEStorageController.L4), MachineType.ESTORAGE, 4));
        }
        if (BlockEStorageController.L6 != null) {
            arecipes.add(new CachedMultiBlockRecipe(new ItemStack(BlockEStorageController.L6), MachineType.ESTORAGE, 6));
        }
        if (BlockEStorageController.L9 != null) {
            arecipes.add(new CachedMultiBlockRecipe(new ItemStack(BlockEStorageController.L9), MachineType.ESTORAGE, 9));
        }

        // EFabricator controllers
        if (BlockEFabricatorController.L4 != null) {
            arecipes.add(new CachedMultiBlockRecipe(new ItemStack(BlockEFabricatorController.L4), MachineType.EFABRICATOR, 4));
        }
        if (BlockEFabricatorController.L6 != null) {
            arecipes.add(new CachedMultiBlockRecipe(new ItemStack(BlockEFabricatorController.L6), MachineType.EFABRICATOR, 6));
        }
        if (BlockEFabricatorController.L9 != null) {
            arecipes.add(new CachedMultiBlockRecipe(new ItemStack(BlockEFabricatorController.L9), MachineType.EFABRICATOR, 9));
        }
    }

    @Override
    public void drawExtras(int recipe) {
        if (recipe >= 0 && recipe < arecipes.size()) {
            CachedMultiBlockRecipe cachedRecipe = (CachedMultiBlockRecipe) arecipes.get(recipe);
            if (cachedRecipe != null) {
                cachedRecipe.drawStructure();
            }
        }
    }

    public enum MachineType {
        ECALCULATOR,
        ESTORAGE,
        EFABRICATOR
    }

    public class CachedMultiBlockRecipe extends CachedRecipe {

        final ItemStack controllerStack;
        final MachineType machineType;
        final int tier;

        public CachedMultiBlockRecipe(ItemStack controller, MachineType type, int tier) {
            this.controllerStack = controller.copy();
            this.controllerStack.stackSize = 1;
            this.machineType = type;
            this.tier = tier;
        }

        @Override
        public PositionedStack getResult() {
            return new PositionedStack(controllerStack, 120, 30);
        }

        @Override
        public List<PositionedStack> getIngredients() {
            return new ArrayList<>();
        }

        public void drawStructure() {
            FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
            int baseX = 6;
            int baseY = 24;

            // Draw title
            String title = getMachineName();
            fr.drawString(title, baseX, baseY - 12, 0xFF404040);

            // Draw tier info
            fr.drawString("Tier L" + tier, baseX, baseY + getStructureHeight() + 4, 0xFF808080);

            // Draw structure blocks
            drawStructureBlocks(baseX, baseY);
        }

        private String getMachineName() {
            switch (machineType) {
                case ECALCULATOR:
                    return "ECPU Calculator";
                case ESTORAGE:
                    return "Extended Digital Storage";
                case EFABRICATOR:
                    return "Molecular Assembler";
                default:
                    return "Unknown Machine";
            }
        }

        private int getStructureHeight() {
            return 20 + tier * 3;
        }

        private void drawStructureBlocks(int baseX, int baseY) {
            switch (machineType) {
                case ECALCULATOR:
                    drawECalculatorStructure(baseX, baseY);
                    break;
                case ESTORAGE:
                    drawEStorageStructure(baseX, baseY);
                    break;
                case EFABRICATOR:
                    drawEFabricatorStructure(baseX, baseY);
                    break;
            }
        }

        private void drawECalculatorStructure(int baseX, int baseY) {
            // Draw controller
            drawBlockPlaceholder(baseX + 40, baseY, 0xFFFF00); // Yellow for controller

            // Draw channel
            drawBlockPlaceholder(baseX + 40, baseY + 12, 0xFF00FF); // Magenta for ME Channel

            // Draw threads based on tier
            int threadCount = tier;
            for (int i = 0; i < threadCount; i++) {
                drawBlockPlaceholder(baseX + 40 + (i % 2) * 12, baseY + 24 + (i / 2) * 12, 0xFF00FF00); // Green for threads
            }

            // Draw tail
            drawBlockPlaceholder(baseX + 40 + tier, baseY + 24, 0xFF808080); // Gray for tail
        }

        private void drawEStorageStructure(int baseX, int baseY) {
            // Draw controller
            drawBlockPlaceholder(baseX + 40, baseY, 0xFFFF00); // Yellow for controller

            // Draw channel
            drawBlockPlaceholder(baseX + 40, baseY + 12, 0xFF00FF); // Magenta for ME Channel

            // Draw storage cells
            int cellCount = tier * 2;
            for (int i = 0; i < cellCount; i++) {
                drawBlockPlaceholder(baseX + 40 + (i % 2) * 12, baseY + 24 + (i / 2) * 12, 0xFF0000FF); // Blue for cells
            }
        }

        private void drawEFabricatorStructure(int baseX, int baseY) {
            // Draw controller
            drawBlockPlaceholder(baseX + 40, baseY, 0xFFFF00); // Yellow for controller

            // Draw channel
            drawBlockPlaceholder(baseX + 40, baseY + 12, 0xFF00FF); // Magenta for ME Channel

            // Draw pattern bus
            drawBlockPlaceholder(baseX + 28, baseY + 24, 0xFF00FFFF); // Cyan for pattern bus

            // Draw workers based on tier
            int workerCount = tier;
            for (int i = 0; i < workerCount; i++) {
                drawBlockPlaceholder(baseX + 40 + i * 12, baseY + 24, 0xFFFF00FF); // Purple for workers
            }
        }

        private void drawBlockPlaceholder(int x, int y, int color) {
            // Simple colored rectangle to represent a block
            // In a full implementation, this would draw the actual block texture
            Minecraft.getMinecraft().fontRenderer.drawString("■", x, y, color);
        }
    }
}

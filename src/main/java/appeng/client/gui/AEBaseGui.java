package appeng.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

public class AEBaseGui extends GuiContainer {
    public AEBaseGui(Container container) {
        super(container);
    }

    public void drawFG(final int offsetX, final int offsetY, final int mouseX, final int mouseY) {
    }

    public void drawBG(final int offsetX, final int offsetY, final int mouseX, final int mouseY) {
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        drawBG(mouseX, mouseY, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        drawFG(mouseX, mouseY, mouseX, mouseY);
    }
}

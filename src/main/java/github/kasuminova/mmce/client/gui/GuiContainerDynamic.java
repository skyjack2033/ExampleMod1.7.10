package github.kasuminova.mmce.client.gui;

import github.kasuminova.mmce.client.gui.widget.base.WidgetController;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

public class GuiContainerDynamic<T extends Container> extends GuiContainer {

    protected WidgetController widgetController = new WidgetController();

    public GuiContainerDynamic(T container) {
        super(container);
    }

    @SuppressWarnings("unchecked")
    public T getContainer() {
        return (T) inventorySlots;
    }

    public WidgetController getWidgetController() {
        return widgetController;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        // Stub
    }
}

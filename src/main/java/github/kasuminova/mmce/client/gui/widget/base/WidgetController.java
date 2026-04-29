package github.kasuminova.mmce.client.gui.widget.base;

import github.kasuminova.mmce.client.gui.util.RenderPos;
import github.kasuminova.mmce.client.gui.widget.event.GuiEvent;

import java.util.ArrayList;
import java.util.List;

public class WidgetController {

    private final List<DynamicWidget> containers = new ArrayList<>();
    private WidgetGui widgetGui;

    public WidgetController() {
    }

    public WidgetController(WidgetGui widgetGui) {
        this.widgetGui = widgetGui;
    }

    public void addWidget(DynamicWidget container) {
        containers.add(container);
    }

    public void postGuiEvent(GuiEvent event) {
        for (DynamicWidget container : containers) {
            container.onGuiEvent(event);
        }
    }

    public void render(RenderPos renderPos, WidgetGui widgetGui) {
        for (DynamicWidget container : containers) {
            if (container.isVisible()) {
                container.render(renderPos, widgetGui);
            }
        }
    }

    public List<DynamicWidget> getContainers() {
        return containers;
    }

}

package github.kasuminova.mmce.client.gui.widget.base;

import github.kasuminova.mmce.client.gui.util.RenderPos;
import github.kasuminova.mmce.client.gui.widget.container.WidgetContainer;

import java.util.ArrayList;
import java.util.List;

public class WidgetController {

    private final List<WidgetContainer> containers = new ArrayList<>();

    public void addWidget(WidgetContainer container) {
        containers.add(container);
    }

    public void render(RenderPos renderPos, WidgetGui widgetGui) {
        for (WidgetContainer container : containers) {
            if (container.isVisible()) {
                container.render(renderPos, widgetGui);
            }
        }
    }

    public List<WidgetContainer> getContainers() {
        return containers;
    }

}

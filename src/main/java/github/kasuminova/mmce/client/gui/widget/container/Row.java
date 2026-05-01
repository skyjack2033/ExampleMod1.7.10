package github.kasuminova.mmce.client.gui.widget.container;

import github.kasuminova.mmce.client.gui.util.RenderPos;
import github.kasuminova.mmce.client.gui.widget.base.DynamicWidget;
import github.kasuminova.mmce.client.gui.widget.base.WidgetGui;

public class Row extends WidgetContainer {

    @Override
    public void render(RenderPos renderPos, WidgetGui widgetGui) {
        if (!isVisible() || widgets.isEmpty()) {
            return;
        }

        int currentX = 0;

        for (DynamicWidget widget : widgets) {
            if (!widget.isVisible()) {
                continue;
            }

            // Position child widget relative to this container
            widget.setAbsX(currentX);
            widget.setAbsY(0);

            // Render with adjusted position
            RenderPos childPos = new RenderPos(renderPos.posX() + getAbsX() + currentX, renderPos.posY() + getAbsY());
            widget.render(childPos, widgetGui);

            // Move to next position (with spacing consideration via widget margins)
            currentX += widget.getWidth();
        }
    }

}

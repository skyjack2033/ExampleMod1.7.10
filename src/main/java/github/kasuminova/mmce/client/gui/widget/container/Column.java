package github.kasuminova.mmce.client.gui.widget.container;

import github.kasuminova.mmce.client.gui.util.RenderPos;
import github.kasuminova.mmce.client.gui.widget.base.DynamicWidget;
import github.kasuminova.mmce.client.gui.widget.base.WidgetGui;

public class Column extends WidgetContainer {

    @Override
    public void render(RenderPos renderPos, WidgetGui widgetGui) {
        if (!isVisible() || widgets.isEmpty()) {
            return;
        }

        int currentY = 0;

        for (DynamicWidget widget : widgets) {
            if (!widget.isVisible()) {
                continue;
            }

            // Position child widget relative to this container
            widget.setAbsX(0);
            widget.setAbsY(currentY);

            // Render with adjusted position
            RenderPos childPos = new RenderPos(renderPos.posX() + getAbsX(), renderPos.posY() + getAbsY() + currentY);
            widget.render(childPos, widgetGui);

            // Move to next position (with spacing consideration via widget margins)
            currentY += widget.getHeight();
        }
    }

}

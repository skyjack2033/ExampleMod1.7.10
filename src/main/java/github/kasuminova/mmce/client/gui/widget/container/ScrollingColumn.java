package github.kasuminova.mmce.client.gui.widget.container;

import github.kasuminova.mmce.client.gui.util.MousePos;
import github.kasuminova.mmce.client.gui.util.RenderFunction;
import github.kasuminova.mmce.client.gui.util.RenderPos;
import github.kasuminova.mmce.client.gui.util.RenderSize;
import github.kasuminova.mmce.client.gui.widget.base.DynamicWidget;
import github.kasuminova.mmce.client.gui.widget.base.WidgetGui;

public class ScrollingColumn extends WidgetContainer {

    private int scrollY;
    protected Scrollbar scrollbar = new Scrollbar();

    public void scroll(int amount) {
        this.scrollY += amount;
    }

    public int getScrollY() {
        return scrollY;
    }

    public void setScrollY(int scrollY) {
        this.scrollY = scrollY;
    }

    public Scrollbar getScrollbar() {
        return scrollbar;
    }

    // Stub: push scissor region
    public void pushScissor(WidgetGui gui, RenderSize size, RenderPos pos, int width, int height) {
    }

    // Stub: pop scissor region
    public void popScissor(RenderSize size) {
    }

    // Stub: get widget render offset
    public RenderPos getWidgetRenderOffset(DynamicWidget widget, int width, int y) {
        return new RenderPos(0, y);
    }

    protected void doRender(WidgetGui gui, RenderSize renderSize, RenderPos renderPos, MousePos mousePos, RenderFunction renderFunction) {
        for (DynamicWidget widget : widgets) {
            if (widget.isVisible()) {
                renderFunction.doRender(widget, gui, renderSize, renderPos, mousePos);
            }
        }
    }

}

package github.kasuminova.mmce.client.gui.widget.base;

import github.kasuminova.mmce.client.gui.util.RenderPos;
import github.kasuminova.mmce.client.gui.widget.event.GuiEvent;

public abstract class DynamicWidget {

    private int width;
    private int height;
    private boolean visible = true;
    private int absX;
    private int absY;

    public abstract void render(RenderPos renderPos, WidgetGui widgetGui);

    public void onGuiEvent(GuiEvent event) {
    }

    public int getAbsX() {
        return absX;
    }

    public void setAbsX(int absX) {
        this.absX = absX;
    }

    public int getAbsY() {
        return absY;
    }

    public void setAbsY(int absY) {
        this.absY = absY;
    }

    public DynamicWidget setAbsXY(int x, int y) {
        this.absX = x;
        this.absY = y;
        return this;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

}

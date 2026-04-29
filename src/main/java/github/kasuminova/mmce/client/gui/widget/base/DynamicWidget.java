package github.kasuminova.mmce.client.gui.widget.base;

import github.kasuminova.mmce.client.gui.util.RenderPos;
import github.kasuminova.mmce.client.gui.widget.event.GuiEvent;

public abstract class DynamicWidget {

    protected int width;
    protected int height;
    private boolean visible = true;
    private int absX;
    private int absY;
    private boolean rightAligned = false;
    private boolean verticalCentering = false;

    public abstract void render(RenderPos renderPos, WidgetGui widgetGui);

    public boolean onGuiEvent(GuiEvent event) {
        return false;
    }

    public void update(WidgetGui gui) {
    }

    public int getAbsX() {
        return absX;
    }

    public DynamicWidget setAbsX(int absX) {
        this.absX = absX;
        return this;
    }

    public int getAbsY() {
        return absY;
    }

    public DynamicWidget setAbsY(int absY) {
        this.absY = absY;
        return this;
    }

    public DynamicWidget setAbsXY(int x, int y) {
        this.absX = x;
        this.absY = y;
        return this;
    }

    public int getWidth() {
        return width;
    }

    public DynamicWidget setWidth(int width) {
        this.width = width;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public DynamicWidget setHeight(int height) {
        this.height = height;
        return this;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isRightAligned() {
        return rightAligned;
    }

    public DynamicWidget setRightAligned(boolean rightAligned) {
        this.rightAligned = rightAligned;
        return this;
    }

    public boolean isVerticalCentering() {
        return verticalCentering;
    }

    public DynamicWidget setVerticalCentering(boolean verticalCentering) {
        this.verticalCentering = verticalCentering;
        return this;
    }

}

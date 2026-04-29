package github.kasuminova.mmce.client.gui.widget;

import github.kasuminova.mmce.client.gui.util.RenderPos;
import github.kasuminova.mmce.client.gui.widget.base.DynamicWidget;
import github.kasuminova.mmce.client.gui.widget.base.WidgetGui;

import java.util.List;

public class MultiLineLabel extends DynamicWidget {

    private final List<String> lines;
    private boolean autoWrap;
    private float scale = 1.0F;
    private int marginTop;
    private int marginBottom;
    private int marginLeft;
    private int marginRight;

    public MultiLineLabel(List<String> lines) {
        this.lines = lines;
    }

    public void setAutoWrap(boolean autoWrap) {
        this.autoWrap = autoWrap;
    }

    public boolean isAutoWrap() {
        return autoWrap;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public float getScale() {
        return scale;
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
    }

    public void setMargin(int top, int bottom, int left, int right) {
        this.marginTop = top;
        this.marginBottom = bottom;
        this.marginLeft = left;
        this.marginRight = right;
    }

    @Override
    public void render(RenderPos renderPos, WidgetGui widgetGui) {
        // Stub
    }

}

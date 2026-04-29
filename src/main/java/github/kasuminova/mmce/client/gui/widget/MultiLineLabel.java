package github.kasuminova.mmce.client.gui.widget;

import github.kasuminova.mmce.client.gui.util.RenderPos;
import github.kasuminova.mmce.client.gui.widget.base.DynamicWidget;
import github.kasuminova.mmce.client.gui.widget.base.WidgetGui;

import java.util.List;

public class MultiLineLabel extends DynamicWidget {

    private final List<String> lines;
    private boolean autoWrap;
    private float scale = 1.0F;
    private boolean verticalCentering;
    private boolean rightAligned;
    private int marginTop;
    private int marginBottom;
    private int marginLeft;
    private int marginRight;

    public MultiLineLabel(List<String> lines) {
        this.lines = lines;
    }

    public MultiLineLabel setAutoWrap(boolean autoWrap) {
        this.autoWrap = autoWrap;
        return this;
    }

    public MultiLineLabel setAutoRecalculateSize(boolean autoRecalculateSize) {
        return this;
    }

    public boolean isAutoWrap() {
        return autoWrap;
    }

    public MultiLineLabel setScale(float scale) {
        this.scale = scale;
        return this;
    }

    public float getScale() {
        return scale;
    }

    public MultiLineLabel setVerticalCentering(boolean verticalCentering) {
        this.verticalCentering = verticalCentering;
        return this;
    }

    public boolean isVerticalCentering() {
        return verticalCentering;
    }

    public MultiLineLabel setRightAligned(boolean rightAligned) {
        this.rightAligned = rightAligned;
        return this;
    }

    public boolean isRightAligned() {
        return rightAligned;
    }

    @Override
    public MultiLineLabel setWidth(int width) {
        super.setWidth(width);
        return this;
    }

    @Override
    public MultiLineLabel setHeight(int height) {
        super.setHeight(height);
        return this;
    }

    @Override
    public MultiLineLabel setMargin(int margin) {
        this.marginTop = margin;
        this.marginBottom = margin;
        this.marginLeft = margin;
        this.marginRight = margin;
        return this;
    }

    public MultiLineLabel setContents(List<String> contents) {
        // Stub
        return this;
    }

    public MultiLineLabel setMargin(int top, int bottom, int left, int right) {
        this.marginTop = top;
        this.marginBottom = bottom;
        this.marginLeft = left;
        this.marginRight = right;
        return this;
    }

    @Override
    public void render(RenderPos renderPos, WidgetGui widgetGui) {
        // Stub
    }

}

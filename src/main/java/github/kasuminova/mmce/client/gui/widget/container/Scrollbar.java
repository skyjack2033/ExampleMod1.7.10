package github.kasuminova.mmce.client.gui.widget.container;

import github.kasuminova.mmce.client.gui.widget.Button;
import github.kasuminova.mmce.client.gui.widget.base.DynamicWidget;

public class Scrollbar extends DynamicWidget {

    private final Button scroll = new Button();
    private int scrollUnit = 1;
    private boolean disabled = false;
    private boolean mouseWheelCheckPos = true;
    private int currentScroll = 0;

    public Scrollbar setScrollUnit(int scrollUnit) {
        this.scrollUnit = scrollUnit;
        return this;
    }

    public int getRange() {
        return 0;
    }

    public Button getScroll() {
        return scroll;
    }

    public Scrollbar setDisabled(boolean disabled) {
        this.disabled = disabled;
        return this;
    }

    @Override
    public boolean isDisabled() {
        return disabled;
    }

    public Scrollbar setMouseWheelCheckPos(boolean mouseWheelCheckPos) {
        this.mouseWheelCheckPos = mouseWheelCheckPos;
        return this;
    }

    public int getCurrentScroll() {
        return currentScroll;
    }

    public void setCurrentScroll(int currentScroll) {
        this.currentScroll = currentScroll;
    }

    public boolean isMouseWheelCheckPos() {
        return mouseWheelCheckPos;
    }

}

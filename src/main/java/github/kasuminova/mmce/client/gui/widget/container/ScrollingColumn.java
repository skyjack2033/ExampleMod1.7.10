package github.kasuminova.mmce.client.gui.widget.container;

public class ScrollingColumn extends WidgetContainer {

    private int scrollY;

    public void scroll(int amount) {
        this.scrollY += amount;
    }

    public int getScrollY() {
        return scrollY;
    }

    public void setScrollY(int scrollY) {
        this.scrollY = scrollY;
    }

}

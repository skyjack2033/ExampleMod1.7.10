package github.kasuminova.mmce.client.gui.widget;

import github.kasuminova.mmce.client.gui.util.RenderPos;
import github.kasuminova.mmce.client.gui.widget.base.DynamicWidget;
import github.kasuminova.mmce.client.gui.widget.base.WidgetGui;

public class Button extends DynamicWidget {

    private Runnable onClickListener;

    public void setOnClickListener(Runnable onClickListener) {
        this.onClickListener = onClickListener;
    }

    public Runnable getOnClickListener() {
        return onClickListener;
    }

    @Override
    public void render(RenderPos renderPos, WidgetGui widgetGui) {
        // Stub
    }

}

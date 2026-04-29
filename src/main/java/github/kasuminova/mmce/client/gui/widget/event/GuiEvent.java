package github.kasuminova.mmce.client.gui.widget.event;

public class GuiEvent {

    private final String eventType;

    public GuiEvent(String eventType) {
        this.eventType = eventType;
    }

    public String getEventType() {
        return eventType;
    }

}

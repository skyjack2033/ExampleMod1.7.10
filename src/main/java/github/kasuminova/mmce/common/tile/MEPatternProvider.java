package github.kasuminova.mmce.common.tile;

import github.kasuminova.mmce.common.tile.base.MEMachineComponent;

public class MEPatternProvider extends MEMachineComponent {

    public enum WorkModeSetting {
        DEFAULT,
        ENHANCED_BLOCKING_MODE,
        IGNORE
    }

    public int getChannel() {
        return 0;
    }

    public Object getPatterns() {
        return null;
    }

    public Object getCore() {
        return null;
    }

    public Object getProxy() {
        return null;
    }

    public Object findConnectedProxy() {
        return null;
    }

    public WorkModeSetting getWorkMode() {
        return WorkModeSetting.DEFAULT;
    }
}

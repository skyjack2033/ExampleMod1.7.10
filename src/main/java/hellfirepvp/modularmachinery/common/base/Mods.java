package hellfirepvp.modularmachinery.common.base;

public enum Mods {
    TOP(false),
    AE2(false),
    MEKENG(false),
    CRAFTTWEAKER(false),
    MBD(false),
    COMPONENT_MODEL_HIDER(false);

    private final boolean present;

    Mods(boolean present) {
        this.present = present;
    }

    public boolean isPresent() {
        return present;
    }
}

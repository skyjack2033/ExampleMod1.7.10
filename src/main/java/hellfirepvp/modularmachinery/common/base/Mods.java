package hellfirepvp.modularmachinery.common.base;

import cpw.mods.fml.common.Loader;

/**
 * Stub for mod availability checks in GTNH 1.7.10.
 * Replaces MMCE's Mods enum.
 */
public enum Mods {
    TOP(false),
    AE2(false),
    MEKENG(false),
    CRAFTTWEAKER(false),
    MBD(false),
    COMPONENT_MODEL_HIDER(false);

    private final boolean present;

    Mods(boolean defaultPresent) {
        // Check if mod is loaded based on known mod IDs
        this.present = checkModPresent(this.name());
    }

    private static boolean checkModPresent(String modName) {
        // Map enum names to actual mod IDs
        switch (modName) {
            case "AE2":
                return Loader.isModLoaded("appliedenergistics2");
            case "MEKENG":
                return Loader.isModLoaded("mekeng");
            case "TOP":
                return Loader.isModLoaded("theoneprobe");
            case "CRAFTTWEAKER":
                return Loader.isModLoaded("crafttweaker");
            case "MBD":
                return Loader.isModLoaded("mbd");
            default:
                return false;
        }
    }

    public boolean isPresent() {
        return present;
    }
}

package github.kasuminova.ecoaeextension.common.integration.theoneprobe;

import cpw.mods.fml.common.Optional;

/**
 * The One Probe integration for 1.7.10.
 * TOP API does not exist for 1.7.10, so this is a no-op stub.
 */
public class IntegrationTOP {

    @Optional.Method(modid = "theoneprobe")
    public static void registerProvider() {
        // No-op: TOP is not available on 1.7.10
    }

}

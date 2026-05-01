package github.kasuminova.ecoaeextension.mixin.ae2;

import appeng.me.cluster.implementations.CraftingCPUCluster;
import github.kasuminova.ecoaeextension.common.ecalculator.ECPUCluster;
import github.kasuminova.ecoaeextension.common.tile.ecotech.ecalculator.ECalculatorController;
import org.spongepowered.asm.mixin.Mixin;

/**
 * Mixin for CraftingCPUCluster to support ECalculator vCPU integration.
 * This mixin provides helper methods to check if a CPU is owned by an ECalculator.
 */
@Mixin(value = CraftingCPUCluster.class, remap = false)
public abstract class MixinCraftingCPUCluster {

    /**
     * Check if this cluster is owned by an ECalculator.
     */
    public ECalculatorController getECalculatorOwner() {
        ECPUCluster ecpu = ECPUCluster.from((CraftingCPUCluster) (Object) this);
        if (ecpu != null) {
            return ecpu.novaeng_ec$getVirtualCPUOwner();
        }
        return null;
    }

    /**
     * Check if this is a virtual CPU created by ECalculator.
     */
    public boolean isVirtualCPU() {
        return getECalculatorOwner() != null;
    }
}

package github.kasuminova.ecoaeextension.mixin.mmce;

import github.kasuminova.ecoaeextension.ECOAEExtension;
import github.kasuminova.ecoaeextension.common.block.ecotech.ecalculator.BlockECalculatorController;
import github.kasuminova.ecoaeextension.common.block.ecotech.efabricator.BlockEFabricatorController;
import github.kasuminova.ecoaeextension.common.block.ecotech.estorage.BlockEStorageController;
import hellfirepvp.modularmachinery.common.block.BlockController;
import hellfirepvp.modularmachinery.common.machine.DynamicMachine;
import hellfirepvp.modularmachinery.common.machine.MachineRegistry;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;


@Mixin(MachineRegistry.class)
public class MixinMachineRegistry {

    @Redirect(
            method = "getWaitForLoadMachines",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/List;add(Ljava/lang/Object;)Z",
                    remap = false
            ),
            remap = false
    )
    @SuppressWarnings("SameReturnValue")
    private static boolean filterSpecialMachine(final List<Object> instance, final Object e) {
        DynamicMachine machine = (DynamicMachine) e;
        // In 1.7.10 MMCE, DynamicMachine uses getMachineName() returning String, not ResourceLocation
        String machineName = machine.getMachineName();
        if (machineName == null) {
            instance.add(e);
            return true;
        }
        if (BlockEStorageController.REGISTRY.containsKey(machineName)) {
            // BlockController.MACHINE_CONTROLLERS not available in 1.7.10 MMCE - skipped
            return true;
        }
        if (BlockEFabricatorController.REGISTRY.containsKey(machineName)) {
            return true;
        }
        if (BlockECalculatorController.REGISTRY.containsKey(machineName)) {
            return true;
        }
        instance.add(e);
        return true;
    }

}

package hellfirepvp.modularmachinery.common.machine;

import net.minecraft.util.ResourceLocation;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MachineRegistry {

    private static final MachineRegistry INSTANCE = new MachineRegistry();

    private final Map<String, DynamicMachine> loadedMachines = new HashMap<String, DynamicMachine>();

    private MachineRegistry() {
    }

    public static MachineRegistry getInstance() {
        return INSTANCE;
    }

    public static MachineRegistry getRegistry() {
        return INSTANCE;
    }

    public Optional<DynamicMachine> getMachine(String name) {
        return Optional.ofNullable(loadedMachines.get(name));
    }

    public DynamicMachine getMachine(ResourceLocation name) {
        return loadedMachines.get(name == null ? null : name.getResourcePath());
    }

    public Collection<DynamicMachine> getLoadedMachines() {
        return Collections.unmodifiableCollection(loadedMachines.values());
    }

    public void registerMachine(DynamicMachine machine) {
        if (machine != null && machine.getMachineName() != null) {
            loadedMachines.put(machine.getMachineName(), machine);
        }
    }
}

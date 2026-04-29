package hellfirepvp.modularmachinery;

import github.kasuminova.mmce.common.concurrent.TaskExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModularMachinery {

    public static final Logger log = LogManager.getLogger("ModularMachinery");
    public static final String MODID = "modularmachinery";
    public static final TaskExecutor EXECUTE_MANAGER = TaskExecutor.getInstance();

    private ModularMachinery() {}
}

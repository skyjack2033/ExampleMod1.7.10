package appeng.core;

import appeng.core.api.ApiPart;
import appeng.core.api.Registries;
import appeng.core.api.definitions.ApiDefinition;

public class Api {

    private static final Api INSTANCE = new Api();

    public static Api instance() {
        return INSTANCE;
    }

    public ApiPart partHelper() {
        return null;
    }

    public ApiDefinition definitions() {
        return null;
    }

    public Registries registries() {
        return null;
    }
}

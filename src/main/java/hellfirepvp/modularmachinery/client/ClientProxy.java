package hellfirepvp.modularmachinery.client;

import hellfirepvp.modularmachinery.common.CommonProxy;

public class ClientProxy extends CommonProxy {

    @Override
    public boolean isClient() {
        return true;
    }

    public void registerRenderers() {
    }
}

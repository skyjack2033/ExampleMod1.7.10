package hellfirepvp.modularmachinery.client;

import hellfirepvp.modularmachinery.common.CommonProxy;

public class ClientProxy extends CommonProxy {

    public static final ClientScheduler clientScheduler = new ClientScheduler();

    @Override
    public boolean isClient() {
        return true;
    }

    public void registerRenderers() {
    }

    public static class ClientScheduler {
        public void addRunnable(Runnable runnable, int delay) {
            runnable.run();
        }
    }
}

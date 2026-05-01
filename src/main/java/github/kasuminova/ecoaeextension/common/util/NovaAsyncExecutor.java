package github.kasuminova.ecoaeextension.common.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Simple async executor for offloading heavy tick computations.
 * Replacement for MMCE's TaskExecutor.
 */
public class NovaAsyncExecutor {

    private static final NovaAsyncExecutor INSTANCE = new NovaAsyncExecutor();
    private final ExecutorService executor = Executors.newCachedThreadPool(r -> {
        Thread t = new Thread(r, "NovaEng-AsyncTick");
        t.setDaemon(true);
        return t;
    });

    private NovaAsyncExecutor() {}

    public static NovaAsyncExecutor getInstance() {
        return INSTANCE;
    }

    public void submit(Runnable task) {
        executor.submit(task);
    }

    public Future<?> submitTask(Runnable task) {
        return executor.submit(task);
    }
}

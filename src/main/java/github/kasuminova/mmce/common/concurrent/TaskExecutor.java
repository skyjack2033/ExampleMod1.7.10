package github.kasuminova.mmce.common.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskExecutor {

    private static final TaskExecutor INSTANCE = new TaskExecutor();

    private final ExecutorService executor = Executors.newCachedThreadPool();

    private TaskExecutor() {
    }

    public static TaskExecutor getInstance() {
        return INSTANCE;
    }

    public void submit(Runnable task) {
        executor.submit(task);
    }

    public void addSyncTask(Runnable task) {
        task.run();
    }

    public Object addTask(Runnable task, long avgTime) {
        executor.submit(task);
        return null;
    }

}

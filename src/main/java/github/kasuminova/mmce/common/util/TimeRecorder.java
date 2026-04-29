package github.kasuminova.mmce.common.util;

public class TimeRecorder {

    private long startTime;
    private long elapsed;
    private int count;
    private boolean running;

    public void start() {
        this.startTime = System.nanoTime();
        this.running = true;
    }

    public void stop() {
        if (running) {
            this.elapsed += System.nanoTime() - startTime;
            this.count++;
            this.running = false;
        }
    }

    public long getElapsed() {
        if (running) {
            return elapsed + (System.nanoTime() - startTime);
        }
        return elapsed;
    }

    public long usedTimeAvg() {
        return count == 0 ? 0 : elapsed / count;
    }

    public void reset() {
        this.elapsed = 0;
        this.count = 0;
        this.running = false;
    }

}

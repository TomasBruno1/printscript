package CLI;

public class Timer {
    private long startTime;
    private long endTime;

    public void stop() {
        endTime = System.currentTimeMillis();
    }

    public long getElapsedTime() {
        return endTime - startTime;
    }

    public void start() {
        startTime = System.currentTimeMillis();
    }

}

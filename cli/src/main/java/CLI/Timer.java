package CLI;

public class Timer {
    private long startTime;
    private long endTime;

    public Timer() {
        startTime = System.currentTimeMillis();
    }

    public void stop() {
        endTime = System.currentTimeMillis();
    }

    public long getElapsedTime() {
        return endTime - startTime;
    }

    public void reset() {
        startTime = System.currentTimeMillis();
    }

}

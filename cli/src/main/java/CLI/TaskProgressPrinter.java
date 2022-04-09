package CLI;

public class TaskProgressPrinter {

    public static void printStart(Task task) {
        System.out.println("Started " + task.name() + "...");
    }

    public static void printEnd(Task task, Timer timer) {
        System.out.println(task.name() + " done (" + timer.getElapsedTime() + "ms)" + "\n");
    }
}

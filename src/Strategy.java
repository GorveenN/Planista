import java.util.Arrays;
import java.util.LinkedList;

public abstract class Strategy {

    protected LinkedList<Task> tasks;
    protected double executionTime;
    protected double waitTime;
    protected int tasksNumber;


    Strategy(Task[] t) {
        executionTime = 0;
        waitTime = 0;
        this.tasksNumber = t.length;
        this.tasks = new LinkedList<>();

        for (int i = 0; i < t.length; i++) {
            tasks.add(new Task(t[i]));
        }
    }
    protected abstract void runTasks();

    public void runTasksAndPrint() {
        printHeader();
        runTasks();
        printTimes();
    }

    protected void printHeader() {
        System.out.println("Strategia: " + this.getClass().getSimpleName());
    }

    protected void printTimes() {
        System.out.println();
        System.out.println("Średni czas obrotu: " + String.format("%.2f", (double)Math.round(this.executionTime * 100) / 100));
        System.out.println("Średni czas oczekiwania: " + String.format("%.2f", (double)Math.round(this.waitTime * 100) / 100));
        System.out.println();
    }

    protected void updateTimes(Task t) {
        executionTime += t.getFinishTime() - t.getAppearanceTime();
        waitTime += t.getFinishTime() - t.getAppearanceTime() - t.getDemand();
    }

    protected void finishTimes() {
        executionTime /= this.tasksNumber;
        waitTime /= this.tasksNumber;
    }
}

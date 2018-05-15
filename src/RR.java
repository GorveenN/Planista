import java.util.LinkedList;
import java.util.PriorityQueue;

public class RR extends Strategy {
    private int quantum;
    
    
    RR(Task[] t, int quantum) {
        super(t);
        this.quantum = quantum;
    }

    @Override
    protected void printHeader() {
        System.out.println("Strategia: " + this.getClass().getSimpleName() + "-" + Integer.toString(quantum));
    }

    @Override
    protected void runTasks() {
        int currentTime = 0;
        LinkedList<Task> tasksQueue = new LinkedList<>();
        Task currentTask = null;
        
        while (!tasksQueue.isEmpty() || !tasks.isEmpty() || currentTask != null) {
            
            if (tasksQueue.isEmpty() && !tasks.isEmpty() && currentTask == null) {
                currentTime = currentTime < tasks.element().getAppearanceTime() ? tasks.element().getAppearanceTime() : currentTime;
            }
            while (!tasks.isEmpty() && tasks.element().getAppearanceTime() <= currentTime) {
                if (currentTask != null && currentTime <= tasks.element().getAppearanceTime()) {
                    tasksQueue.add(currentTask);
                    currentTask = null;
                }
                tasksQueue.add(tasks.poll());
            }
            if (currentTask != null) {
                tasksQueue.add(currentTask);
            }
            
            currentTask = tasksQueue.poll();
            double timeForTask = currentTask.getDemand() - quantum >= 0 ? quantum : currentTask.getDemand();
            currentTask.setDemand(currentTask.getDemand() - timeForTask);
            currentTime += timeForTask;

            if (currentTask.getDemand() == 0) {
                currentTask.setDemand(currentTask.getFinishTime());
                currentTask.setFinishTime(currentTime);
                System.out.print(currentTask.toString());
                this.updateTimes(currentTask);
                currentTask = null;
            }
        }
        this.finishTimes();
    }
}

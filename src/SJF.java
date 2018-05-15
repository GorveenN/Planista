import java.util.PriorityQueue;

public class SJF extends Strategy {

    SJF(Task[] t) {
        super(t);
    }

    @Override
    protected void runTasks() {
        int currentTime = 0;
        PriorityQueue<Task> tasksQueue = new PriorityQueue<>(new DemandIDComparator());

        while (!tasks.isEmpty() || !tasksQueue.isEmpty()) {
            while (!tasks.isEmpty() && tasks.element().getAppearanceTime() <= currentTime) {
                tasksQueue.add(tasks.poll());
            }
            int nextTaskTime = !tasks.isEmpty() ? tasks.element().getAppearanceTime() : Integer.MAX_VALUE;

            while (!tasksQueue.isEmpty() && currentTime < nextTaskTime) {
                Task currentTask = tasksQueue.poll();
                currentTime += currentTask.getDemand();
                currentTask.setFinishTime(currentTime);
                System.out.print(currentTask.toString());
                this.updateTimes(currentTask);
            }

            currentTime = nextTaskTime > currentTime ? nextTaskTime : currentTime;
        }
        this.finishTimes();
    }
}

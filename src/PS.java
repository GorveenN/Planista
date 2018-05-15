import java.util.PriorityQueue;

public class PS extends Strategy {

    PS(Task[] t) {
        super(t);
    }

    @Override
    protected void runTasks() {
        double currentTime = 0;
        int tasksCount = 0;

        PriorityQueue<Task> tasksQueue = new PriorityQueue<>(new DemandIDComparator());


        while (!tasks.isEmpty() || !tasksQueue.isEmpty()) {

            while (!tasks.isEmpty() && tasks.element().getAppearanceTime() <= currentTime) {
                tasksQueue.add(tasks.poll());
                tasksCount++;
            }

            int nextTaskTime = !tasks.isEmpty() ? tasks.element().getAppearanceTime() : Integer.MAX_VALUE;
            double ranTime = 0;
            int size = tasksQueue.size();
            PriorityQueue<Task> tasksQueueHolder = new PriorityQueue<>(new DemandIDComparator());

            //while (!tasksQueue.isEmpty())
            for (int a = 0; a < size; a++)
            {
                Task currentTask = tasksQueue.poll();
                currentTask.setDemand(currentTask.getDemand() - ranTime);

                double timeToRun = (nextTaskTime - currentTime)/tasksCount >= currentTask.getDemand() ? currentTask.getDemand() : (nextTaskTime - currentTime)/tasksCount;
                currentTime += timeToRun * tasksCount;
                ranTime += timeToRun;
                currentTask.setDemand(currentTask.getDemand() - timeToRun);

                if (currentTask.getDemand() == 0) {
                    currentTask.setDemand(currentTask.getFinishTime());
                    currentTask.setFinishTime(currentTime);
                    System.out.print(currentTask.toString());
                    this.updateTimes(currentTask);
                    tasksCount--;
                }
                else {
                    tasksQueueHolder.add(currentTask);
                }
            }
            currentTime = nextTaskTime;
            tasksQueue = tasksQueueHolder;
        }
        waitTime = 0;
        this.finishTimes();
    }
}

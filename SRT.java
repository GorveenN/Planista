import java.util.PriorityQueue;

public class SRT extends Strategy {

    SRT(Task[] t) {
        super(t);
    }

    @Override
    protected void runTasks() {
        double currentTime = 0;
        Task lastTask = null;
        PriorityQueue<Task> tasksQueue = new PriorityQueue<>(new DemandIDComparator());

        while (!tasks.isEmpty() || !tasksQueue.isEmpty()) {
            boolean firstRun = true;
            while (!tasks.isEmpty() && tasks.element().getAppearanceTime() <= currentTime) {
                tasksQueue.add(tasks.poll());
            }

            int nextTaskTime = !tasks.isEmpty() ? tasks.element().getAppearanceTime() : Integer.MAX_VALUE;

            while (!tasksQueue.isEmpty() && currentTime < nextTaskTime) {
                Task currentTask = tasksQueue.poll();
                if (!tasksQueue.isEmpty() && tasksQueue.element().getDemand() == currentTask.getDemand() && tasksQueue.element().getId() < currentTask.getId()) {
                    Task tmp = currentTask;
                    currentTask = tasksQueue.poll();
                    tasksQueue.add(tmp);
                }

                if (lastTask != null && firstRun && currentTask != lastTask && lastTask.getId() < 0) {
                    lastTask.setID(-lastTask.getId());
                }

                double timeToRun = nextTaskTime - currentTime >= currentTask.getDemand() ? currentTask.getDemand() : nextTaskTime - currentTime;
                currentTime += timeToRun;

                if (currentTask.getDemand() == timeToRun) {
                    if (currentTask.getId() < 0) {
                        currentTask.setID(-currentTask.getId());
                    }
                    currentTask.setDemand(currentTask.getFinishTime());
                    currentTask.setFinishTime(currentTime);

                    System.out.print(currentTask.toString());

                    updateTimes(currentTask);
                }
                else {
                    currentTask.setDemand(currentTask.getDemand() - timeToRun);
                    tasksQueue.offer(currentTask);
                    if (currentTime >= nextTaskTime) {
                        currentTask.setID(currentTask.getId() > 0 ? -currentTask.getId() : currentTask.getId());
                        lastTask = currentTask;
                    }
                }
                firstRun = false;
            }
            currentTime = currentTime < nextTaskTime ? nextTaskTime : currentTime;
        }
        this.finishTimes();
    }
}

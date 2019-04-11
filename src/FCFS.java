public class FCFS extends Strategy {

    FCFS(Task[] t) {
        super(t);
    }

    @Override
    public void runTasks() {
        int tasksSize = tasks.size();
        int currentTime = 0;

        while (!tasks.isEmpty()) {
            Task currentTask = tasks.poll();
            currentTime += currentTask.getDemand();
            currentTask.setFinishTime(currentTime);

            if (!tasks.isEmpty() && currentTime < tasks.element().getAppearanceTime()) {
                currentTime = tasks.element().getAppearanceTime();
            }
            System.out.print(currentTask.toString());
            this.updateTimes(currentTask);
        }
        this.finishTimes();
    }
}

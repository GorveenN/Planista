public class Task {

    private int id;
    private double demand;
    private int appearanceTime;
    private double finishTime;


    Task(int id, double demand, int appearanceTime) {
        this.id = id;
        this.demand = demand;
        this.appearanceTime = appearanceTime;
        this.finishTime = demand;
    }

    Task(Task t) throws NullPointerException {
        if (t == null) {
            throw new NullPointerException();
        }
        this.id = t.id;
        this.demand = t.demand;
        this.appearanceTime = t.appearanceTime;
        this.finishTime = t.getFinishTime();
    }

    public int getAppearanceTime() {
        return appearanceTime;
    }

    public double getDemand() {
        return demand;
    }

    public int getId() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public double getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(double finishTime) {
        this.finishTime = finishTime;
    }

    protected void setDemand(double time) {
        this.demand = time;
    }

    @Override
    public String toString() {
        return "[" +
                Integer.toString(this.id) + " " +
                Integer.toString(this.appearanceTime) + " " +
                String.format("%.2f", (double)Math.round(this.finishTime * 100) / 100) + "]";
    }
}

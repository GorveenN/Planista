import java.util.Comparator;

public class DemandIDComparator implements Comparator<Task> {

    @Override
    public int compare(Task o1, Task o2) {
        if (o1.getDemand() < o2.getDemand()) {
            return -1;
        }
        else if (o1.getDemand() > o2.getDemand()) {
            return 1;
        }
        else return new IDComparator().compare(o1, o2);
    }
}

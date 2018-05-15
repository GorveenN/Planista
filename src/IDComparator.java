import java.util.Comparator;

public class IDComparator implements Comparator<Task> {

    @Override
    public int compare(Task o1, Task o2) {
        return Integer.compare(o1.getId(), o2.getId());
/*        if (o1.id < o2.id) { return -1; }
        else if (o1.id > o2.id) { return 1; }
        else return 0;*/
    }
}

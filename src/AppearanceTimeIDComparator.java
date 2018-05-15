import java.util.Comparator;

public class AppearanceTimeIDComparator implements Comparator<Task> {
    @Override
    public int compare(Task o1, Task o2) {
        if (o1.getAppearanceTime() < o2.getAppearanceTime()) { return -1; }
        else if (o1.getAppearanceTime() > o2.getAppearanceTime()) { return 1; }
        else return new IDComparator().compare(o1, o2);
    }
}

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Locale;

public class Planista {

    public static void main(String[] args) {
        Locale.setDefault(new Locale("en", "US"));
        Parser parser = null;
        try {
            if (args.length == 1) {
                parser = new Parser(args[0]);
            }
            else {
                parser = new Parser();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Plik z danymi nie jest dostępny.");
            System.exit(0);
        }
        Task[] tasks = new Task[0];

        int[] quanta = new int[0];
        try {
            tasks = parser.readTasks();
            quanta = parser.readQuantum(tasks.length + 2);
            parser.checkEOF(tasks.length + 3);
        }
        catch (InvalidLineException e) {
            System.out.println(e.toString());
            System.exit(0);
        }

        Arrays.sort(tasks, new AppearanceTimeIDComparator());

        FCFS fcfs  = new FCFS(tasks);
        fcfs.runTasksAndPrint();

        SJF sjf = new SJF(tasks);
        sjf.runTasksAndPrint();

        SRT srt = new SRT(tasks);
        srt.runTasksAndPrint();

        PS ps = new PS(tasks);
        ps.runTasksAndPrint();

        for (int quantum : quanta) {
            RR rr = new RR(tasks, quantum);
            rr.runTasksAndPrint();
        }
    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {

    private Scanner sc;

    Parser(String fileName) throws FileNotFoundException {
        this.sc = new Scanner(new File(fileName));
    }

    Parser() {
        this.sc = new Scanner(System.in);
    }

    private int readNumber(int id, Scanner line) throws InvalidLineException {
        if (line.hasNextInt()) {
            return line.nextInt();
        }
        throw new TooShortLineException(id + 1);
    }

    private Scanner readLine(int id) throws InvalidLineException {
        if (sc.hasNextLine()) {
            return new Scanner(sc.nextLine());

        }
        throw new TooShortLineException(id);
    }

    private int readParametersNumber(int id) throws InvalidLineException {
        Scanner line = readLine(id);
        int parametersNumber = readNumber(id, line);
        if (line.hasNext()) {
            throw new TooLongLineException(id);
        }
        return parametersNumber;
    }

    private Task readTask(int id) throws InvalidLineException {
        Scanner line = readLine(id);
        int appearanceTime = readNumber(id, line);
        int demand = readNumber(id, line);

        if (line.hasNext()) {
            throw new TooLongLineException(id + 1);
        }
        return new Task(id, demand, appearanceTime);
    }

    public Task[] readTasks() throws InvalidLineException {
        int taskNumber = readParametersNumber(1);

        Task[] tasks = new Task[taskNumber];
        for (int i = 0; i < taskNumber; i++) {
            tasks[i] = readTask(i + 1);
        }
        return tasks;
    }

    public int[] readQuantum(int id) throws InvalidLineException {
        int quantaNumber = readParametersNumber(id);
        int[] quanta = new int[quantaNumber];
        Scanner line = readLine(id + 1);
        for (int i = 0; i < quantaNumber; i++) {
            quanta[i] = readNumber(id, line);
        }
        if (line.hasNext()) {
            throw new TooLongFileException(id + 1);
        }
        if (sc.hasNext()) {
            throw new TooLongFileException(id + 2);
        }
        return quanta;
    }

    public void checkEOF(int id) throws TooLongFileException {
        if (sc.hasNext()) {
            throw new TooLongFileException(id);
        }
    }
}

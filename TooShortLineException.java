public class TooShortLineException extends InvalidLineException {

    TooShortLineException(int lineNumber) {
        super(lineNumber);
        this.message += "Za mało danych w wierszu lub dane nie reprezentują liczby.";
    }
}

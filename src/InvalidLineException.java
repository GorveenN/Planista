public abstract class InvalidLineException extends Exception {
    protected String message;

    InvalidLineException(int lineNumber) {
        this.message = "Błąd w wierszu " + Integer.toString(lineNumber) + ": ";
    }

    @Override
    public String toString() {
        return message;
    }
}

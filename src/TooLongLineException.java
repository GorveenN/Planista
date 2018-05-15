public class TooLongLineException extends InvalidLineException {

    TooLongLineException(int lineNumber) {
        super(lineNumber);
        this.message += "Za dużo danych.";
    }
}

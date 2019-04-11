public class TooLongFileException extends InvalidLineException {

    TooLongFileException(int id) {
        super(id);
        this.message += "Plik powinien już się skończyć.";
    }
}

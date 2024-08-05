package librarymanagementsystem.utils.exceptions;

public class BookNotAvailableException extends RuntimeException {

    public BookNotAvailableException() {
        super();
    }

    public BookNotAvailableException(String message) {
        super(message);
    }
}
package librarymanagementsystem.utils.exceptions;

public class PatronHasOverdueBooksException extends RuntimeException{
    public PatronHasOverdueBooksException() {
        super();
    }

    public PatronHasOverdueBooksException(String message) {
        super(message);
    }
}

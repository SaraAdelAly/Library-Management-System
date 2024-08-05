package librarymanagementsystem.utils.exceptions;

public class BorrowingNotFoundException extends RuntimeException{

    public BorrowingNotFoundException() {
        super();
    }

    public BorrowingNotFoundException(String message) {
        super(message);
    }
}

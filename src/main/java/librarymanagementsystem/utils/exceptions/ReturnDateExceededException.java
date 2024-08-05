package librarymanagementsystem.utils.exceptions;

public class ReturnDateExceededException extends RuntimeException{

    public ReturnDateExceededException() {
        super();
    }

    public ReturnDateExceededException(String message) {
        super(message);
    }
}

package librarymanagementsystem.utils;

public class PatronNotFoundException extends RuntimeException{
        public PatronNotFoundException(Integer id) {
            super("patron not found with id: " + id);
        }
}

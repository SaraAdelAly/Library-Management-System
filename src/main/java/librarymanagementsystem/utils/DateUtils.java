package librarymanagementsystem.utils;
import java.time.LocalDate;

public class DateUtils {
    public static boolean isDateInPast(LocalDate date) {
        return date != null && date.isBefore(LocalDate.now());
    }
}
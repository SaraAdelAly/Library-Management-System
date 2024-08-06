package librarymanagementsystem.utils.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import librarymanagementsystem.utils.DateUtils;
import librarymanagementsystem.utils.annotations.PastDate;

import java.time.LocalDate;

public class PastDateValidator implements ConstraintValidator<PastDate, LocalDate> {

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
        return DateUtils.isDateInPast(date);
    }
}

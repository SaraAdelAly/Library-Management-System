package librarymanagementsystem.utils.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import librarymanagementsystem.utils.validation.PastDateValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PastDateValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface PastDate {
    String message() default "Date must be in the past";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
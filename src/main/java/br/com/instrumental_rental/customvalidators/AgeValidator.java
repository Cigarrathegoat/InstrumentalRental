package br.com.instrumental_rental.customvalidators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class AgeValidator implements ConstraintValidator<IOverage, LocalDate> {

    @Override
    public void initialize(IOverage constraintAnnotation) {}
    @Override
    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext context) {
        if (dateOfBirth == null) {
            return true;
        }
        Period age = Period.between(dateOfBirth, LocalDate.now());
        return age.getYears() >= 18;
    }
}

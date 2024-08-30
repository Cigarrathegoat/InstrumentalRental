package br.com.instrumental_rental.validators;

import br.com.instrumental_rental.customvalidators.agevalidators.IOverage;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class EighteenYearsOldValidator implements ConstraintValidator<IOverage, LocalDate> {

    @Override
    public void initialize(IOverage constraintAnnotation) {
        // Initialization code, if needed
    }

    @Override
    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext context) {
        if (dateOfBirth == null) {
            return false;  // Null values are considered invalid
        }

        LocalDate today = LocalDate.now();
        return Period.between(dateOfBirth, today).getYears() >= 18;
    }
}


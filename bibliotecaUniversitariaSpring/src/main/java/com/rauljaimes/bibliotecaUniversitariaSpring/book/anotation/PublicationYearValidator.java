package com.rauljaimes.bibliotecaUniversitariaSpring.book.anotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Year;

public class PublicationYearValidator implements
        ConstraintValidator<ValidPublicationYear, Integer> {

    @Override
    public boolean isValid(Integer year, ConstraintValidatorContext context) {
        if(year == null) return true;

        return year <= Year.now().getValue();
    }
}

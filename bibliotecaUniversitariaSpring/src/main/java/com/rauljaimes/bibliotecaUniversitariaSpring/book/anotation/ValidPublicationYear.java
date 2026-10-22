package com.rauljaimes.bibliotecaUniversitariaSpring.book.anotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {PublicationYearValidator.class})
@Target({ElementType.FIELD,ElementType.PARAMETER,ElementType.ANNOTATION_TYPE,ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPublicationYear {

    String message() default "The publication year cannot exceed the current year";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

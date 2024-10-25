package com.example.claims.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = ClaimFieldValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidClaimField {
    String message() default "Valor inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String fieldType(); // "company", "reason", "status"
}
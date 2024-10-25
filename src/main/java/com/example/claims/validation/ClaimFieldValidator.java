package com.example.claims.validation;

import com.example.claims.constants.ClaimConstants;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class ClaimFieldValidator implements ConstraintValidator<ValidClaimField, String> {
    private String fieldType;

    @Override
    public void initialize(ValidClaimField constraintAnnotation) {
        this.fieldType = constraintAnnotation.fieldType();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Let @NotNull handle null validation
        }

        return switch (fieldType) {
            case "company" -> ClaimConstants.VALID_COMPANIES.contains(value);
            case "reason" -> ClaimConstants.VALID_REASONS.contains(value);
            case "status" -> ClaimConstants.VALID_STATUSES.contains(value);
            default -> false;
        };
    }
}
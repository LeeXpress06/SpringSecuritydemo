package com.Camp.SpringSecurity.SpringSecuritydemo.WorkerCustomValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
public class SalaryLaw implements ConstraintValidator<MinimumSalary,Long> {
    @Override
    public void initialize(MinimumSalary constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    @Override
    public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {
        if(value < 20) return false;
        else return true;
    }
}

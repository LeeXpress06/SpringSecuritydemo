package com.Camp.SpringSecurity.SpringSecuritydemo.WorkerCustomValidation;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = SalaryLaw.class)
public @interface MinimumSalary {








}

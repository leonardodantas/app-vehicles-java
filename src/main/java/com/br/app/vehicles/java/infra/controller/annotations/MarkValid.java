package com.br.app.vehicles.java.infra.controller.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MarkValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MarkValid {

    String message() default "Marca Invalida";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

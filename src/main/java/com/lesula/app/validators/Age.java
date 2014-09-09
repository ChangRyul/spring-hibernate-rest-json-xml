package com.lesula.app.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by enrico on 9/9/14.
 */
@Documented
@Constraint(validatedBy = AgeConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Age {

    int value();

    String message() default "{Age}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

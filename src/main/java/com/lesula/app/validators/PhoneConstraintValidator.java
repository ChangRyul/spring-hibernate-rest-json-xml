package com.lesula.app.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by enrico on 9/9/14.
 */
public class PhoneConstraintValidator implements ConstraintValidator<Phone, String> {
    @Override
    public void initialize(Phone phone) {

    }

    @Override
    public boolean isValid(String phoneField, ConstraintValidatorContext constraintValidatorContext) {
        if(phoneField == null) {
            //phone is not mandatory
            return true;
        }
        return phoneField.matches("[0-9]*");
    }
}

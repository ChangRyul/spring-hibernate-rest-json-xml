package com.lesula.app.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by enrico on 9/9/14.
 */
public class AgeConstraintValidator implements ConstraintValidator<Age, Date> {

    private int ageConstraint;
    @Override
    public void initialize(Age age) {
        ageConstraint = age.value();
    }

    @Override
    public boolean isValid(Date birthday, ConstraintValidatorContext constraintValidatorContext) {
        if(birthday == null){
            return true;
        }

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -ageConstraint);

        Date minConstraint = cal.getTime();
        return minConstraint.after(birthday);
    }

}

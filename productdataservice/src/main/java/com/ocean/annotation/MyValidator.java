package com.ocean.annotation;


import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class MyValidator implements ConstraintValidator<MyValid, String> {
    private static final String REGEX_EMAIL = "^\\s*\\w+(?:\\.?[\\w-]+)*@[\\w]+(?:[-.][\\w]+)*\\.[a-zA-Z]+\\s*$";

    @Override
    public void initialize(MyValid constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.isEmpty(s) ? true : Pattern.matches("^\\s*\\w+(?:\\.?[\\w-]+)*@[\\w]+(?:[-.][\\w]+)*\\.[a-zA-Z]+\\s*$",s);
    }
}

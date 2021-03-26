package com.ocean.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {MyValidator.class})
public @interface MyValid {
    String message() default "请输入正确的邮箱格式！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

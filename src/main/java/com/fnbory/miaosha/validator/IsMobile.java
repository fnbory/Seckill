package com.fnbory.miaosha.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
/**
 * @Author: fnbory
 * @Date: 2019/6/14 20:51
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {IsMobileValidator.class })
public @interface IsMobile {
    boolean required() default true;
    String message() default "手机号码格式错误";
    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

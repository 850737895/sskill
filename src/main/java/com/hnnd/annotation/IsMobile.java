package com.hnnd.annotation;

import com.hnnd.validator.MobileValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 表示是手机号码校验的注解
 * Created by 85073 on 2018/3/30.
 */

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {MobileValidator.class}
)
public @interface IsMobile {

    boolean requried() default true;

    String message() default "手机格式错误";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}

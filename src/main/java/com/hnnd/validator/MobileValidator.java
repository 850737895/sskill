package com.hnnd.validator;

import com.hnnd.annotation.IsMobile;
import com.hnnd.util.ValidatorUtils;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义手机验证器
 * Created by 85073 on 2018/3/30.
 */
public class MobileValidator implements ConstraintValidator<IsMobile, String> {

    private boolean isRequired=false;

    @Override
    public void initialize(IsMobile isMobile) {
        isRequired = isMobile.requried();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(!isRequired){
            return false;
        }else {
            if(StringUtils.isEmpty(s)) {
                return false;
            }else{
                return ValidatorUtils.checkMobileFormat(s);
            }
        }
    }
}

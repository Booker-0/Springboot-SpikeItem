package com.lyy.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
//没弄懂
@Component
public class ValidatorImpl implements InitializingBean {
    private Validator validator;
    //实现校验方法并返回校验结果
    public ValidationResult validate(Object bean){
        ValidationResult result = new ValidationResult();
        //验证违背了约束则set则会有错误信息
        Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(bean);
        //大于-则说明有错误
        if(constraintViolationSet.size()>0){
            result.setHasErrors(true);
            //java8中lamda表达式
            constraintViolationSet.forEach(constraintViolation->{
                String errMsg  = constraintViolation.getMessage();
                //还需要是哪个字段错了？
                String propertyName = constraintViolation.getPropertyPath().toString();
                result.getErrorMsgMap().put(propertyName,errMsg);

            });

        }
        return result;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //将hibernate validator通过工厂的初始化方式使其实例化
      this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}

package com.yunfatong.evidence.common.validator;


import com.yunfatong.evidence.common.exception.CommonException;
import com.yunfatong.evidence.common.exception.ValidationException;
import com.yunfatong.evidence.common.resultEnum.ResultCode;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * hibernate-validator校验工具类
 * <p>
 * 参考文档：http://docs.jboss.org/hibernate/validator/5.4/reference/en-US/html_single/
 */
public class ValidatorUtils {
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     *
     * @param object 待校验对象
     * @param groups 待校验的组
     * @throws CommonException 校验不通过，则报RRException异常
     */
    public static void validateEntity(Object object, Class<?>... groups)
            throws CommonException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            ConstraintViolation<Object> constraint = constraintViolations.iterator().next();
            throw new CommonException(constraint.getMessage());
        }
    }

    /**
     * 校验对象
     *
     * @param object 待校验对象
     * @param code   错误code枚举
     * @param groups 待校验的组
     * @throws ValidationException 校验不通过，则报RRException异常
     */
    public static void validateEntity(Object object, ResultCode code, Class<?>... groups)
            throws ValidationException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            ConstraintViolation<Object> constraint = constraintViolations.iterator().next();
            if (StringUtils.isNotBlank(constraint.getMessage())) {
                throw new ValidationException(code.getCode(), code.getMsg() + "-" + constraint.getMessage());
            } else {
                throw new ValidationException(code.getCode(), code.getMsg());
            }
        }
    }
}

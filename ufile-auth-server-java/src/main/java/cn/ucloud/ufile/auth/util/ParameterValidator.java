package cn.ucloud.ufile.auth.util;

import sun.security.validator.ValidatorException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Iterator;
import java.util.Set;

/**
 * @description:
 * @author: joshua
 * @E-mail: joshua.yin@ucloud.cn
 * @date: 2018-11-29 15:07
 */
public class ParameterValidator {
    /**
     * 校验器工厂
     */
    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    public static <T> void validator(T obj) throws ValidatorException {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
        Iterator<ConstraintViolation<T>> it = constraintViolations.iterator();
        while (it.hasNext())
            throw new ValidatorException(it.next().getMessage());
    }
}

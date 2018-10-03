package za.co.soma.solutions.smart.grocer.Service;

import za.co.soma.solutions.smart.grocer.exception.GrocerErrorType;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public interface SomaValidation {

    default GrocerErrorType validate(Validator validator, Object object, Class...  validationClass){

        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, validationClass);
        if(constraintViolations.isEmpty() ==false)
            return  new GrocerErrorType(constraintViolations.iterator().next().getMessage());

        return null;

    }
}

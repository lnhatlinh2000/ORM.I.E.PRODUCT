package validation;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class ValidatorTesting<T> {

    public boolean testHibernateValidator(T entity){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintValidatorSet = validator.validate(entity);

        if(constraintValidatorSet.size() > 0){
            for (ConstraintViolation<T> violation : constraintValidatorSet){
                System.out.println(violation.getMessage());
            }
            return false;
        } else {
            System.out.println("Valid Object");
            return true;
        }
    }
}

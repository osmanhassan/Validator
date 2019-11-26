package Validation;

import Validation.ValidationDecoratots.DefaultValidationDecorator;
import Validation.ValidationDecoratots.EmailValidationDecorator;
import Validation.ValidationDecoratots.RequiredValidationDecorator;

import java.util.HashMap;

public class ValidatorClassRegister {

    HashMap validatorClassRegistry;

    public ValidatorClassRegister(){
        this.validatorClassRegistry = new HashMap();

        this.validatorClassRegistry
                .put("required", new RequiredValidationDecorator<>(new DefaultValidationDecorator(), "").getClass().getName());
        this.validatorClassRegistry
                .put("email", new EmailValidationDecorator<>(new DefaultValidationDecorator(), "").getClass().getName());

    }

    public HashMap getValidatorClassRegistry() {
        return validatorClassRegistry;
    }
}

package Validation;

import java.util.HashMap;

public class ValidatorClassRegister {

    HashMap validatorClassRegistry;

    public ValidatorClassRegister(){
        this.validatorClassRegistry = new HashMap();

        this.validatorClassRegistry
                .put("required", "Validation.ValidationDecoratots.RequiredValidationDecorator");
        this.validatorClassRegistry
                .put("email", "Validation.ValidationDecoratots.EmailValidationDecorator");

    }

    public HashMap getValidatorClassRegistry() {
        return validatorClassRegistry;
    }
}

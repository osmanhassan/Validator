package Validation;

import Validation.ValidationDecoratots.*;
import java.util.HashMap;

public class ValidatorClassRegister {

    HashMap validatorClassRegistry;

    public ValidatorClassRegister() {

        this.validatorClassRegistry = new HashMap();

        this.validatorClassRegistry
                .put("required",
                        new RequiredValidationDecorator<>
                                (new DefaultValidationDecorator(), "")
                                .getClass().getName());

        this.validatorClassRegistry
                .put("default",
                        new DefaultValidationDecorator<>
                                (new DefaultValidationDecorator(), "")
                                .getClass().getName());

        this.validatorClassRegistry
                .put("bail",
                        new BailValidationDecorator<>
                                (new DefaultValidationDecorator(), "")
                                .getClass().getName());


    }

    public HashMap getValidatorClassRegistry() {
        return validatorClassRegistry;
    }
}

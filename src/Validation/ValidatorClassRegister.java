package Validation;

import Validation.ValidationDecoratots.*;
import java.util.HashMap;

public class ValidatorClassRegister {

    HashMap validatorClassRegistry;

    public ValidatorClassRegister() {

        this.validatorClassRegistry = new HashMap();

        this.validatorClassRegistry
                .put("alpha_dash", new AlphaDashValidationDecorator<>
                        (new DefaultValidationDecorator(), "")
                        .getClass().getName());

    }

    public HashMap getValidatorClassRegistry() {
        return validatorClassRegistry;
    }
}

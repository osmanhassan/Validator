package Validation;

import Validation.ValidationDecoratots.*;
import java.util.HashMap;

public class ValidatorClassRegister {

    HashMap validatorClassRegistry;

    public ValidatorClassRegister() {

        this.validatorClassRegistry = new HashMap();

    }

    public HashMap getValidatorClassRegistry() {
        return validatorClassRegistry;
    }
}

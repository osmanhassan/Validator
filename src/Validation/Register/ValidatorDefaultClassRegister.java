package Validation.Register;

import Validation.ValidationDecoratots.*;
import java.util.HashMap;

public class ValidatorDefaultClassRegister implements IValidatorClassRegister{

    @Override
    public HashMap<String, Class> registry() {
        HashMap<String, Class> validatorClassRegistry = new HashMap();

        validatorClassRegistry
                .put("email", EmailValidationDecorator.class);

        validatorClassRegistry
                .put("default", DefaultValidationDecorator.class);

        validatorClassRegistry
                .put("bail", BailValidationDecorator.class);

        return validatorClassRegistry;
    }
}

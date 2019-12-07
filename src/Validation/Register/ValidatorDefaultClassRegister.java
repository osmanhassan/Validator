package Validation.Register;

import Validation.ValidationDecoratots.*;
import java.util.HashMap;

public class ValidatorDefaultClassRegister implements IValidatorClassRegister{

    @Override
    public HashMap<String, Class> registry() {
        HashMap<String, Class> validatorClassRegistry = new HashMap();

        validatorClassRegistry
                .put("default",
                        new DefaultValidationDecorator<>
                                (new DefaultValidationDecorator(), "")
                                .getClass());

        validatorClassRegistry
                .put("bail",
                        new BailValidationDecorator<>
                                (new DefaultValidationDecorator(), "")
                                .getClass());

        validatorClassRegistry
                .put("date",
                        new DateValidationDecorator<>
                                (new DefaultValidationDecorator(), "")
                                .getClass());

        return validatorClassRegistry;
    }
}

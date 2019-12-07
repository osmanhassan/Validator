package Validation.Register;

import Validation.ValidationDecoratots.*;
import java.util.HashMap;

public class ValidatorDefaultClassRegister implements IValidatorClassRegister{

    @Override
    public HashMap<String, Class> registry() {
        HashMap<String, Class> validatorClassRegistry = new HashMap();

        validatorClassRegistry
                .put("alpha_num", new AlphaNumValidationDecorator<>
                        (new DefaultValidationDecorator(), "")
                        .getClass());

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

        return validatorClassRegistry;
    }
}

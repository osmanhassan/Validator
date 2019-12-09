package Validation.ValidationDecoratots;

import java.lang.reflect.Method;

public class BailValidationDecorator<T> extends ValidationDecorator<T> {

    public BailValidationDecorator(ValidationDecorator validationDecorator, String validationAdditionalInfo, String ruleName) {
        super(validationDecorator, validationAdditionalInfo, ruleName);
    }

    @Override
    public boolean isValid(T o, String subjectFieldName) throws Exception {
        setIsBail(true);
        return true;
    }
}

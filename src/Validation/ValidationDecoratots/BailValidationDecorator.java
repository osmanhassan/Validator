package Validation.ValidationDecoratots;

import java.lang.reflect.Method;

public class BailValidationDecorator<T> extends ValidationDecorator<T> {

    public BailValidationDecorator(ValidationDecorator validationDecorator, String validationAdditionalInfo) {
        super(validationDecorator, validationAdditionalInfo);
    }

    @Override
    public String validate(T o, String subjectFieldName) throws Exception {
        setIsBail(true);
        return "";
    }
}

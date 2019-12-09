package Validation.ValidationDecoratots;

import Validation.ValidationDecoratots.ValidationDecorator;

import java.lang.reflect.Method;
import java.util.List;

public class RequiredValidationDecorator<T> extends ValidationDecorator<T> {

    public RequiredValidationDecorator(ValidationDecorator validationDecorator, String validationAdditionalInfo, String ruleName) {
        super(validationDecorator, validationAdditionalInfo, ruleName);
    }

    @Override
    public boolean isValid(T o, String subjectFieldName) throws Exception {

        if (getIsNull()) {
           return false;
        }
        else{
            String value = getFieldValue(o, subjectFieldName);

            if (value.trim().equals("")) {
                return false;
            }
            return true;
        }

    }
}

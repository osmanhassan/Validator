package Validation.ValidationDecoratots;

import Validation.ValidationDecoratots.ValidationDecorator;

import java.lang.reflect.Method;
import java.util.List;

public class RequiredValidationDecorator<T> extends ValidationDecorator<T> {

    public RequiredValidationDecorator(ValidationDecorator validationDecorator, String validationAdditionalInfo) {
        super(validationDecorator, validationAdditionalInfo);
    }

    @Override
    public String validate(T o, String subjectFieldName) throws Exception {

        String displayName = getDisplayNameFormFieldName(subjectFieldName);
        boolean isValidationFailed = false;

        if (getIsNull()) {
            isValidationFailed = true;
        }
        else{
            String value = getFieldValue(o, subjectFieldName);

            if (value.trim().equals("")) {
                isValidationFailed = true;
            }
        }

        boolean isFailed = isValidationFailed && isBail;
        String message = displayName + " is required. ";

        if(isFailed)
            return message;

        String validationPassedString = validationDecorator.validate(o, subjectFieldName);
        String validationFailedString = message + validationPassedString;

        if(isValidationFailed)
            return validationFailedString;

        return validationPassedString;


    }
}

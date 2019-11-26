package Validation.ValidationDecoratots;

import Validation.ValidationDecoratots.ValidationDecorator;

import java.lang.reflect.Method;
import java.util.List;

public class RequiredValidationDecorator<T> extends ValidationDecorator<T> {

    public RequiredValidationDecorator(ValidationDecorator validationDecorator, String validationAdditionalInfo){
        super(validationDecorator, validationAdditionalInfo);
    }

    @Override
    public String validate(T o, String subjectFieldName) throws Exception {

        String displayName = getDisplayNameFormFieldName(subjectFieldName);

        String validationPassedString = validationDecorator.validate(o,  subjectFieldName);
        String validationFailedString = displayName  + " is required. " + validationPassedString;

        if(getIsNull()){
            return validationFailedString;
        }

        String value = getFieldValue(o, subjectFieldName);

        if(value.trim().equals("")){
            return validationFailedString;
        }

        return validationPassedString;


    }
}

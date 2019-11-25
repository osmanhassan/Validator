package Validation.ValidationDecoratots;

import Validation.ValidationDecoratots.ValidationDecorator;

import java.lang.reflect.Method;
import java.util.List;

public class RequiredValidationDecorator<T> extends ValidationDecorator<T> {

    ValidationDecorator validationDecorator;

    public RequiredValidationDecorator(ValidationDecorator validationDecorator){
        this.validationDecorator = validationDecorator;
    }

    @Override
    public String validate(T o, String additionalDataOfRule, String subjectFieldName) throws Exception {

        String displayName = getDisplayNameFormFieldName(subjectFieldName);

        String validationPassedString = this.validationDecorator.validate(o, additionalDataOfRule, subjectFieldName);
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

package Validation.ValidationDecoratots;

import java.util.List;

public class AcceptedValidationDecorator extends ValidationDecorator {

    ValidationDecorator validationDecorator;

    public AcceptedValidationDecorator(ValidationDecorator validationDecorator){
        this.validationDecorator = validationDecorator;
    }

    @Override
    public String validate(Object o, String additionalDataOfRule, String subjectFieldName) throws Exception {

        String displayName = getDisplayNameFormFieldName(subjectFieldName);

        String validationPassedString = this.validationDecorator.validate(o, additionalDataOfRule, subjectFieldName);
        String validationFailedString = displayName  + " must be accepted. " + validationPassedString;

        if(! getIsNull()){

            String value = getFieldValue(o, subjectFieldName).trim().toLowerCase();

            if(value.equals("1") || value.equals("yes") || value.equals("on") || value.equals("true") ){
                return validationPassedString;
            }
            return validationFailedString;
        }

        return validationPassedString;
    }
}

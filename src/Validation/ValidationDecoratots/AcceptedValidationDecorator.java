package Validation.ValidationDecoratots;

import java.util.List;

public class AcceptedValidationDecorator extends ValidationDecorator {

    public AcceptedValidationDecorator(ValidationDecorator validationDecorator, String validationAdditionalInfo){
        super(validationDecorator, validationAdditionalInfo);
    }

    @Override
    public String validate(Object o, String subjectFieldName) throws Exception {

        String displayName = getDisplayNameFormFieldName(subjectFieldName);

        String validationPassedString = validationDecorator.validate(o, subjectFieldName);

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

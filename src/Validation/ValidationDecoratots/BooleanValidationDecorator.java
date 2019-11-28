package Validation.ValidationDecoratots;

import java.util.regex.Matcher;

public class BooleanValidationDecorator<T> extends ValidationDecorator<T> {
    public BooleanValidationDecorator(ValidationDecorator validationDecorator, String validationAdditionalInfo) {
        super(validationDecorator, validationAdditionalInfo);
    }

    @Override
    public String validate(T o, String subjectFieldName) throws Exception {

        boolean isValidationFailed = false;

        String displayName = getDisplayNameFormFieldName(subjectFieldName);

        if (!getIsNull()) {

            String value = getFieldValue(o, subjectFieldName).trim().toLowerCase();

            try {
                boolean b = Boolean.valueOf(value);
            }
            catch (Exception e){
                isValidationFailed = true;
                //e.printStackTrace();
            }
        }

        boolean isFailed = isValidationFailed && isBail;
        String message = displayName + " must be boolean. ";

        if(isFailed)
            return message;

        String validationPassedString = validationDecorator.validate(o, subjectFieldName);
        String validationFailedString = message + validationPassedString;

        if(isValidationFailed)
            return validationFailedString;

        return validationPassedString;
    }
}

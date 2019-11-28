package Validation.ValidationDecoratots;

public class AcceptedValidationDecorator<T> extends ValidationDecorator<T> {

    public AcceptedValidationDecorator(ValidationDecorator validationDecorator, String validationAdditionalInfo) {
        super(validationDecorator, validationAdditionalInfo);
    }

    @Override
    public String validate(T o, String subjectFieldName) throws Exception {

        String displayName = getDisplayNameFormFieldName(subjectFieldName);
        boolean isValidationFailed = false;

        if (!getIsNull()) {

            String value = getFieldValue(o, subjectFieldName).trim().toLowerCase();

            if (value.equals("1") || value.equals("yes") || value.equals("on") || value.equals("true")) {

            }
            else{
                isValidationFailed = true;
            }

        }

        boolean isFailed = isValidationFailed && isBail;
        String message = displayName +" must be accepted. ";

        if(isFailed)
            return message;

        String validationPassedString = validationDecorator.validate(o, subjectFieldName);
        String validationFailedString = message + validationPassedString;

        if(isValidationFailed)
            return validationFailedString;

        return validationPassedString;
    }
}

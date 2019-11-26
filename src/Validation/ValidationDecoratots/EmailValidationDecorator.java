package Validation.ValidationDecoratots;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidationDecorator<T> extends ValidationDecorator<T> {


    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    public EmailValidationDecorator(ValidationDecorator validationDecorator, String validationAdditionalInfo) {
        super(validationDecorator, validationAdditionalInfo);
    }

    @Override
    public String validate(T o, String subjectFieldName) throws Exception {

        boolean isValidationFailed = false;


        String email = "";

        if (!getIsNull()) {

            email = getFieldValue(o, subjectFieldName).trim();

            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
            if (!matcher.find()) {
                isValidationFailed = true;
            }

        }

        boolean isFailed = isValidationFailed && isBail;
        String message = email + " is not a valid email. ";

        if(isFailed)
            return message;

        String validationPassedString = validationDecorator.validate(o, subjectFieldName);
        String validationFailedString = message + validationPassedString;

        if(isValidationFailed)
            return validationFailedString;

        return validationPassedString;
    }
}

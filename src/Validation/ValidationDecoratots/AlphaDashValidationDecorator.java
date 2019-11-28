package Validation.ValidationDecoratots;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//The field under validation may have alpha-numeric characters, as well as dashes and underscores.

public class AlphaDashValidationDecorator<T> extends ValidationDecorator<T> {

    public static final Pattern VALID_ALPHA_DASH_REGEX =
            Pattern.compile("^[0-9A-Za-z\\_-]+$", Pattern.CASE_INSENSITIVE);

    public AlphaDashValidationDecorator(ValidationDecorator validationDecorator, String validationAdditionalInfo) {
        super(validationDecorator, validationAdditionalInfo);
    }

    @Override
    public String validate(T o, String subjectFieldName) throws Exception {

        String fieldDisplayName = getDisplayNameFormFieldName(subjectFieldName);
        boolean isValidationFailed = false;

        if (!getIsNull()) {

            String fieldValue = getFieldValue(o, subjectFieldName).trim();
            Matcher matcher = VALID_ALPHA_DASH_REGEX.matcher(fieldValue);

            if (!matcher.find()) {
                isValidationFailed = true;
            }

        }

        boolean isFailed = isValidationFailed && isBail;
        String message = fieldDisplayName + " can contain alphabets, digits, '-' and '_' only. ";

        if(isFailed)
            return message;

        String validationPassedString = validationDecorator.validate(o, subjectFieldName);
        String validationFailedString = message + validationPassedString;

        if(isValidationFailed)
            return validationFailedString;

        return validationPassedString;
    }
}

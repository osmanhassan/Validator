package Validation.ValidationDecoratots;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


//The field under validation must be entirely alpha-numeric characters.

public class AlphaNumValidationDecorator<T> extends ValidationDecorator<T> {

    public static final Pattern VALID_ALPHA_NUM_REGEX =
            Pattern.compile("[0-9]+$", Pattern.CASE_INSENSITIVE);

    public AlphaNumValidationDecorator(ValidationDecorator validationDecorator, String validationAdditionalInfo) {
        super(validationDecorator, validationAdditionalInfo);
    }

    @Override
    public String validate(T o, String subjectFieldName) throws Exception {

        String validationPassedString = validationDecorator.validate(o, subjectFieldName);

        if (!getIsNull()) {
            String fieldDisplayName = getDisplayNameFormFieldName(subjectFieldName);
            String fieldValue = getFieldValue(o, subjectFieldName).trim();
            String validationFailedString = fieldDisplayName + " can contain digits only. " + validationPassedString;

            Matcher matcher = VALID_ALPHA_NUM_REGEX.matcher(fieldValue);
            if (!matcher.find()) {
                return validationFailedString;
            }

            return validationPassedString;

        }

        return validationPassedString;
    }
}


package Validation.ValidationDecoratots;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// The field under validation must be entirely alphabetic characters.

public class AlphaValidationDecorator<T> extends ValidationDecorator<T> {

    public static final Pattern VALID_ALPHA_REGEX =
            Pattern.compile("^[a-zA-Z]*$", Pattern.CASE_INSENSITIVE);

    public AlphaValidationDecorator(ValidationDecorator validationDecorator, String validationAdditionalInfo) {
        super(validationDecorator, validationAdditionalInfo);
    }

    @Override
    public String validate(T o, String subjectFieldName) throws Exception {

        String validationPassedString = validationDecorator.validate(o, subjectFieldName);

        if (!getIsNull()) {
            String fieldDisplayName = getDisplayNameFormFieldName(subjectFieldName);
            String fieldValue = getFieldValue(o, subjectFieldName).trim();
            String validationFailedString = fieldDisplayName + " can contain alphabets only. " + validationPassedString;

            Matcher matcher = VALID_ALPHA_REGEX.matcher(fieldValue);
            if (!matcher.find()) {
                return validationFailedString;
            }

            return validationPassedString;

        }

        return validationPassedString;
    }
}

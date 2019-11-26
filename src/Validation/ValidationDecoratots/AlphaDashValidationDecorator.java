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

        String validationPassedString = validationDecorator.validate(o, subjectFieldName);

        if (!getIsNull()) {
            String fieldDisplayName = getDisplayNameFormFieldName(subjectFieldName);
            String fieldValue = getFieldValue(o, subjectFieldName).trim();
            String validationFailedString = fieldDisplayName + " can contain alphabets, digits, '-' and '_' only. " + validationPassedString;

            Matcher matcher = VALID_ALPHA_DASH_REGEX.matcher(fieldValue);
            if (!matcher.find()) {
                return validationFailedString;
            }

            return validationPassedString;

        }

        return validationPassedString;
    }
}

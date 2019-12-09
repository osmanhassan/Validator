package Validation.ValidationDecoratots;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// The field under validation must be entirely alphabetic characters.

public class AlphaValidationDecorator<T> extends ValidationDecorator<T> {

    public static final Pattern VALID_ALPHA_REGEX =
            Pattern.compile("^[a-zA-Z]*$", Pattern.CASE_INSENSITIVE);

    public AlphaValidationDecorator(ValidationDecorator validationDecorator, String validationAdditionalInfo, String ruleName) {
        super(validationDecorator, validationAdditionalInfo, ruleName);
    }

    @Override
    public boolean isValid(T o, String subjectFieldName) throws Exception {

        if (!getIsNull()) {

            String fieldValue = getFieldValue(o, subjectFieldName).trim();
            Matcher matcher = VALID_ALPHA_REGEX.matcher(fieldValue);

            if (!matcher.find()) {
                return false;
            }

        }

        return true;

    }
}

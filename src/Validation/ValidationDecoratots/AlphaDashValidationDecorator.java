package Validation.ValidationDecoratots;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//The field under validation may have alpha-numeric characters, as well as dashes and underscores.

public class AlphaDashValidationDecorator<T> extends ValidationDecorator<T> {

    public static final Pattern VALID_ALPHA_DASH_REGEX =
            Pattern.compile("^[0-9A-Za-z\\_-]+$", Pattern.CASE_INSENSITIVE);

    public AlphaDashValidationDecorator(ValidationDecorator validationDecorator, String validationAdditionalInfo, String ruleName) {
        super(validationDecorator, validationAdditionalInfo, ruleName);
    }

    @Override
    public boolean isValid(T o, String subjectFieldName) throws Exception {

        if (!getIsNull()) {

            String fieldValue = getFieldValue(o, subjectFieldName).trim();
            Matcher matcher = VALID_ALPHA_DASH_REGEX.matcher(fieldValue);

            if (!matcher.find()) {
                return false;
            }
        }
        return true;

    }
}

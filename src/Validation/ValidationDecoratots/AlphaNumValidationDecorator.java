package Validation.ValidationDecoratots;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


//The field under validation must be entirely alpha-numeric characters.

public class AlphaNumValidationDecorator<T> extends ValidationDecorator<T> {

    public static final Pattern VALID_ALPHA_NUM_REGEX =
            Pattern.compile("[0-9]+$", Pattern.CASE_INSENSITIVE);

    public AlphaNumValidationDecorator(ValidationDecorator validationDecorator, String validationAdditionalInfo, String ruleName) {
        super(validationDecorator, validationAdditionalInfo, ruleName);
    }

    @Override
    public boolean isValid(T o, String subjectFieldName) throws Exception {

        if (!getIsNull()) {

            String fieldValue = getFieldValue(o, subjectFieldName).trim();
            Matcher matcher = VALID_ALPHA_NUM_REGEX.matcher(fieldValue);

            if (!matcher.find()) {
               return false;
            }

        }

       return true;
    }
}


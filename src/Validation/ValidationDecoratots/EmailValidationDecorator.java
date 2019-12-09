package Validation.ValidationDecoratots;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidationDecorator<T> extends ValidationDecorator<T> {


    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    public EmailValidationDecorator(ValidationDecorator validationDecorator, String validationAdditionalInfo, String ruleName) {
        super(validationDecorator, validationAdditionalInfo, ruleName);
    }

    @Override
    public boolean isValid(T o, String subjectFieldName) throws Exception {

        String email = "";

        if (!getIsNull()) {

            email = getFieldValue(o, subjectFieldName).trim();

            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
            if (!matcher.find()) {
               return false;
            }

        }

        return true;
    }
}

package Validation.ValidationDecoratots;

import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidationDecorator<T> extends ValidationDecorator<T> {


    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    public EmailValidationDecorator(ValidationDecorator validationDecorator, String validationAdditionalInfo){
        super(validationDecorator, validationAdditionalInfo);
    }

    @Override
    public String validate(T o, String subjectFieldName) throws Exception {

        String validationPassedString = validationDecorator.validate(o, subjectFieldName);

        if(! getIsNull()){

            String email = getFieldValue(o, subjectFieldName).trim();
            String validationFailedString = email  + " is not a valid email. " + validationPassedString;

            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
            if(! matcher.find()){
                return  validationFailedString;
            }

            return validationPassedString;

        }


        return validationPassedString;
    }
}

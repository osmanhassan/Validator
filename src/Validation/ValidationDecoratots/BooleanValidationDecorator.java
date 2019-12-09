package Validation.ValidationDecoratots;

import java.util.regex.Matcher;

public class BooleanValidationDecorator<T> extends ValidationDecorator<T> {
    public BooleanValidationDecorator(ValidationDecorator validationDecorator, String validationAdditionalInfo, String ruleName) {
        super(validationDecorator, validationAdditionalInfo, ruleName);
    }

    @Override
    public boolean isValid(T o, String subjectFieldName) throws Exception {

        if (!getIsNull()) {

            String value = getFieldValue(o, subjectFieldName).trim().toLowerCase();

            if(value.equals("true") || value.equals("false") || value.equals("1") || value.equals("0")){
                return true;
            }
            else{
                return false;
            }
        }

        return true;

    }
}

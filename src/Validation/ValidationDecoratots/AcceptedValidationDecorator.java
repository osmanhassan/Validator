package Validation.ValidationDecoratots;

public class AcceptedValidationDecorator<T> extends ValidationDecorator<T> {

    public AcceptedValidationDecorator(ValidationDecorator validationDecorator, String validationAdditionalInfo, String ruleName) {
        super(validationDecorator, validationAdditionalInfo, ruleName);
    }

    @Override
    public boolean isValid(T o, String subjectFieldName) throws Exception {

        if (!getIsNull()) {

            String value = getFieldValue(o, subjectFieldName).trim().toLowerCase();

            if (value.equals("1") || value.equals("yes") || value.equals("on") || value.equals("true")) {
                return true;
            }
            else{
                return false;
            }

        }

       return true;
    }
}

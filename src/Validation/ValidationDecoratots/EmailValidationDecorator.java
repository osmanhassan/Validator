package Validation.ValidationDecoratots;

import java.util.List;

public class EmailValidationDecorator<T> extends ValidationDecorator<T> {

    ValidationDecorator validationDecorator;

    public EmailValidationDecorator(ValidationDecorator validationDecorator){
        this.validationDecorator = validationDecorator;
    }

    @Override
    public String validate(T o, List<String> allFieldNames, String subjectFieldName) throws Exception {
        return "Email validated " + this.validationDecorator.validate(o, allFieldNames, subjectFieldName);
    }
}

package Validation.ValidationDecoratots;

import java.util.List;

public class DefaultValidationDecorator<T> extends ValidationDecorator<T> {
    @Override
    public String validate(T o, List<String> allFieldNames, String subjectFieldName) {
        return "";
    }
}

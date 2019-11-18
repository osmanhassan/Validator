package Validation.ValidationDecoratots;

import java.util.List;

public abstract class ValidationDecorator<T> {
    public abstract String validate(T o, List<String> allFieldNames, String subjectFieldName) throws Exception;
}

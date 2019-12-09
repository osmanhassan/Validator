package Validation.ValidationDecoratots;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;


public class DefaultValidationDecorator<T> extends ValidationDecorator<T> {

    public DefaultValidationDecorator(ValidationDecorator validationDecorator, String validationAdditionalInfo, String ruleName) {
        super(validationDecorator, validationAdditionalInfo, ruleName);
    }

    public DefaultValidationDecorator(ValidationDecorator validationDecorator, String validationAdditionalInfo, HashMap settings, String ruleName) {
        super(validationDecorator, validationAdditionalInfo, ruleName);
        setSettings(settings);
    }

    public DefaultValidationDecorator() {
        super(null, "", "");
    }

    @Override
    public boolean isValid(T o, String subjectFieldName) throws Exception {

        Field field = o.getClass().getDeclaredField(subjectFieldName);
        boolean isAccessible = field.isAccessible();
        field.setAccessible(true);

        if (field.get(o) == null) {
            setIsNull(true);
        } else {
            setIsNull(false);
        }

        field.setAccessible(isAccessible);

        return true;
    }
}

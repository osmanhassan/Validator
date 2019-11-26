package Validation.ValidationDecoratots;

import java.lang.reflect.Method;



public class DefaultValidationDecorator<T> extends ValidationDecorator<T> {

    public DefaultValidationDecorator(ValidationDecorator validationDecorator, String validationAdditionalInfo) {
        super(validationDecorator, validationAdditionalInfo);
    }

    public DefaultValidationDecorator() {
        super(null, "");
    }

    @Override
    public String validate(T o, String subjectFieldName) throws Exception {

        Method method = getMethodFromFieldName(o, subjectFieldName);

        if (method.invoke(o) == null) {
            setIsNull(true);
        } else {
            setIsNull(false);
        }

        return "";
    }
}

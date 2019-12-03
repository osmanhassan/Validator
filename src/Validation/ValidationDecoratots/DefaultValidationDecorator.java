package Validation.ValidationDecoratots;

import java.lang.reflect.Method;
import java.util.HashMap;


public class DefaultValidationDecorator<T> extends ValidationDecorator<T> {

    public DefaultValidationDecorator(ValidationDecorator validationDecorator, String validationAdditionalInfo) {
        super(validationDecorator, validationAdditionalInfo);
    }

    public DefaultValidationDecorator(ValidationDecorator validationDecorator, String validationAdditionalInfo, HashMap settings) {
        super(validationDecorator, validationAdditionalInfo);
        setSettings(settings);
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

        if(validationDecorator == null)
            return "";
        return validationDecorator.validate(o, subjectFieldName);
    }
}

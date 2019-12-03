package Validation.ValidationDecoratots;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

public abstract class ValidationDecorator<T> {

    static boolean isNull;
    static boolean isBail;
    static HashMap settings;

    ValidationDecorator validationDecorator;
    String validationAdditionalInfo;

    public ValidationDecorator(ValidationDecorator validationDecorator, String validationAdditionalInfo) {
        this.validationDecorator = validationDecorator;
        this.validationAdditionalInfo = validationAdditionalInfo;
    }

    public abstract String validate(T o, String subjectFieldName) throws Exception;

    public void setIsNull(boolean isNull) {
        ValidationDecorator.isNull = isNull;
    }

    public boolean getIsNull() {
        return isNull;
    }

    public void setIsBail(boolean isBail) {
        ValidationDecorator.isBail = isBail;
    }
    public boolean getIsBail() {
        return isBail;
    }

    public void setSettings(HashMap settings) {
        ValidationDecorator.settings = settings;
    }
    public HashMap getSettings() {
        return settings;
    }

    public Method getMethodFromFieldName(T o, String fieldName) throws Exception {
        String methodName = getMethodNameFromFieldName(fieldName);
        Method method = o.getClass().getMethod(methodName);
        return method;
    }

    private String getMethodNameFromFieldName(String fieldName) {
        String getFieldMethodName = "get" + getDisplayNameFormFieldName(fieldName);
        return getFieldMethodName;
    }

    public String getDisplayNameFormFieldName(String fieldName) {
        char[] fieldNameCharArray = fieldName.toCharArray();
        fieldNameCharArray[0] = Character.toUpperCase(fieldNameCharArray[0]);
        return new String(fieldNameCharArray);
    }

    public String getFieldValue(T o, String fieldName) throws Exception {
        Method method = getMethodFromFieldName(o, fieldName);
        return method.invoke(o).toString();
    }
}

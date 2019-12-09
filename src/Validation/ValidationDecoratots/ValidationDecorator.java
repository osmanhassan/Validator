package Validation.ValidationDecoratots;

import Validation.ErrorMessage.DefaultErrorMessages;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

public abstract class ValidationDecorator<T> {

    static boolean isNull;
    static boolean isBail;
    static HashMap settings;

    ValidationDecorator validationDecorator;
    String validationAdditionalInfo;
    String ruleName;

    public ValidationDecorator(ValidationDecorator validationDecorator, String validationAdditionalInfo, String ruleName) {
        this.validationDecorator = validationDecorator;
        this.validationAdditionalInfo = validationAdditionalInfo;
        this.ruleName = ruleName;
    }

    public abstract boolean isValid(T o, String subjectFieldName) throws Exception;

    public String validate(T o, String subjectFieldName, HashMap<String, String> errorMessages) throws Exception {

        boolean isBailed = isBail;

        boolean isValid = isValid(o, subjectFieldName);

        boolean isFailed = isBailed && ! isValid;

        String errorMessage = "";

        if(! isValid){
            errorMessage = getErrorMessage(subjectFieldName, errorMessages, o, subjectFieldName);
        }

        if(isFailed){
            return errorMessage;
        }
        else if(! isValid){
            if(validationDecorator == null)
                return errorMessage;

            return errorMessage + " " + validationDecorator.validate(o, subjectFieldName, errorMessages);
        }

        if(validationDecorator != null)
            return validationDecorator.validate(o, subjectFieldName, errorMessages);
        return "";
    }

    public String getErrorMessage(String subjectFieldName, HashMap<String, String> errorMessages, T subjectObject, String fieldName){

        String key = subjectFieldName + "." + this.ruleName;
        String errorMessage = "";

        if(errorMessages != null) {
            if (errorMessages.containsKey(key)) {
                errorMessage = errorMessages.get(key);
                return getModifiedMessage(errorMessage, subjectObject, fieldName);
            }
        }

        HashMap<String, String> defaultErrorMessages = new DefaultErrorMessages().getErrorMessages();

        if(defaultErrorMessages.containsKey(this.ruleName)){
            errorMessage = defaultErrorMessages.get(this.ruleName);;
            return getModifiedMessage(errorMessage, subjectObject, fieldName);
        }

        return errorMessage;

    }

    public String getModifiedMessage(String message, T o, String fieldName) {

        try {
            return message.replace("{{fieldName}}", getDisplayNameFormFieldName(fieldName)).replace("{{value}}", getFieldValue(o, fieldName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

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

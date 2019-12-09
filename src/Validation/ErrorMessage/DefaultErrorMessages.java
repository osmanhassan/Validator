package Validation.ErrorMessage;

import java.util.HashMap;

public class DefaultErrorMessages implements IErrorMessages {
    @Override
    public HashMap<String, String> getErrorMessages() {
        HashMap<String, String> errorMessages = new HashMap<>();

        errorMessages.put("default", "");
        errorMessages.put("bail", "");
        errorMessages.put("alpha_dash", "{{fieldName}} can contain alphabets, digits, '-' and '_' only.");

        return errorMessages;
    }
}

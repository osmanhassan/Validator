package Validation.ErrorMessage;

import java.util.HashMap;

public class DefaultErrorMessages implements IErrorMessages {
    @Override
    public HashMap<String, String> getErrorMessages() {
        HashMap<String, String> errorMessages = new HashMap<>();

        errorMessages.put("default", "");
        errorMessages.put("bail", "");
        errorMessages.put("date_after", "{{fieldName}} should be a date after provided date.");

        return errorMessages;
    }
}

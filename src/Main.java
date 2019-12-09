import Pojo.User;
import Validation.Settings.ValidatorCustomSettings;
import Validation.Validator.Validator;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Main {

    public static void main(String[] args) throws Exception {

        LinkedHashMap rulesByFields = new LinkedHashMap();
        String date = "12/03/2019";
        rulesByFields.put("name", "bail | date_after: " + date + ", MM/dd/yyyy");
        User user = new User();

        user.setName("12/02/2019");
        user.setEmail("has@g.cp");

        HashMap<String, String> errorMessages = new HashMap<>();
        errorMessages.put("name.date_after", "{{fieldName}} must be a date after " + date);

       String message = new Validator(user, rulesByFields)
                            .settings(new ValidatorCustomSettings())
                            .errorMessages(errorMessages)
                            .validate();
       
        System.out.println(message);

    }
}

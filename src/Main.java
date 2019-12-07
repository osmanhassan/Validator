import Pojo.User;
import Validation.Settings.ValidatorCustomSettings;
import Validation.Validator.Validator;

import java.util.LinkedHashMap;

public class Main {

    public static void main(String[] args) throws Exception {

        LinkedHashMap rulesByFields = new LinkedHashMap();
        rulesByFields.put("name", "bail | date_after: 12/03/2019, MM/dd/yyyy");
        User user = new User();

        user.setName("12/02/2019");
        user.setEmail("has@g.cp");

       String message = new Validator(user, rulesByFields)
                            .settings(new ValidatorCustomSettings())
                            .validate();
       
        System.out.println(message);

    }
}

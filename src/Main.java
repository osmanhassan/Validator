import Pojo.User;
import Validation.Settings.ValidatorCustomSettings;
import Validation.Validator.Validator;

import java.util.LinkedHashMap;

public class Main {

    public static void main(String[] args) throws Exception {

        LinkedHashMap rulesByFields = new LinkedHashMap();
        rulesByFields.put("name", "alpha");
        rulesByFields.put("email", "alpha");

        User user = new User();

        user.setName("Na-d1i_m");
        user.setEmail("123423");

       String message = new Validator(user, rulesByFields)
                            .settings(new ValidatorCustomSettings())
                            .validate();
       
        System.out.println(message);


    }
}

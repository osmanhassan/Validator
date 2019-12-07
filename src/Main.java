import Pojo.User;
import Validation.Settings.ValidatorCustomSettings;
import Validation.Validator.Validator;

import java.util.LinkedHashMap;

public class Main {

    public static void main(String[] args) throws Exception {

        LinkedHashMap rulesByFields = new LinkedHashMap();
        rulesByFields.put("name", "alpha_dash");
        rulesByFields.put("email", "alpha_dash");

        User user = new User();

        user.setName("Na_d1-im");
        user.setEmail("has@g.cp");


       String message = new Validator(user, rulesByFields)
                            .settings(new ValidatorCustomSettings())
                            .validate();
       
        System.out.println(message);


    }
}

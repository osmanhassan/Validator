import Pojo.User;
import Validation.Settings.ValidatorCustomSettings;
import Validation.Validator.Validator;

import java.util.LinkedHashMap;

public class Main {

    public static void main(String[] args) throws Exception {

        LinkedHashMap rulesByFields = new LinkedHashMap();

        User user = new User();

       String message = new Validator(user, rulesByFields)
                            .settings(new ValidatorCustomSettings())
                            .validate();
       
        System.out.println(message);


    }
}

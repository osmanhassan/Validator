import Pojo.User;
import Validation.Validator.Validator;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Main {

    public static void main(String[] args) {

        LinkedHashMap rulesByFields = new LinkedHashMap();
        rulesByFields.put("name", "required | email");
        rulesByFields.put("email", "required | email");

        LinkedHashMap errorMessageByRulesByFields = new LinkedHashMap();
        rulesByFields.put("name.required", "Name is required");
        rulesByFields.put("email.required", "Email is required");
        rulesByFields.put("name.email", "Name is not a valid email");


        User user = new User();

        Validator validator = new Validator(user, rulesByFields);
        System.out.println(validator.validate());

        User user1 = new User();
        user1.setName("Nadim");
        rulesByFields = new LinkedHashMap();
        rulesByFields.put("name", "required");
        validator = new Validator(user1, rulesByFields);
        System.out.println(validator.validate());

    }
}

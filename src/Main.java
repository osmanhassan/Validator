import Pojo.User;
import Validation.Validator.Validator;
import javafx.print.Collation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        LinkedHashMap rulesByFields = new LinkedHashMap();
        rulesByFields.put("name", "required");
        rulesByFields.put("email", "required | email");
        rulesByFields.put("age", "required");

        List collation = new ArrayList<Integer>();

        User user = new User();
        user.setName("Nadim");
        user.setEmail("has@g.cp");

        Validator validator = new Validator(user, rulesByFields);
        System.out.println(validator.validate());



    }
}

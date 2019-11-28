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
        rulesByFields.put("name", "date : MM/dd/yyyy");
        rulesByFields.put("email", "date");

        User user = new User();
        user.setName("11-28-2019");
        user.setEmail("2019-11-28 01:09:23");

        Validator validator = new Validator(user, rulesByFields);
        System.out.println(validator.validate());


    }
}

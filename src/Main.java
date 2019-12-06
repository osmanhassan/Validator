import Pojo.User;
import Validation.Settings.ValidatorCustomSettings;
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
        rulesByFields.put("name", "bail | date_after: 12/03/2019, MM/dd/yyyy");
        User user = new User();
        user.setName("12/04/2019");
        user.setEmail("has@g.cp");

        Validator validator = new Validator(user, rulesByFields, new ValidatorCustomSettings());
        System.out.println(validator.validate());


    }
}

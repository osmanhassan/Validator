package Validation.Validator;

import Validation.ValidationDecoratots.DefaultValidationDecorator;
import Validation.ValidationDecoratots.ValidationDecorator;

import java.lang.reflect.Constructor;
import java.util.*;

public class ValidationExecutor<T> {

    HashMap<String, Class> validatorClassRegistry;
    T subjectObject;
    LinkedHashMap rulesByField;
    HashMap settings;
    HashMap errorMessages;

    public ValidationExecutor(Validator<T> validator){

        this.validatorClassRegistry = validator.validatorClassRegistry;
        this.subjectObject = validator.subjectObject;
        this.rulesByField = validator.rulesByField;
        this.settings = validator.settings;
        this.errorMessages = validator.errorMessages;
    }

    public String validate() throws IllegalAccessException {

        LinkedHashMap rulesByField = this.rulesByField;

        final String[] errorMessage = {""};

        rulesByField.forEach((key, value) -> {
            String fieldName = key.toString();
            String rulesString = value.toString();
            rulesString = "default | " + rulesString;
            String[] rules = rulesString.split("\\|");

            try {
                errorMessage[0] += validateSingleField(fieldName, rules);

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        return errorMessage[0];

    }

    public String validateSingleField(String subjectFieldName, String[] rules) throws Exception {

        Class[] validatorClasses = new Class[rules.length];
        String[] additionalDataOfRules = new String[rules.length];
        String[] ruleNames = new String[rules.length];

        int count = 0;
        for(String rule : rules){
            rule = rule.trim();
            String[] partsOfRule = rule.split("\\:");
            String ruleName = partsOfRule[0].trim().toLowerCase();

            String additionalDataOfRule = "";

            if(partsOfRule.length > 1) {
                additionalDataOfRule = partsOfRule[1];
            }

            Class validatorClass = this.validatorClassRegistry.get(ruleName);
            validatorClasses[count] = validatorClass;
            additionalDataOfRules[count] = additionalDataOfRule.trim();
            ruleNames[count] = ruleName;

            count++;
        }

        ValidationDecorator<T> validationDecorator = new DefaultValidationDecorator<T>(null, "", this.settings, "");

        for(int i = validatorClasses.length - 1; i >= 0; i --){

            Class cls = validatorClasses[i];
            String additionalDataOfRule = additionalDataOfRules[i];
            String ruleName = ruleNames[i];

            Constructor constructor = cls.getConstructor(ValidationDecorator.class, String.class);
            validationDecorator = (ValidationDecorator<T>) constructor.newInstance(validationDecorator, additionalDataOfRule, ruleName);
        }


        return validationDecorator.validate(this.subjectObject, subjectFieldName, errorMessages);
    }

}

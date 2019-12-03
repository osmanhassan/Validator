package Validation.Validator;

import Validation.ValidationDecoratots.DefaultValidationDecorator;
import Validation.ValidationDecoratots.ValidationDecorator;
import Validation.ValidatorClassRegister;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;

public class Validator<T> {

    ValidatorClassRegister validatorClassRegister;
    T subjectObject;
    LinkedHashMap rulesByField;
    HashMap settings;

    public Validator(T subjectObject, LinkedHashMap rulesByField){

        this.subjectObject = subjectObject;
        this.rulesByField = rulesByField;
        this.validatorClassRegister = new ValidatorClassRegister();
        setOwnSettings();

    }

    public Validator(T subjectObject, LinkedHashMap rulesByField, HashMap settings){

        this.subjectObject = subjectObject;
        this.rulesByField = rulesByField;
        this.validatorClassRegister = new ValidatorClassRegister();
        setOwnSettings();
        settings.forEach((key, value) -> {
            this.settings.put(key, value);
        });
    }

    public void setOwnSettings(){
        this.settings = new HashMap();

        this.settings.put("default.date.format", "yyyy-MM-dd hh:mm:ss");

    }

    public String validate() throws IllegalAccessException {

        LinkedHashMap rulesByField = this.rulesByField;
        HashMap validatorClassRegistry = this.validatorClassRegister.getValidatorClassRegistry();
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

        int count = 0;
        for(String rule : rules){
            rule = rule.trim();
            String[] partsOfRule = rule.split("\\:");
            String ruleName = partsOfRule[0].trim().toLowerCase();

            String additionalDataOfRule = "";

            if(partsOfRule.length > 1) {
                additionalDataOfRule = partsOfRule[1];
            }

            String className = this.validatorClassRegister.getValidatorClassRegistry().get(ruleName).toString();
            Class validatorClass = Class.forName(className);
            validatorClasses[count] = validatorClass;
            additionalDataOfRules[count] = additionalDataOfRule.trim();

            count++;
        }

        ValidationDecorator<T> validationDecorator = new DefaultValidationDecorator<T>(null, "", this.settings);

        for(int i = validatorClasses.length - 1; i >= 0; i --){

            Class cls = validatorClasses[i];
            String additionalDataOfRule = additionalDataOfRules[i];
            Constructor constructor = cls.getConstructor(ValidationDecorator.class, String.class);
            validationDecorator = (ValidationDecorator<T>) constructor.newInstance(validationDecorator, additionalDataOfRule);
        }


        return validationDecorator.validate(this.subjectObject, subjectFieldName);
    }

}

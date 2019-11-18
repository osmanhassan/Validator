package Validation.Validator;

import Validation.ValidationDecoratots.DefaultValidationDecorator;
import Validation.ValidationDecoratots.EmailValidationDecorator;
import Validation.ValidationDecoratots.RequiredValidationDecorator;
import Validation.ValidationDecoratots.ValidationDecorator;
import Validation.ValidatorClassRegister;
import sun.security.jca.GetInstance;

import java.lang.reflect.Constructor;
import java.util.*;

public class Validator<T> {

    ValidatorClassRegister validatorClassRegister;
    T subjectObject;
    LinkedHashMap rulesByField;


    public Validator(T subjectObject, LinkedHashMap rulesByField){

        this.subjectObject = subjectObject;
        this.rulesByField = rulesByField;
        this.validatorClassRegister = new ValidatorClassRegister();

    }

    public String validate(){

        LinkedHashMap rulesByField = this.rulesByField;
        HashMap validatorClassRegistry = this.validatorClassRegister.getValidatorClassRegistry();

        final String[] errorMessage = {""};

        rulesByField.forEach((key, value) -> {
            String fieldName = key.toString();
            String rulesString = value.toString();

            String[] ruleNames = rulesString.split("\\|");

            try {
                errorMessage[0] += validateSingleField(fieldName,
                        new ArrayList<>(rulesByField.keySet()), ruleNames);

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        return errorMessage[0];

    }

    public String validateSingleField(String subjectFieldName, List<String> allFieldsName, String[] rulesName) throws Exception {

        List<Class> validatorClasses = new ArrayList<>();

        for(String ruleName : rulesName){
            ruleName = ruleName.trim().toLowerCase();
            String className = this.validatorClassRegister.getValidatorClassRegistry().get(ruleName).toString();
            Class validatorClass = Class.forName(className);
            validatorClasses.add(validatorClass);
        }

        ValidationDecorator<T> validationDecorator = new DefaultValidationDecorator<T>();

        for(int i = validatorClasses.size() - 1; i >= 0; i --){

            Class cls = validatorClasses.get(i);
            Constructor constructor = cls.getConstructor(ValidationDecorator.class);
            validationDecorator = (ValidationDecorator<T>) constructor.newInstance(validationDecorator);
        }


        return validationDecorator.validate(this.subjectObject, allFieldsName,subjectFieldName);
    }

}

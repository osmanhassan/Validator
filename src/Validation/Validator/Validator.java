package Validation.Validator;

import Validation.Register.IValidatorClassRegister;
import Validation.Settings.IValidatorSettings;
import Validation.Settings.ValidatorDefaultSettings;
import Validation.Register.ValidatorDefaultClassRegister;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Validator<T> {
    HashMap<String, Class> validatorClassRegistry;
    T subjectObject;
    LinkedHashMap rulesByField;
    HashMap settings;
    HashMap errorMessages;

    public Validator(T subjectObject, LinkedHashMap rulesByField){
        this.subjectObject = subjectObject;
        this.rulesByField = rulesByField;
        this.validatorClassRegistry = new ValidatorDefaultClassRegister().registry();
        this.settings = new ValidatorDefaultSettings().settings();
    }

    public Validator settings(IValidatorSettings validatorSettings) {
        this.settings.putAll(validatorSettings.settings());
        return this;
    }

    public Validator errorMessages(HashMap errorMessages) {
        this.errorMessages = errorMessages;
        return this;
    }

    public Validator validatorClassRegister(IValidatorClassRegister validatorClassRegister) {
        validatorClassRegister.registry().forEach((key, value)->{
            this.validatorClassRegistry.computeIfAbsent(key, k->value);
        });
        return this;
    }

    public String validate(){
        ValidationExecutor validationExecutor = new ValidationExecutor(this);
        try {
           return validationExecutor.validate();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return "";
    }
}

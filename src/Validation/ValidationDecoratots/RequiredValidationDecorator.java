package Validation.ValidationDecoratots;

import java.util.List;

public class RequiredValidationDecorator<T> extends ValidationDecorator<T> {

    ValidationDecorator validationDecorator;

    public RequiredValidationDecorator(ValidationDecorator validationDecorator){
        this.validationDecorator = validationDecorator;
    }

    @Override
    public String validate(T o, List<String> allFieldNames, String subjectFieldName) throws Exception {

        char[] subjectFieldNameCharArray = subjectFieldName.toCharArray();
        subjectFieldNameCharArray[0] = Character.toUpperCase(subjectFieldNameCharArray[0]);

        String getFieldMethodName = "get" + new String(subjectFieldNameCharArray) ;

        if(o.getClass().getMethod(getFieldMethodName).invoke(o) == null){
            return new String(subjectFieldNameCharArray)  + " is required " + this.validationDecorator.validate(o, allFieldNames, subjectFieldName);
        }
        else
            return this.validationDecorator.validate(o, allFieldNames, subjectFieldName);


    }
}

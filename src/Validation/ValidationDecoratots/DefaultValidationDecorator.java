package Validation.ValidationDecoratots;

import java.lang.reflect.Method;
import java.util.List;


public class DefaultValidationDecorator<T> extends ValidationDecorator<T> {
    @Override
    public String validate(T o, String additionalDataOfRule, String subjectFieldName) throws Exception {

        Method method = getMethodFromFieldName(o, subjectFieldName);

        if(method.invoke(o) == null){
            setIsNull(true);
        }
        else{
            setIsNull(false);
        }

        return "";
    }
}

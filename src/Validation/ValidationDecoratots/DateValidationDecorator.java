package Validation.ValidationDecoratots;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidationDecorator<T> extends ValidationDecorator<T> {

    public DateValidationDecorator(ValidationDecorator validationDecorator, String validationAdditionalInfo, String ruleName) {
        super(validationDecorator, validationAdditionalInfo, ruleName);
    }

    @Override
    public boolean isValid(T o, String subjectFieldName) throws Exception {

        String value = "";
        String defaultFormat = settings.get("default.date.format").toString().trim();

        if (!getIsNull()) {

            value = getFieldValue(o, subjectFieldName).trim();

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(defaultFormat);

            if(! validationAdditionalInfo.trim().equals("")){
                try {
                    dateTimeFormatter = DateTimeFormatter.ofPattern(validationAdditionalInfo.trim());
                }
                catch (Exception e){
                   // e.printStackTrace();
                }
            }

            try {
                LocalDate.parse(value, dateTimeFormatter);
                return true;
            } catch (DateTimeParseException e) {
                return false;
            }

        }

       return true;
    }
}

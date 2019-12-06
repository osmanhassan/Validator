package Validation.ValidationDecoratots;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidationDecorator<T> extends ValidationDecorator<T> {

    public DateValidationDecorator(ValidationDecorator validationDecorator, String validationAdditionalInfo) {
        super(validationDecorator, validationAdditionalInfo);
    }

    @Override
    public String validate(T o, String subjectFieldName) throws Exception {

        boolean isValidationFailed = false;


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
            } catch (DateTimeParseException e) {
                isValidationFailed = true;
            }

        }

        boolean isFailed = isValidationFailed && isBail;
        String message = value + " is not a valid date. Date format must be ";
        if(! validationAdditionalInfo.trim().equals("")) {
            message = message + validationAdditionalInfo.trim();
        }
        else{
            message = message + defaultFormat;
        }


        if(isFailed)
            return message;

        String validationPassedString = validationDecorator.validate(o, subjectFieldName);
        String validationFailedString = message + validationPassedString;

        if(isValidationFailed)
            return validationFailedString;

        return validationPassedString;
    }
}

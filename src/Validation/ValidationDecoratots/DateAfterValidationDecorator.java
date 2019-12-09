package Validation.ValidationDecoratots;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateAfterValidationDecorator<T> extends ValidationDecorator<T> {
    public DateAfterValidationDecorator(ValidationDecorator validationDecorator, String validationAdditionalInfo, String ruleName) {
        super(validationDecorator, validationAdditionalInfo, ruleName);
    }

    public DateTimeFormatter getDateTimeFormatter(String defaultFormat, String newFormat){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(defaultFormat);
        try {
            dateTimeFormatter = DateTimeFormatter.ofPattern(newFormat);
        }
        catch (Exception e){
            // e.printStackTrace();
        }
        return dateTimeFormatter;
    }

    @Override
    public boolean isValid(T o, String subjectFieldName) throws Exception {

        String dateString1 = "?";
        String dateString2 = "?";

        String defaultFormat = settings.get("default.date.format").toString();

        if (!getIsNull()) {

            dateString1 = getFieldValue(o, subjectFieldName).trim();

            DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern(defaultFormat);
            DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern(defaultFormat);

            if(! validationAdditionalInfo.trim().equals("")){

                String[] validationMeta = validationAdditionalInfo.trim().split(",");

                if(validationMeta.length == 3){
                    dateTimeFormatter1 = getDateTimeFormatter(defaultFormat, validationMeta[1].trim());
                    dateTimeFormatter2 = getDateTimeFormatter(defaultFormat, validationMeta[2].trim());
                }
                else if(validationMeta.length == 2){
                    dateTimeFormatter1 = getDateTimeFormatter(defaultFormat, validationMeta[1].trim());
                    dateTimeFormatter2 = dateTimeFormatter1;
                }

                dateString2 = validationMeta[0].trim();

            }

            try {

                LocalDate date1 = LocalDate.parse(dateString1, dateTimeFormatter1);
                LocalDate date2 = LocalDate.parse(dateString2, dateTimeFormatter2);

                if(date1.compareTo(date2) <= 0){
                    return false;
                }
                return true;

            } catch (DateTimeParseException e) {
                return false;
            }

        }

        return true;

    }
}

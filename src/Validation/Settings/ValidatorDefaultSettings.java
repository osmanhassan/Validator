package Validation.Settings;

import java.util.HashMap;

public class ValidatorDefaultSettings implements IValidatorSettings {
    @Override
    public HashMap settings() {
        HashMap settings = new HashMap();

        settings.put("default.date.format", "yyyy-MM-dd hh:mm:ss");

        return settings;
    }
}

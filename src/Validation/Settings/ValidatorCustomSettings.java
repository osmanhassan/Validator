package Validation.Settings;

import java.util.HashMap;

public class ValidatorCustomSettings implements IValidatorSettings {
    @Override
    public HashMap settings() {
        HashMap settings = new HashMap();

        settings.put("default.date.format", "yyyy-MM-dd");
        settings.put("default.date", "yyyy-MM-dd");

        return settings;
    }
}

package ui.utilities.validators;

import ui.components.forms.FormField;
import ui.utilities.FieldValidator;

public class PatternValidator extends FieldValidator {
    private String patternString;

    public PatternValidator(FormField field, String patternString) {
        super(field);
        this.patternString = patternString;
    }

    @Override
    public boolean validatorFunction() {
        return field.getValue().matches(patternString);
    }

    @Override
    public String getErrorMessage() {
        return "Invalid input";
    }

}

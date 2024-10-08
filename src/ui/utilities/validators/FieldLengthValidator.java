package ui.utilities.validators;

import ui.components.forms.FormField;
import ui.utilities.FieldValidator;

public class FieldLengthValidator extends FieldValidator {
    private int minLength;
    private int maxLength;

    public FieldLengthValidator(FormField field, int minLength, int maxLength) {
        super(field);
        this.minLength = minLength;

    }

    public FieldLengthValidator(FormField field, int minLength) {
        super(field);

        this.minLength = minLength;
    }

    @Override
    public boolean validatorFunction() {
        if (field.getValue().isEmpty()) return true;
        if (maxLength == 0) {
            return field.getValue().length() >= minLength;
        }
        return field.getValue().length() >= minLength && field.getValue().length() <= maxLength;
    }

    @Override
    public String getErrorMessage() {
        if (maxLength == 0) {
            return field.getLabel() + " must be at least " + minLength + " characters long";
        }
        return field.getLabel() + " must be between " + minLength + " and " + maxLength + " characters long";
    }

}

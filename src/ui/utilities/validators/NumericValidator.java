package ui.utilities.validators;

import ui.components.forms.FormField;
import ui.utilities.FieldValidator;

public class NumericValidator extends FieldValidator {
    Number model;

    public NumericValidator(FormField field) {
        super(field);
    }

    public NumericValidator(FormField field, Number model) {
        super(field);
        this.model = model;
    }

    @Override
    public boolean validatorFunction() {
        if (field.getValue().isEmpty())
            return true;
        try {
            if (model instanceof Integer || model instanceof Long) {
                Long.parseLong(field.getValue());
            } else
                Double.parseDouble(field.getValue());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String getErrorMessage() {
        if (model instanceof Integer)
            return field.getLabel() + " must be a whole number.";
        return field.getLabel() + " must be a number";
    }

}

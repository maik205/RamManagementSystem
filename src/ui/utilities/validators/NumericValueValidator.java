package ui.utilities.validators;

import ui.components.forms.FormField;
import ui.utilities.FieldValidator;

public class NumericValueValidator extends FieldValidator {
    private final double minValue;
    private final double maxValue;

    public NumericValueValidator(FormField field, int minValue, int maxValue) {
        this(field, (double) minValue, (double) maxValue);
    }

    public NumericValueValidator(FormField field, double minValue, double maxValue) {
        super(field);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public NumericValueValidator(FormField field, double minValue) {
        super(field);
        this.minValue = minValue;
        this.maxValue = Double.MIN_VALUE;
    }

    public NumericValueValidator(FormField field, int minValue) {
        this(field, (double) minValue);
    }

    @Override
    public boolean validatorFunction() {
        if (field.getValue().isEmpty())
            return true;
        try {
            double value = Double.parseDouble(field.getValue());
            if (value < minValue) {
                return false;
            }
            if (maxValue != Double.MIN_VALUE && value > maxValue) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    @Override
    public String getErrorMessage() {
        if (this.maxValue == Double.MIN_VALUE) {
            return String.format("The value must be above %.0f", this.minValue);
        }
        return String.format("Value must be between %.0f and %.0f", this.minValue, this.maxValue);
    }

}

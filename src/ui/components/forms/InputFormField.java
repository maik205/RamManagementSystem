package ui.components.forms;

import ui.UIController;
import ui.utilities.Colorizer;
import ui.utilities.FieldValidator;
import ui.utilities.fieldrendering.FormFieldValueRenderingContext;
import ui.utilities.fieldrendering.strategies.InputFormFieldRenderingStrategy;

public class InputFormField extends FormField<String> {

    public InputFormField(String label, String value, FieldValidator[] validators) {
        super(label, value, validators);
    }

    public InputFormField(String label, String value) {
        super(label, value);
    }

    @Override
    public String toString() {
        try {
            StringBuilder result = new StringBuilder();
            result.append(this.renderTitleString());

            result.append(
                    renderValueString());
            for (FieldValidator validator : validators) {
                if (!validator.validatorFunction()) {
                    result.append('\n' + Colorizer.colorize(validator.getErrorMessage(), "red"));
                }
            }
            return result.toString();
        } catch (Exception e) {
            return "You messed up the colors";
        }
    }

    @Override
    public void update(Character value) {
        this.setValue(UIController.input.getBuffer());
    }

    @Override
    public String renderValueString() {
        FormFieldValueRenderingContext ctx = new FormFieldValueRenderingContext(new InputFormFieldRenderingStrategy());
        return ctx.renderFormField(this);
    }

}

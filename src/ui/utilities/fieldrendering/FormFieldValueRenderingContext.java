package ui.utilities.fieldrendering;

import ui.components.forms.FormField;

public class FormFieldValueRenderingContext {
    private FormFieldValueRenderingStrategy renderingStrategy;

    public FormFieldValueRenderingContext(FormFieldValueRenderingStrategy renderingStrategy) {
        this.renderingStrategy = renderingStrategy;
    }

    public String renderFormField(FormField formField) {
        return renderingStrategy.renderFormFieldValue(formField);
    }
}

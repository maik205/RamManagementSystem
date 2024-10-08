package ui.utilities.fieldrendering;

import ui.components.forms.FormField;

public interface FormFieldValueRenderingStrategy<T> {

    String renderFormFieldValue(FormField<T> formField);
}

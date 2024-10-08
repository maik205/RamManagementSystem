package ui.utilities.fieldrendering.strategies;

import java.util.List;

import ui.components.forms.FormField;
import ui.exceptions.InvalidColorException;
import ui.utilities.Colorizer;
import ui.utilities.StringUtils;
import ui.utilities.fieldrendering.FormFieldValueRenderingStrategy;

public class InputFormFieldRenderingStrategy implements FormFieldValueRenderingStrategy<String> {

    @Override
    public String renderFormFieldValue(FormField<String> formField) {
        StringBuilder sb = new StringBuilder();
        List<String> splitStrings = StringUtils.splitLongString(formField.getValue());

        // System.out.println(splitStrings[0]);
        String valueColor = formField.getEditing() ? "black" : "white";
        String valueBackground = formField.getEditing() ? "white" : "black";

        for (int i = 0; i < splitStrings.size(); i++) {
            sb.append(StringUtils.getLabelPadding(i == 0 ? formField.getLabel() : ""));
            try {
                sb.append(
                        Colorizer.colorize(formField.getValue().length() == 0 ? "<Empty>" : splitStrings.get(i),
                                valueColor,
                                valueBackground));
            } catch (InvalidColorException e) {
                e.printStackTrace();
            }
            if (i < splitStrings.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

}

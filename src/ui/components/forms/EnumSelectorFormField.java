package ui.components.forms;

import ui.utilities.Colorizer;
import ui.utilities.FieldValidator;
import ui.utilities.StringUtils;

public class EnumSelectorFormField<T extends Enum<T>> extends FormField<Enum<T>> {

    private int selectedIndex = 0;

    public EnumSelectorFormField(String label, Enum<T> value, FieldValidator[] validators) {
        super(label, value, validators);
    }

    public EnumSelectorFormField(String label, Enum<T> value) {
        super(label, value);
    }

    @Override
    public String renderValueString() {
        Enum<T>[] enums = this.value.getClass().getEnumConstants();
        StringBuilder result = new StringBuilder();
        result.append(StringUtils.getLabelPadding(this.getLabel()));
        for (Enum<?> e : enums) {
            if (e.ordinal() == selectedIndex) {
                result.append(Colorizer.colorize("[" + e.name() + "]", "blue"));
            } else {
                result.append("[" + e.name() + "]");
            }
            result.append(" ");
        }
        return result.toString();

    }

    @Override
    public void update(Character value) {
        if (Character.toLowerCase(value) == 'd') {
            this.selectedIndex++;
            if (this.selectedIndex == this.value.getClass().getEnumConstants().length) {
                this.selectedIndex = 0;
            }
        }
        if (Character.toLowerCase(value) == 'a') {
            this.selectedIndex--;
            if (this.selectedIndex == -1) {
                this.selectedIndex = this.value.getClass().getEnumConstants().length - 1;
            }
        }
    }
}

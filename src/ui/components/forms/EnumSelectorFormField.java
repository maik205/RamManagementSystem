package ui.components.forms;

import ui.utilities.Colorizer;
import ui.utilities.FieldValidator;
import ui.utilities.StringUtils;

public class EnumSelectorFormField<T extends Enum<T>> extends FormField<Enum<T>> {

    private int selectedIndex = 0;
    @SuppressWarnings("unchecked")
    private final Enum<T>[] enums = this.value.getClass().getEnumConstants();

    public EnumSelectorFormField(String label, Enum<T> value, FieldValidator[] validators) {
        super(label, value, validators);
    }

    public EnumSelectorFormField(String label, Enum<T> value) {
        super(label, value);
    }

    @Override
    public String renderValueString() {
        StringBuilder result = new StringBuilder();
        result.append(StringUtils.getLabelPadding(this.getLabel()));
        for (Enum<T> e : enums) {
            if (e == enums[selectedIndex]) {
                result.append(Colorizer.colorize("[" + e + "]", "blue"));
            } else {
                result.append("[" + e + "]");
            }
            result.append(" ");
        }
        return result.toString();

    }

    @Override
    public Enum<T> getValue() {
        return enums[selectedIndex];
    }

    @Override
    public void setValue(Enum<T> value) {
        for (int i = 0; i < enums.length; i++) {
            if (enums[i] == value) {
                this.selectedIndex = i;
                break;
            }
        }
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

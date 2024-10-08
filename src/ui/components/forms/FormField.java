package ui.components.forms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ui.utilities.Colorizer;
import ui.utilities.FieldValidator;
import utils.Observer;

public abstract class FormField<T> implements Observer<Character> {
    protected String label;
    protected T value;
    protected boolean isEditing = false;
    protected boolean isSelecting = false;
    protected final List<FieldValidator> validators = new ArrayList<>();

    public FormField(String label, T value, FieldValidator... validators) {
        this.label = label;
        this.value = value;
        this.setValidators(validators);
    }

    public void setValidators(FieldValidator... validators) {
        this.validators.addAll(Arrays.asList(validators));
    }

    public String getLabel() {
        return this.label;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setEditing(boolean isEditing) {
        this.isEditing = isEditing;
    }

    public void setSelecting(boolean isSelecting) {
        this.isSelecting = isSelecting;
    }

    public boolean getEditing() {
        return this.isEditing;
    }

    public boolean getSelecting() {
        return this.isSelecting;
    }

    public boolean isValid() {
        if (this.value == null || this.value.equals(""))
            return false;
        for (FieldValidator fv : validators) {
            if (!fv.validatorFunction())
                return false;
        }
        return true;
    }

    public String renderTitleString() {
        StringBuilder result = new StringBuilder();
        String labelColor = isSelecting ? "black" : "white";
        String labelBackground = isSelecting ? (isEditing ? "yellow" : "white") : "black";
        result.append(Colorizer.colorize(this.label, labelColor, labelBackground));
        return result.toString();

    }

    public abstract String renderValueString();

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.renderTitleString());
        result.append(this.renderValueString());
        for (FieldValidator validator : validators) {
            if (!validator.validatorFunction()) {
                result.append('\n' + Colorizer.colorize(validator.getErrorMessage(), "red"));
            }
        }
        return result.toString();
    }
}

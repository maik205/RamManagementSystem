package ui.components.forms;

import java.util.ArrayList;
import java.util.List;

import rammanagementsystem.exceptions.InvalidFormatException;
import rammanagementsystem.interfaces.Queriable;
import ui.Router;
import ui.UIController;
import ui.exceptions.InvalidColorException;
import ui.routes.Route;
import ui.utilities.Colorizer;

import utils.RouteDescriptor;

public abstract class Form<T extends Queriable> extends Route {
    protected final List<FormField<?>> fields = new ArrayList<>();
    protected boolean isEditing = false;
    protected Route prevRoute;
    private int currentSelectedFieldIndex = 0;
    private String acceptMessage = "Submit";

    public void setAcceptMessage(String acceptMessage) {
        this.acceptMessage = acceptMessage;
    }

    private String cancelMessage = "Cancel";

    public void setCancelMessage(String cancelMessage) {
        this.cancelMessage = cancelMessage;
    }

    public Form(RouteDescriptor routeDescriptor, Router router, Route prevRoute) {
        super(routeDescriptor, router);
        this.prevRoute = prevRoute;
        initializeForm();
    }

    @Override
    public void update(Character keyDown) {
        int keyDownInt = (int) keyDown;
        if (keyDownInt == 13) {
            if (currentSelectedFieldIndex == fields.size()) {
                submitForm();
                return;
            }
            if (currentSelectedFieldIndex == fields.size() + 1) {
                cancelForm();
                return;
            }
            if (isEditing) {
                this.unsubscribe(this.fields.get(currentSelectedFieldIndex));
            } else {
                UIController.input.clearBuffer();
                this.subscribe(this.fields.get(currentSelectedFieldIndex));
            }
            this.isEditing = !this.isEditing;
        }
        if (isEditing == false) {
            if (Character.toLowerCase(keyDown) == 'w') {
                currentSelectedFieldIndex--;
                if (currentSelectedFieldIndex == -1) {
                    currentSelectedFieldIndex = fields.size() + 1;
                }
            } else if (Character.toLowerCase(keyDown) == 's') {
                currentSelectedFieldIndex++;
                if (currentSelectedFieldIndex == fields.size() + 2) {
                    currentSelectedFieldIndex = 0;
                }
            }
        } else {
            this.set(keyDown);
        }
    }

    /**
     * Render the form
     * 
     * @return the form as a string
     */
    public String renderForm() {
        StringBuilder sb = new StringBuilder();
        fields.forEach(field -> {
            field.setEditing(isEditing && fields.indexOf(field) == currentSelectedFieldIndex);
            field.setSelecting(fields.indexOf(field) == currentSelectedFieldIndex);
            sb.append(field).append('\n');
        });
        sb.append(renderOption(acceptMessage, this.fields.size()));
        sb.append(renderOption(cancelMessage, this.fields.size() + 1));
        return sb.toString();
    }

    private String renderOption(String message, int index) {
        boolean isSelected = index == currentSelectedFieldIndex;
        try {
            return Colorizer.colorize(message, isSelected ? "black" : "white", isSelected ? "white" : "black") + "\n";
        } catch (InvalidColorException e) {
            return message;
        }

    }

    public boolean isValid() {
        for (FormField<?> field : this.fields) {
            if (!field.isValid())
                return false;
        }
        return true;
    }

    public abstract void submitForm();

    public abstract T parseForm() throws InvalidFormatException;

    /**
     * Initialize and create the form fields
     */
    public abstract void initializeForm();

    /**
     * Submit the form and returns the appropriate data
     * 
     * @return the data from the form
     */

    public abstract void cancelForm();
}

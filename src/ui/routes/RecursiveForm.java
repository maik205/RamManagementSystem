package ui.routes;

import rammanagementsystem.exceptions.InvalidFormatException;
import rammanagementsystem.interfaces.Queriable;
import ui.Router;
import ui.components.forms.Form;
import utils.RouteDescriptor;

public class RecursiveForm extends Form {

    public RecursiveForm(RouteDescriptor routeDescriptor, Router router, Route prevRoute) {
        super(routeDescriptor, router, prevRoute);
    }

    @Override
    public void initializeForm() {
    }

    @Override
    public void submitForm() {
        if (prevRoute instanceof Form) {
            ((Form) prevRoute).initializeForm();
            router.navigate(prevRoute);
        }
    }

    @Override
    public void cancelForm() {
        router.navigate(new MainMenu(router));
    }

    @Override
    public String renderRouteContent() {
        return this.renderForm();
    }

    @Override
    public Queriable parseForm() throws InvalidFormatException {
        return null;
    }

}

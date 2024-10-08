package ui.routes;

import rammanagementsystem.exceptions.InvalidFormatException;
import rammanagementsystem.exceptions.InvalidItemException;
import rammanagementsystem.services.DataServiceProvider;
import ui.Router;
import utils.RouteDescriptor;

public class AddProductForm extends ProductForm {

    public AddProductForm(Router router) {
        super(router);
    }

    @Override
    protected String provideUID() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public void submitForm() {
        try {
            if (this.isValid())
                DataServiceProvider.ramItemsDataService.addItem(this.parseForm());
            else
                return;
            Router.setMotd("Product added successfully.");
            this.router.navigate(new RecursiveForm(
                    new RouteDescriptor("Continue?", "Would you like to add another product?"),
                    router,
                    this));
        } catch (InvalidFormatException e) {
            Router.setMotd("Invalid format. Please try again.");
        } catch (InvalidItemException e) {
            Router.setMotd(e.getMessage());
        }

    }

}

package ui.routes;

import rammanagementsystem.exceptions.InvalidFormatException;
import ui.Router;
import ui.components.forms.ProductQueryActionForm;
import utils.constants.StringConstants;

public class UpdateQuery extends ProductQueryActionForm {

    public UpdateQuery(Router router, Route prevRoute) {
        super(
                StringConstants.ROUTE_DESCRIPTOR.get((short) 2), router,
                prevRoute);
    }

    @Override
    public void submitForm() {
        try {
            this.router.navigate(new UpdateProductForm(router, this, parseForm()));
        } catch (InvalidFormatException e) {
            Router.setMotd("Invalid product ID.");
        }
    }

}

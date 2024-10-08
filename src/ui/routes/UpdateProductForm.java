package ui.routes;

import rammanagementsystem.exceptions.InvalidFormatException;
import rammanagementsystem.models.RamItem;
import rammanagementsystem.services.DataServiceProvider;

import ui.Router;
import utils.RouteDescriptor;

public class UpdateProductForm extends ProductForm {

    private RamItem product;

    public UpdateProductForm(Router router, Route prevRoute, RamItem product) {
        super(router, prevRoute);
        this.product = product;
        this.setProduct(product);

    }

    @Override
    protected String provideUID() {
        return this.product.getId();

    }

    @Override
    public void submitForm() {
        try {
            DataServiceProvider.ramItemsDataService.put(this.provideUID(),
                    this.parseForm());
        } catch (InvalidFormatException e) {
            Router.setMotd(e.getMessage());
        }
        Router.setMotd("RamItem updated succesfully.");
        this.router.navigate(new RecursiveForm(
                new RouteDescriptor(this.routeDescriptor.getRouteName(), "Would you like to update another product?"),
                router, prevRoute));
    }

}

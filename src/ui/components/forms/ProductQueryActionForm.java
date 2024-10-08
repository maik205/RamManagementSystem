package ui.components.forms;

import rammanagementsystem.exceptions.InvalidFormatException;
import rammanagementsystem.models.RamItem;
import rammanagementsystem.services.DataServiceProvider;
import ui.Router;
import ui.routes.Route;
import ui.routes.Search;
import ui.utilities.validators.LinkValidator;
import utils.RouteDescriptor;

public abstract class ProductQueryActionForm extends Search {

    public ProductQueryActionForm(RouteDescriptor rd, Router router, Route prevRoute) {
        super(
                rd, router, prevRoute);

    }

    @Override
    public abstract void submitForm();

    @Override
    public RamItem parseForm() throws InvalidFormatException {
        try {
            RamItem prod = DataServiceProvider.ramItemsDataService.get(this.fields.get(2).getValue());
            if (prod == null)
                throw new InvalidFormatException();
            return prod;
        } catch (Exception e) {
            throw new InvalidFormatException("Item doesn't exist.");
        }
    }

    @Override
    public void initializeForm() {
        super.initializeForm();
        this.setAcceptMessage("Continue");
        this.setCancelMessage("Return");
        this.fields.add(
                new InputFormField("ID", ""));
        this.fields.get(1).setValidators(
                new LinkValidator<RamItem>((FormField<String>) fields.get(2), DataServiceProvider.ramItemsDataService));
    }

}
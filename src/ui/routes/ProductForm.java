package ui.routes;

import rammanagementsystem.exceptions.InvalidFormatException;
import rammanagementsystem.models.RamItem;
import rammanagementsystem.models.RamType;
import ui.Router;
import ui.components.forms.EnumSelectorFormField;
import ui.components.forms.Form;
import ui.components.forms.FormField;
import ui.components.forms.InputFormField;
import ui.utilities.validators.FieldLengthValidator;
import ui.utilities.validators.NumericValidator;
import ui.utilities.validators.NumericValueValidator;

import utils.constants.StringConstants;

import java.time.Year;

public abstract class ProductForm extends Form<RamItem> {

    public ProductForm(Router router) {
        super(StringConstants.ROUTE_DESCRIPTOR.get((short) 1), router, null);
    }

    public ProductForm(Router router, Route prevRoute) {
        super(StringConstants.ROUTE_DESCRIPTOR.get((short) 1), router, prevRoute);
    }

    @Override
    public String renderRouteContent() {
        return this.renderForm();
    }

    @Override
    public void initializeForm() {
        FormField<String> name = new InputFormField("Name", "");
        name.setValidators(
                new FieldLengthValidator(name, 5));
        this.fields.add(name);

        FormField<Enum<RamType>> type = new EnumSelectorFormField<RamType>("Type", RamType.DDR4);
        this.fields.add(type);
    }

    @Override
    public RamItem parseForm() throws InvalidFormatException {
        if (!this.isValid()) throw new InvalidFormatException("Invalid form data.");
        
        return null;
    }

    @Override
    public void cancelForm() {
        router.navigate(new MainMenu(router));
    }

    protected void setProduct(RamItem productData) {
        // TODO: Implement this method

        // this.fields.get(0).setValue(productData.getName());
        // this.fields.get(1).setValue(productData.getBrandId());
        // this.fields.get(2).setValue(productData.getCategoryId());
        // this.fields.get(3).setValue(Short.toString(productData.getModelYear()));
        // this.fields.get(4).setValue(Long.toString(productData.getListPrice()));
    }

    protected abstract String provideUID();

    public abstract void submitForm();

}

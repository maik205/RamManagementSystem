package ui.routes;

import java.time.YearMonth;

import rammanagementsystem.exceptions.InvalidFormatException;
import rammanagementsystem.models.RamItem;
import rammanagementsystem.models.enums.BooleanEnum;
import rammanagementsystem.models.enums.RamType;
import rammanagementsystem.services.DataServiceProvider;
import ui.Router;
import ui.components.forms.EnumSelectorFormField;
import ui.components.forms.Form;
import ui.components.forms.FormField;
import ui.components.forms.InputFormField;

import ui.utilities.validators.FieldLengthValidator;
import ui.utilities.validators.NumericValidator;
import ui.utilities.validators.PatternValidator;
import utils.constants.StringConstants;

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

        FormField<Enum<RamType>> type = new EnumSelectorFormField<RamType>("Type", RamType.DDR4);
        this.fields.add(type);

        FormField<String> busSpeed = new InputFormField("Bus Speed", "");
        busSpeed.setValidators(new NumericValidator(busSpeed));
        this.fields.add(busSpeed);

        FormField<String> brand = new InputFormField("Brand", "");
        brand.setValidators(new FieldLengthValidator(brand, 1, 20));
        this.fields.add(brand);

        FormField<String> quantity = new InputFormField("Quantity", "");
        quantity.setValidators(new NumericValidator(quantity));
        this.fields.add(quantity);

        FormField<String> monthYearOfProduction = new InputFormField("Produced on", "2024-12");
        monthYearOfProduction.setValidators(
                new FieldLengthValidator(monthYearOfProduction, 7, 7),
                new PatternValidator(monthYearOfProduction, "\\d{4}-\\d{2}"));
        this.fields.add(monthYearOfProduction);

        FormField<Enum<BooleanEnum>> isActive = new EnumSelectorFormField<BooleanEnum>("Is Active", BooleanEnum.TRUE);
        this.fields.add(isActive);
    }

    @Override
    public RamItem parseForm() throws InvalidFormatException {
        if (!this.isValid())
            throw new InvalidFormatException("Invalid form data.");
        try {
            RamItem item = new RamItem(
                    DataServiceProvider.ramItemsDataService.provideNewUID((RamType) this.fields.get(0).getValue()),
                    (RamType) this.fields.get(0).getValue(),
                    Integer.parseInt(this.fields.get(1).getValue().toString()),
                    this.fields.get(2).getValue().toString(),
                    Integer.parseInt(this.fields.get(3).getValue().toString()),
                    YearMonth.parse(this.fields.get(4).getValue().toString()),
                    this.fields.get(3).getValue() == BooleanEnum.TRUE ? true : false);
            return item;
        } catch (Exception e) {
            throw new InvalidFormatException("Invalid form data." + e.getMessage());
        }
    }

    @Override
    public void cancelForm() {
        router.navigate(new MainMenu(router));
    }

    @SuppressWarnings("unchecked")
    protected void setProduct(RamItem productData) {

        ((FormField<RamType>) this.fields.get(0)).setValue(productData.getType());
        ((FormField<String>) this.fields.get(1)).setValue(Integer.toString(productData.getBusSpeed()));
        ((FormField<String>) this.fields.get(2)).setValue(productData.getBrand());
        ((FormField<String>) this.fields.get(3)).setValue(Integer.toString(productData.getQuantity()));
        ((FormField<String>) this.fields.get(4)).setValue(productData.getProductionMonthYear().toString());
        ((FormField<BooleanEnum>) this.fields.get(5))
                .setValue(productData.isActive() ? BooleanEnum.TRUE : BooleanEnum.FALSE);
    }

    protected abstract String provideUID();

    public abstract void submitForm();

}

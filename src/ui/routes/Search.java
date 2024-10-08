package ui.routes;

import rammanagementsystem.exceptions.InvalidFormatException;

import rammanagementsystem.models.RamItem;
import rammanagementsystem.models.enums.SearchType;
import rammanagementsystem.services.DataService;
import rammanagementsystem.services.DataServiceProvider;

import rammanagementsystem.utilities.Constants;

import ui.Router;
import ui.components.forms.EnumSelectorFormField;
import ui.components.forms.Form;

import ui.components.forms.InputFormField;
import utils.RouteDescriptor;
import utils.constants.StringConstants;

import java.util.List;
import java.util.ArrayList;

public class Search extends Form<RamItem> {

    private int currentPage = 0;
    private DataService<RamItem> dataService = DataServiceProvider.ramItemsDataService;
    protected List<RamItem> dataList = new ArrayList<RamItem>();

    public Search(Router router, Route prevRoute) {
        super(StringConstants.ROUTE_DESCRIPTOR.get((short) 3), router, prevRoute);
    }

    public Search(RouteDescriptor rd, Router router, Route prevRoute) {
        super(rd, router, prevRoute);
    }

    public Search(DataService ds, RouteDescriptor rd, Router router, Route prevRoute) {
        super(rd, router, prevRoute);
        this.dataService = ds;
    }

    @Override
    public void update(Character keyDown) {
        super.update(keyDown);
        if (!this.isEditing) {
            int maxPages = dataService.size() / Constants.PAGE_SIZE;
            if (Character.toLowerCase(keyDown) == 'd') {
                if (this.currentPage >= maxPages - 1) {
                    this.currentPage = 0;
                } else
                    currentPage++;
            }
            if (Character.toLowerCase(keyDown) == 'a') {
                if (this.currentPage <= 1) {
                    this.currentPage = maxPages - 1;
                } else
                    currentPage--;
            }
        }
        if (this.isEditing) {
            this.dataList.clear();
            StringBuilder queryString = new StringBuilder();
            switch ((SearchType) this.fields.get(1).getValue()) {
                case BUS_SPEED:
                    queryString.append("busSpeed=");
                    break;
                case BRAND:
                    queryString.append("brand=");
                    break;
                case TYPE:
                    queryString.append("type=");
                    break;
                case GENERAL:
                    queryString.append("");
            }
            queryString.append(this.fields.get(0).getValue());
            Router.setMotd(queryString.toString());
            this.dataList.addAll(this.dataService.queryMap(queryString.toString()).values());
        }
    }

    @Override
    public void initializeForm() {
        this.setCancelMessage("");
        this.setAcceptMessage("Back");

        this.fields.add(new InputFormField(
                "Query:", ""));

        this.fields.add(new EnumSelectorFormField<>("Search by", SearchType.GENERAL));

    }

    @Override
    public void submitForm() {
        router.navigate(prevRoute);
    }

    @Override
    public void cancelForm() {
        router.navigate(prevRoute);
    }

    @Override
    public String renderRouteContent() {
        return String.format("%s\n%s",
                this.renderForm(),
                this.renderPaginator());
    }

    private String renderPaginator() {
        StringBuilder sb = new StringBuilder();
        int maxPages = dataList.size() / Constants.PAGE_SIZE;
        if (this.dataService.size() == 0) {
            sb.append("No products currently available, please load a new file.");
            return sb.toString();
        }
        if (dataList.size() == 0) {
            sb.append("No products found.");
            return sb.toString();
        }

        sb.append(renderPage(currentPage));
        sb.append(String.format("-----------\nPage %d of %d | Showing items %d - %d",
                this.currentPage + 1,
                maxPages == 0 ? 1 : maxPages,
                currentPage * Constants.PAGE_SIZE,
                this.currentPage == maxPages ? this.dataList.size() : (currentPage + 1) * Constants.PAGE_SIZE));
        return sb.toString();
    }

    private String renderPage(int page) {
        StringBuilder sb = new StringBuilder();
        for (int i = currentPage * Constants.PAGE_SIZE; i < (currentPage + 1) * Constants.PAGE_SIZE; i++) {
            try {
                sb.append(dataList.get(i).toString() + "\n");
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
        return sb.toString();
    }

    @Override
    public RamItem parseForm() throws InvalidFormatException {
        throw new InvalidFormatException();
    }

}

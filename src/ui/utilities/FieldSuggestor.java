package ui.utilities;

import java.util.HashMap;

import rammanagementsystem.interfaces.Queriable;
import rammanagementsystem.services.DataService;
import ui.components.forms.FormField;

public class FieldSuggestor<T extends Queriable> {
    private DataService<T> dataService;
    private FormField<String> field;

    public FieldSuggestor(
            DataService<T> dataService,
            FormField<String> field

    ) {
        this.dataService = dataService;
        this.field = field;
    }

    public String getSuggestion() {
        HashMap<String, T> queryResult = this.dataService.queryMap(field.getValue());
        if (queryResult.isEmpty())
            return "";
        for (Queriable q : queryResult.values()) {
            if (q.getQueryString().contains(field.getValue())) {
                return q.getId();
            }
        }
        return "";
    }
}

package utils.constants;
import java.util.HashMap;
import java.util.Map;
import utils.RouteDescriptor;

public final class StringConstants {
    public final static Map<Short, String> MAIN_MENU = new HashMap<>();
    public final static int FORM_FIELD_PADDING = 15;
    public final static int FORM_FIELD_MAX_LENGTH = 40;
    public final static Map<Short, RouteDescriptor> ROUTE_DESCRIPTOR = new HashMap<>();
    static {
        MAIN_MENU.put((short) 0, "Add a product");
        MAIN_MENU.put((short) 1, "View & search items");
        MAIN_MENU.put((short) 2, "Update a product");
        MAIN_MENU.put((short) 3, "Deactivate an item");
        MAIN_MENU.put((short) 4, "Generate random data");
        MAIN_MENU.put((short) 5, "Exit");

        ROUTE_DESCRIPTOR.put((short) 0, new RouteDescriptor("Main Menu", "Press W or S to navigate the options and Enter to select an option"));
        ROUTE_DESCRIPTOR.put((short) 1, new RouteDescriptor("Add a product", "Press Enter to to toggle inputting for the field that is highlighted in white."));
        ROUTE_DESCRIPTOR.put((short) 2, new RouteDescriptor("Search a product by name", "Search a product by name, and its attributes. Use A and D to navigate between pages."));
        ROUTE_DESCRIPTOR.put((short) 3, new RouteDescriptor("Update a product", "Update a product in the product list by ID."));
        ROUTE_DESCRIPTOR.put((short) 4, new RouteDescriptor("Delete a product", "Delete a product from the product list"));
        ROUTE_DESCRIPTOR.put((short) 5, new RouteDescriptor("Save to file", "Save the product list to a file"));
        ROUTE_DESCRIPTOR.put((short) 6, new RouteDescriptor("Load product list from file", "Load the product list from a file"));
    }
}


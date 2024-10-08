package ui.routes;

import java.util.Map;

import ui.Router;
import utils.constants.StringConstants;

public class MainMenu extends Route {
    public MainMenu(Router router) {
        super(StringConstants.ROUTE_DESCRIPTOR.get((short) 0), router);
    }

    private short selectedOption = 0;
    private Map<Short, String> mainMenuOptions = StringConstants.MAIN_MENU;

    @Override
    public void update(Character value) {
        // Handle up and down actions
        if (Character.toLowerCase(value) == 'w') {
            selectedOption--;
            if (selectedOption == -1) {
                selectedOption = (short) (StringConstants.MAIN_MENU.size() - 1);
            }
        } else if (Character.toLowerCase(value) == 's') {
            selectedOption++;
            if (selectedOption == StringConstants.MAIN_MENU.size()) {
                selectedOption = 0;
            }
        }

        // Handle enter action

        if (((int) value) == 13) {
            switch (selectedOption) {
                case 0:
                    router.navigate(new AddProductForm(router));
                    break;
                case 1:
                    router.navigate(new Search(router, this));
                    break;
                case 2:
                    router.navigate(new ProductDeleteQuery(router, this));
                    break;
                case 3:
                    router.navigate(new UpdateQuery(router, this));
                    break;
                case 4:
                    System.exit(0);
                    break;
            }
        }
    }

    @Override
    public String renderRouteContent() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (int i = 0; i < StringConstants.MAIN_MENU.size(); i++) {
            if (i == selectedOption) {
                sb.append("-> ");
            } else {
                sb.append("   ");
            }
            sb.append("Option ").append(i + 1).append(": ");
            sb.append(mainMenuOptions.get((short) i));
            sb.append("\n");
        }
        return sb.toString();
    }

}

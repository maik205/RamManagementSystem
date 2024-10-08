package ui;

import rammanagementsystem.utilities.Constants;
import ui.exceptions.InvalidColorException;
import ui.routes.Route;
import ui.utilities.Colorizer;
import utils.Utils;

/**
 * Router class that manages the current route and renders it
 */
public class Router {
    private Route currentRoute;
    private static String motd;

    Router() {
    }

    /**
     * Renders the current route
     * 
     * @return String representation of the current route
     */
    public String renderRoute() {
        StringBuilder result = new StringBuilder();
        result.append(Colorizer.colorize(Constants.COMPANY_NAME + '\n', "yellow"));
        result.append(currentRoute.routeDescriptor.toString());
        if (motd != null)
            result.append(motd);
        result.append("\n-----------\n");

        if (currentRoute != null) {
            result.append(currentRoute.renderRouteContent());
        }
        return result.toString();
    }

    public static void setMotd(String str) {
        motd = str;
    }

    public void navigate(Route route) {
        Utils.clearConsole();
        UIController.input.unsubscribe(currentRoute);
        UIController.input.subscribe(route);
        this.currentRoute = route;
    }
}

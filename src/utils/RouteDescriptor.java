package utils;

import ui.utilities.Colorizer;
/**
 * RouteDescriptor
 */
public class RouteDescriptor {
    private String routeName;
    private String routeDescription;

    public RouteDescriptor(String routeName, String routeDescription) {
        this.routeName = routeName;
        this.routeDescription = routeDescription;
    }

    public String getRouteName() {
        return routeName;
    }

    public String getRouteDescription() {
        return routeDescription;
    }
    
    @Override
    public String toString() {
        try {
            return String.format("%s\n%s\n",
            Colorizer.colorize(getRouteName(), "black", "white"),
            getRouteDescription()
         );
        } catch (Exception e) {
            return "An error occurred";
        }
    }
}
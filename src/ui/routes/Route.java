package ui.routes;

import ui.Router;
import utils.Observable;
import utils.Observer;
import utils.RouteDescriptor;

public abstract class Route extends Observable<Character> implements Observer<Character> {
    public final RouteDescriptor routeDescriptor;
    protected Router router;

    public Route(RouteDescriptor descriptor, Router router) {
        this.routeDescriptor = descriptor;
        this.router = router;
    }

    public abstract String renderRouteContent();

}

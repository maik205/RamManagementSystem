package ui;

import rammanagementsystem.services.DataServiceProvider;
import ui.routes.MainMenu;
import utils.Utils;

public class UIController {

    public static final InputBuffer input = new InputBuffer();
    private final Router router = new Router();

    static {
        if ((System.getProperty("sun.arch.data.model")).equals("32")) {
            System.loadLibrary("keyhelper32");
        } else {
            System.loadLibrary("keyhelper");
        }
    }

    /**
     * Starts the UI loop and listens for keyboard input.
     */
    public void start() {
        router.navigate(new MainMenu(router));
        DataServiceProvider.ramItemsDataService.selfTest();
        cycle();
        while (true) {
            if (kbHit()) {
                char c = getChar();
                input.addBuffer(c);
                input.set(c);
                cycle();
            }
        }
    }

    /**
     * Clears the console and rerenders the current route.
     */
    private void cycle() {
        Utils.clearConsole();
        System.out.println(this.router.renderRoute());
    }

    private native boolean kbHit();

    private native char getChar();
}

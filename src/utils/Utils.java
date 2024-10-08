package utils;

import ui.utilities.Logger;
import utils.constants.StringConstants;

public class Utils {
    public static void clearConsole() {
        try {
            String operatingSystem = System.getProperty("os.name"); // Check the current operating system

            if (operatingSystem.contains("Windows")) {
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();

                startProcess.waitFor();
            }
        } catch (Exception e) {
            Logger.log(e.getMessage());
            Logger.log("Error clearing the console.");
        }
    }


    public static void moveCursorToPosition(int x, int y) {
        char escCode = 0x1B;

        System.out.print(String.format("%c[%d;%df", escCode, y, x));
    }
}

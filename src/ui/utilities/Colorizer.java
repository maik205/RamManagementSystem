package ui.utilities;

import ui.exceptions.InvalidColorException;

import java.util.HashMap;

/**
 * Utility class for colorizing text with ANSI color codes
 */
public class Colorizer {
    private final static HashMap<String, String> colorMap = new HashMap<>();
    private final static HashMap<String, String> backgroundMap = new HashMap<>();

    static {
        // Text colors
        colorMap.put("black", ColorConstants.ANSI_BLACK);
        colorMap.put("red", ColorConstants.ANSI_RED);
        colorMap.put("green", ColorConstants.ANSI_GREEN);
        colorMap.put("yellow", ColorConstants.ANSI_YELLOW);
        colorMap.put("blue", ColorConstants.ANSI_BLUE);
        colorMap.put("purple", ColorConstants.ANSI_PURPLE);
        colorMap.put("cyan", ColorConstants.ANSI_CYAN);
        colorMap.put("white", ColorConstants.ANSI_WHITE);

        // Background colors
        backgroundMap.put("black", ColorConstants.ANSI_BLACK_BACKGROUND);
        backgroundMap.put("red", ColorConstants.ANSI_RED_BACKGROUND);
        backgroundMap.put("green", ColorConstants.ANSI_GREEN_BACKGROUND);
        backgroundMap.put("yellow", ColorConstants.ANSI_YELLOW_BACKGROUND);
        backgroundMap.put("blue", ColorConstants.ANSI_BLUE_BACKGROUND);
        backgroundMap.put("purple", ColorConstants.ANSI_PURPLE_BACKGROUND);
        backgroundMap.put("cyan", ColorConstants.ANSI_CYAN_BACKGROUND);
        backgroundMap.put("white", ColorConstants.ANSI_WHITE_BACKGROUND);
    }

    /**
     * Colorize text with a text color
     * 
     * @param text  Text to colorize
     * @param color Text color (possible values: black, red, green, yellow, blue,
     *              purple, cyan, white)
     * @return Colorized text with ANSI color codes
     * @throws InvalidColorException If the color is invalid
     */
    public static String colorize(String text, String color) {
        try {
            if (colorMap.containsKey(color)) {
                return colorMap.get(color) + text + ColorConstants.ANSI_RESET;
            } else
                throw new InvalidColorException();
        } catch (InvalidColorException e) {
            return text;
        }
    }

    /**
     * Colorize text with a background color
     * 
     * @param text       Text to colorize
     * @param color      Text color (possible values: black, red, green, yellow,
     *                   blue, purple, cyan, white)
     * @param background Background color
     * @return Colorized text with ANSI color codes
     * @throws InvalidColorException If the color or background is invalid
     */
    public static String colorize(String text, String color, String background) throws InvalidColorException {
        if (backgroundMap.containsKey(background)) {
            return backgroundMap.get(background) + colorize(text, color);
        } else
            throw new InvalidColorException();
    }
}

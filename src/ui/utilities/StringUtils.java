package ui.utilities;

import java.util.ArrayList;
import java.util.List;

import utils.constants.StringConstants;

public class StringUtils {

    public static List<String> splitLongString(String str) {
        List<String> splitStrings = new ArrayList<>();
        StringBuilder workingString = new StringBuilder(str);
        while (true) {
            if (workingString.length() > StringConstants.FORM_FIELD_MAX_LENGTH) {
                splitStrings.add(workingString.substring(0, StringConstants.FORM_FIELD_MAX_LENGTH));
                workingString.delete(0, StringConstants.FORM_FIELD_MAX_LENGTH);
            } else {
                splitStrings.add(workingString.toString());
                break;
            }
        }
        return splitStrings;
    }

    public static String getLabelPadding(String label) {
        return getLabelPadding(label, StringConstants.FORM_FIELD_PADDING);
    }

    public static String getLabelPadding(String label, int padding) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < padding - label.length(); i++) {
            res.append(" ");
        }
        return res.toString();

    }
}

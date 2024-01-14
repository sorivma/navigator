package util;

public class Utils {
    public static String joinStrings(String[] strings) {
        if (strings == null || strings.length == 0) {
            return "";
        }

        StringBuilder result = new StringBuilder(strings[0]);
        for (int i = 1; i < strings.length; i++) {
            result.append("->").append(strings[i]);
        }

        return result.toString();
    }
}

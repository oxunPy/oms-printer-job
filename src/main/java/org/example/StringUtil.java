package org.example;

//import org.apache.commons.lang.StringEscapeUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

public class StringUtil {

    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isLong(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isBigDecimal(String str) {
        try {
            new BigDecimal(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static Double parseDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Integer parseInteger(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Long parseLong(String str) {
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static BigDecimal parseBigDecimal(String str) {
        try {
            return new BigDecimal(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static String toString(Object o) {
        return o != null ? o.toString() : null;
    }

    public static String toStringTrim(Object o) {
        return o != null ? o.toString().trim() : null;
    }

    public static String joinListItems(List<Integer> list) {
        StringBuilder result = new StringBuilder();
        for (Integer item : list) {
            result.append((result.length() == 0) ? item : "," + item);
        }
        return result.toString();
    }

    public static String clearAnother(String text) {
        if (text == null) {
            return null;
        }
        return text.replaceAll("[^0-9,^a-z,^A-Z]", "");
    }

    public static boolean notEmpty(String... values) {
        boolean notEmpty = true;
        for (String value : values) {
            if (value == null || value.isEmpty()) {
                notEmpty = false;
                break;
            }
        }
        return notEmpty;
    }
}

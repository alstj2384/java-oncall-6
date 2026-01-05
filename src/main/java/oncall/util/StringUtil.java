package oncall.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtil {
    public static List<String> split(String input, String delimiter) {
        return Arrays.stream(input.split(delimiter))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
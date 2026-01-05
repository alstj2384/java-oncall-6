package oncall.util;

import java.util.List;
import oncall.domain.WorkQueue;

public class Parser {
    private static final String DELIMITER = ",";

    public static int parsePositiveInt(String value) {
        try {
            int parsed = Integer.parseInt(value);
            if (parsed < 0) {
                throw new IllegalArgumentException("[ERROR] 숫자는 양수여야 합니다.");
            }
            return parsed;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자 형식이 아닙니다.");
        }
    }

    public static WorkQueue parseToWorkQueue(String weekWorkers) {
        List<String> split = StringUtil.split(weekWorkers, DELIMITER);
        return new WorkQueue(split);
    }
}

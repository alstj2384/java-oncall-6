package oncall.util;

import java.util.List;
import oncall.domain.WorkQueue;

public class Parser {
    private static final String DELIMITER = ",";

    public static int parsePositiveInt(String value) {
        try {
            int parsed = Integer.parseInt(value);
            if (parsed < 0) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
            }
            return parsed;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
    }

    public static WorkQueue parseToWorkQueue(String weekWorkers) {
        List<String> split = StringUtil.split(weekWorkers, DELIMITER);
        return new WorkQueue(split);
    }
}

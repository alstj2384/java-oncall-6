package oncall.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DateGenerator {
    private static final List<Integer> dayOfMonth = new ArrayList<>(
            Arrays.asList(999, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31));
    private static final List<Date> holidays = new ArrayList<>(
            Arrays.asList(
                    new Date(1, 1, null, true),
                    new Date(3, 1, null, true),
                    new Date(5, 5, null, true),
                    new Date(6, 6, null, true),
                    new Date(8, 15, null, true),
                    new Date(10, 3, null, true),
                    new Date(12, 25, null, true)
            ));

    public static List<Date> generate(int month, String weekString) {
        Week week = Week.findByString(weekString);
        List<Date> dates = new ArrayList<>();

        for (int i = 1; i <= dayOfMonth.get(month); i++) {
            dates.add(new Date(month, i, week, isHoliday(month, i)));
            week = week.next();
        }
        return dates;
    }

    private static boolean isHoliday(int month, int day) {
        Date date = new Date(month, day, null, false);
        ;

        for (Date holiday : holidays) {
            if (date.equals(holiday)) {
                return true;
            }
        }
        return false;
    }


}

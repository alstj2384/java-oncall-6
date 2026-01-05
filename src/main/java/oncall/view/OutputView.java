package oncall.view;

import java.util.List;
import oncall.domain.Date;
import oncall.dto.Schedule;

public class OutputView {
    public void printException(String message) {
        System.out.println(message);
    }

    public void printSchedule(List<Schedule> schedules) {
        for (Schedule schedule : schedules) {
            Date date = schedule.getDate();
            System.out.print(date.getMonth() + "월 ");
            System.out.print(date.getDay() + "일 ");
            printWeekFormat(date);
            System.out.print(schedule.getWorker());
            System.out.println();
        }
    }

    private void printWeekFormat(Date date) {
        if (date.isWeekend()) {
            System.out.print(date.getWeek() + "(휴일) ");
        }
        System.out.print(date.getWeek() + " ");
    }
}


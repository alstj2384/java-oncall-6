package oncall;

import java.util.List;
import oncall.domain.Date;
import oncall.domain.DateGenerator;
import oncall.domain.WorkQueue;
import oncall.dto.Schedule;
import oncall.dto.WorkQueues;
import oncall.service.ScheduleService;
import oncall.util.Parser;
import oncall.view.InputView;
import oncall.view.OutputView;

public class ScheduleController {
    private static final String DELIMITER = ",";
    private final InputView inputView;
    private final OutputView outputView;
    private final ScheduleService scheduleService;

    public ScheduleController(InputView inputView, OutputView outputView, ScheduleService scheduleService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.scheduleService = scheduleService;
    }

    public void run() {
        List<Date> dates = generateDates();

        WorkQueues workQueues = saveWorkers();
        workQueues.getWeekWorkQueue().display();
        workQueues.getWeekendWorkQueue().display();

        List<Schedule> schedules = scheduleService.makeSchedule(dates, workQueues);

        outputView.printSchedule(schedules);
    }

    private WorkQueues saveWorkers() {
        while (true) {
            try {
                String weekWorkers = inputView.readWeekWorker();
                WorkQueue weekWorkQueue = Parser.parseToWorkQueue(weekWorkers);

                String weekendWorkers = inputView.readWeekendWorker();
                WorkQueue weekendWorkQueue = Parser.parseToWorkQueue(weekendWorkers);

                return new WorkQueues(weekWorkQueue, weekendWorkQueue);
            } catch (IllegalArgumentException e) {
                outputView.printException(e.getMessage());
            }
        }
    }

    private List<Date> generateDates() {
        while (true) {
            try {
                String input = inputView.readMonthAndWeek();
                String[] split = input.split(DELIMITER);
                int month = Parser.parsePositiveInt(split[0]);
                return DateGenerator.generate(month, split[1]);
            } catch (IllegalArgumentException e) {
                outputView.printException(e.getMessage());
            }
        }
    }

    /* 재입력 로직 템플릿 */
//    private 리턴값 메서드이름() {
//        while (true) {
//            try {
//                동작
//
//                return ;
//            } catch (IllegalArgumentException e) {
//                outputView.printException(e.getMessage());
//            }
//        }
//    }
}

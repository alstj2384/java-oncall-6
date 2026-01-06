package oncall.service;

import java.util.ArrayList;
import java.util.List;
import oncall.domain.Date;
import oncall.domain.WeekType;
import oncall.domain.WorkQueue;
import oncall.domain.Worker;
import oncall.dto.Schedule;
import oncall.dto.WorkQueues;
import oncall.repository.WorkerRepository;

public class ScheduleService {
    private final WorkerRepository workerRepository;

    public ScheduleService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    public void save(WorkQueues workQueues) {
        WorkQueue weekendWorkQueue = workQueues.getWeekendWorkQueue();
        WorkQueue weekWorkQueue = workQueues.getWeekWorkQueue();

        workerRepository.add(WeekType.WEEKDAY, weekWorkQueue);
        workerRepository.add(WeekType.WEEKEND, weekendWorkQueue);
    }

    public List<Schedule> makeSchedule(List<Date> dates, WorkQueues workQueues) {
        List<Schedule> schedules = new ArrayList<>();
        WorkQueue weekWorkQueue = workQueues.getWeekWorkQueue();
        WorkQueue weekendWorkQueue = workQueues.getWeekendWorkQueue();

        int scheduleIndex = -1;
        for (Date date : dates) {
            if (date.isHoliday()) {
                Worker peek = weekendWorkQueue.peek();
                // 이전 근무자와 같은 경우를 확인
                if (schedules.size() != 0) {
                    Schedule schedule = schedules.get(scheduleIndex);
                    if (peek.equals(schedule.getWorker())) {
                        weekendWorkQueue.swap();
                    }
                }
                schedules.add(new Schedule(date, weekendWorkQueue.poll()));
            } else {
                Worker peek = weekWorkQueue.peek();
                // 이전 근무자와 같은 경우를 확인
                if (schedules.size() != 0) {
                    Schedule schedule = schedules.get(scheduleIndex);
                    if (peek.equals(schedule.getWorker())) {
                        weekWorkQueue.swap();
                    }
                }
                schedules.add(new Schedule(date, weekWorkQueue.poll()));
            }
            scheduleIndex++;
        }
        return schedules;
    }
}

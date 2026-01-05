package oncall.service;

import java.util.ArrayList;
import java.util.List;
import oncall.domain.Date;
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

    public void save(List<String> workers) {
        for (String worker : workers) {
            workerRepository.add(new Worker(worker));
        }
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
        // 2. 주말이면, 주말 근무자 peek
        // 2-1 앞 근무자와 겹치면, 주말 근무 큐 swap
        // 3. 평일이면, 평일 근무자 peek
        // 3-1 앞 근무자와 겹치면, 평일 근무 큐 swap
        return schedules;
    }
}

package oncall.service;

import java.util.List;
import java.util.Optional;
import oncall.domain.Date;
import oncall.domain.Schedule;
import oncall.domain.WeekType;
import oncall.domain.WorkQueue;
import oncall.domain.Worker;
import oncall.dto.WorkQueues;
import oncall.repository.ScheduleRepository;
import oncall.repository.WorkerRepository;

public class ScheduleService {
    private final WorkerRepository workerRepository;
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(WorkerRepository workerRepository, ScheduleRepository scheduleRepository) {
        this.workerRepository = workerRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public void save(WorkQueues workQueues) {
        WorkQueue weekendWorkQueue = workQueues.getWeekendWorkQueue();
        WorkQueue weekWorkQueue = workQueues.getWeekWorkQueue();

        workerRepository.add(WeekType.WEEKDAY, weekWorkQueue);
        workerRepository.add(WeekType.WEEKEND, weekendWorkQueue);
    }

    public List<Schedule> getSchedules() {
        return scheduleRepository.getAll();
    }

    public void makeSchedule(List<Date> dates) {
        for (Date date : dates) {
            putScheduleByWeekType(date);
        }
    }

    private void putScheduleByWeekType(Date date) {
        if (date.isHoliday()) {
            putSchedule(date, WeekType.WEEKEND);
            return;
        }
        putSchedule(date, WeekType.WEEKDAY);
    }

    private void putSchedule(Date date, WeekType weekType) {
        WorkQueue workQueue = workerRepository.get(weekType);
        swapIfDuplicated(workQueue);
        scheduleRepository.add(new Schedule(date, workQueue.poll()));
    }

    private void swapIfDuplicated(WorkQueue workQueue) {
        Optional<Schedule> optionalSchedule = scheduleRepository.peek();
        Worker peek = workQueue.peek();
        if (optionalSchedule.isEmpty()) {
            return;
        }
        Schedule schedule = optionalSchedule.get();
        if (peek.equals(schedule.getWorker())) {
            workQueue.swap();
        }
    }
}

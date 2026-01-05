package oncall.service;

import java.util.List;
import oncall.domain.Worker;
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
}

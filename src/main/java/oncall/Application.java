package oncall;

import oncall.repository.WorkerRepository;
import oncall.service.ScheduleService;
import oncall.view.InputView;
import oncall.view.OutputView;

public class Application {
    public static void main(String[] args) {
        ScheduleController scheduleController = new ScheduleController(new InputView(), new OutputView(),
                new ScheduleService(new WorkerRepository()));

        scheduleController.run();
    }
}

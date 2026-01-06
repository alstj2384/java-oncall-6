package oncall.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import oncall.domain.Schedule;

public class ScheduleRepository {
    private final List<Schedule> store;

    public ScheduleRepository() {
        this.store = new ArrayList<>();
    }

    public void add(Schedule schedule) {
        store.add(schedule);
    }

    public Optional<Schedule> peek() {
        if (store.size() != 0) {
            return Optional.of(store.get(0));
        }
        return Optional.empty();
    }

    public List<Schedule> getAll() {
        return Collections.unmodifiableList(store);
    }
}

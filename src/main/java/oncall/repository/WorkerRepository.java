package oncall.repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import oncall.domain.WeekType;
import oncall.domain.WorkQueue;


public class WorkerRepository {
    private final Map<WeekType, WorkQueue> store;
//    private final List<Worker> store;

    public WorkerRepository() {
        this.store = new HashMap<>();
    }

    public Map<WeekType, WorkQueue> getAll() {
        return Collections.unmodifiableMap(store);
    }

    public void add(WeekType weekType, WorkQueue workQueue) {
        store.put(weekType, workQueue);
    }

    public WorkQueue get(WeekType weekType) {
        return store.get(weekType);
    }


    public void deleteAll() {
        store.clear();
    }
}

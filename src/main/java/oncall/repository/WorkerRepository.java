package oncall.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import oncall.domain.Worker;


public class WorkerRepository {
    private final List<Worker> store;

    public WorkerRepository() {
        store = new ArrayList<>();
    }

    public List<Worker> getAll() {
        return Collections.unmodifiableList(store);
    }

    public void add(Worker worker) {
        // 만약 이미 있으면 예외 발생
        isNameExists(worker);
        store.add(worker);
    }

    private void isNameExists(Worker worker) {
        Optional<Worker> target = store.stream()
                .filter(wk -> wk.getName().equals(worker.getName()))
                .findAny();

        if (target.isPresent()) {
            throw new IllegalArgumentException("[ERROR] 이미 존재하는 사원입니다");
        }

    }


    public void deleteAll() {
        store.clear();
    }
}

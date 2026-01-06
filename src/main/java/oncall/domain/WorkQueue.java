package oncall.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WorkQueue {
    private final List<Worker> workers;

//    public WorkQueue(List<Worker> workers) {
//        this.workers = new ArrayList<>(workers);
//    }

    public WorkQueue(List<String> names) {
        List<Worker> workers = new ArrayList<>();
        for (String name : names) {
            workers.add(new Worker(name));
        }
        validate(workers);
        this.workers = workers;
    }

    public WorkQueue() {
        workers = new ArrayList<>();
    }

    public void validate(List<Worker> workers) {
        validateIsUnique(workers);
        validateSize(workers);
    }

    private void validateSize(List<Worker> workers) {
        if (workers.size() < 5 || workers.size() > 35) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
    }

    private void validateIsUnique(List<Worker> workers) {
        HashSet<Worker> unique = new HashSet<>(workers);
        if (unique.size() != workers.size()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
    }

    public void swap() {
        Worker remove = workers.remove(0);
        workers.add(1, remove);
    }

    public Worker poll() {
        Worker toRemove = workers.remove(0);
        workers.add(toRemove);
        return toRemove;
    }

    public Worker peek() {
        return workers.get(0);
    }

    public void display() {
        for (Worker worker : workers) {
            System.out.println(worker);
        }
    }
}

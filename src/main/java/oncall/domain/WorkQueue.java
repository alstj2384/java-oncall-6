package oncall.domain;

import java.util.ArrayList;
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
        this.workers = workers;
    }

    public WorkQueue() {
        workers = new ArrayList<>();
    }

    // TODO 개수 검증 (5명인가? 35명 미만인가?, 중복검증)
    public void swap() {
        Worker remove = workers.remove(0);
        workers.add(1, remove);
    }

    public Worker poll() {
        Worker toRemove = workers.remove(0);
        workers.add(toRemove);
        return toRemove;
    }

    public void display() {
        for (Worker worker : workers) {
            System.out.println(worker);
        }
    }
}

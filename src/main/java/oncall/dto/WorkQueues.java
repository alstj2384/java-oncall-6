package oncall.dto;

import oncall.domain.WorkQueue;

public class WorkQueues {
    private final WorkQueue weekWorkQueue;
    private final WorkQueue weekendWorkQueue;

    public WorkQueues(WorkQueue weekWorkQueue, WorkQueue weekendWorkQueue) {
        this.weekWorkQueue = weekWorkQueue;
        this.weekendWorkQueue = weekendWorkQueue;
    }

    public WorkQueue getWeekWorkQueue() {
        return weekWorkQueue;
    }

    public WorkQueue getWeekendWorkQueue() {
        return weekendWorkQueue;
    }
}

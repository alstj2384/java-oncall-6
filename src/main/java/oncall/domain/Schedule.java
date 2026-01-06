package oncall.domain;

public class Schedule {
    private final Date date;
    private final Worker worker;

    public Schedule(Date date, Worker worker) {
        this.date = date;
        this.worker = worker;
    }

    public Date getDate() {
        return date;
    }

    public Worker getWorker() {
        return worker;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "date=" + date +
                ", worker=" + worker +
                '}';
    }
}

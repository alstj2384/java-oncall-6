package oncall.domain;

import java.util.Objects;

public class Date {
    private final int month;
    private final int day;
    private final boolean isWeekend;

    public Date(int month, int day, boolean isWeekend) {
        this.month = month;
        this.day = day;
        this.isWeekend = isWeekend;
    }
    // TODO 생성자 입력값 검증하기

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Date date1 = (Date) o;
        return month == date1.month && day == date1.day;
    }

    @Override
    public int hashCode() {
        return Objects.hash(month, day);
    }

    @Override
    public String toString() {
        return "Date{" +
                "month=" + month +
                ", day=" + day +
                ", isWeekend=" + isWeekend +
                '}';
    }
}

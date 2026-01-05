package oncall.domain;

public enum Week {
    SUNDAY("일", true),
    MONDAY("월", false),
    TUESDAY("화", false),
    WEDNESDAY("수", false),
    THURSDAY("목", false),
    FRIDAY("금", false),
    SATURDAY("토", true);
    private final String value;
    private final boolean isWeekend;

    Week(String value, boolean isWeekend) {
        this.value = value;
        this.isWeekend = isWeekend;
    }

    public boolean isWeekend() {
        return isWeekend;
    }

    public String getValue() {
        return value;
    }

    public static Week findByString(String s) {
        for (Week value : Week.values()) {
            if (s.equals(value.getValue())) {
                return value;
            }
        }
        throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
    }

    public Week next() {
        if (this.equals(MONDAY)) {
            return TUESDAY;
        }
        if (this.equals(TUESDAY)) {
            return WEDNESDAY;
        }
        if (this.equals(WEDNESDAY)) {
            return THURSDAY;
        }
        if (this.equals(THURSDAY)) {
            return FRIDAY;
        }
        if (this.equals(FRIDAY)) {
            return SATURDAY;
        }
        if (this.equals(SATURDAY)) {
            return SUNDAY;
        }
        if (this.equals(SUNDAY)) {
            return MONDAY;
        }
        return null;
    }

    @Override
    public String toString() {
        return value;
    }
}

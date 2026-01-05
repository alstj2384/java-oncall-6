package oncall.domain;

import java.util.Objects;

public class Worker {
    private static final int MAX_LENGTH = 5;
    private final String name;

    public Worker(String name) {
        String strip = name.strip();
        validate(strip);
        this.name = strip;
    }

    public void validate(String name) {
        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("[ERROR] 사원 닉네임은 5자 이하여야 합니다.");
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Worker worker = (Worker) o;
        return Objects.equals(name, worker.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                '}';
    }
}

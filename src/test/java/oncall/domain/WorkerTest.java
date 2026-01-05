package oncall.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class WorkerTest {

    @Test
    void 이름이_5자_초과이면_예외가_발생한다() {
        Assertions.assertThatThrownBy(() -> new Worker("여섯글자이름")).isInstanceOf(IllegalArgumentException.class);
    }

}
package oncall;

import java.util.List;
import oncall.domain.Date;
import oncall.domain.DateGenerator;

public class Application {
    public static void main(String[] args) {
        List<Date> generate = DateGenerator.generate(4, "토");

        for (Date date : generate) {
            System.out.println(date);
        }
    }
}

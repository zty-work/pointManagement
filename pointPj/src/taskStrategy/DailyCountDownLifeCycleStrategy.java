package taskStrategy;

import java.util.Date;

public class DailyCountDownLifeCycleStrategy extends FixDurationCountDownLifeCycleStrategy {

    public DailyCountDownLifeCycleStrategy(int count, Date startTime) {
        super(count, 86400, startTime);
    }

}

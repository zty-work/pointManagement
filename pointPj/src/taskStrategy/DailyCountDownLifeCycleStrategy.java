package taskStrategy;

import java.util.Calendar;

public class DailyCountDownLifeCycleStrategy implements TaskCycleLifeStrategy {

    int count;
    Calendar calendar;

    public DailyCountDownLifeCycleStrategy(int count){
    }

    public boolean shouldFinish() {
        return count != 0;
    }


}

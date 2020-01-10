package taskStrategy;

import java.util.Date;

public class FixDurationCountDownLifeCycleStrategy implements TaskCycleLifeStrategy {
    private final int fixCount;
    private int count;
    private long duration; //ms
    private Date startTime;

    public FixDurationCountDownLifeCycleStrategy(int count, long duration,Date startTime) {
        fixCount = count;
        this.count = count;
        this.duration = duration;
        this.startTime = startTime;
    }

    public boolean shouldFinish() {
        count--;
        return count <= 0;
    }

    public void refresh(){
        count = fixCount;
    }

    public long getDuration() {
        return duration;
    }

    public Date getStartTime() {
        return startTime;
    }
}

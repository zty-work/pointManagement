package taskStrategy;

public class TotalCountDownLifeCycleStrategy implements TaskCycleLifeStrategy {

    private int count;

    public TotalCountDownLifeCycleStrategy(int count) {
        this.count = count;
    }

    public boolean shouldFinish() {
        count--;
        return count <= 0;
    }

}

package taskStrategy;

public class InfiniteLifeCycleStrategy implements TaskCycleLifeStrategy {

    public boolean shouldFinish() {
        return false;
    }
}

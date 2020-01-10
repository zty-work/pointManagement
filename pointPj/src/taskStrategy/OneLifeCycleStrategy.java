package taskStrategy;

public class OneLifeCycleStrategy implements TaskCycleLifeStrategy {

    public OneLifeCycleStrategy(){}

    public boolean shouldFinish() {
        return false;
    }
}

package taskStrategy;

public class TotalCountDownLifeCycleStrategy implements TaskCycleLifeStrategy {

    int count;

    public TotalCountDownLifeCycleStrategy(int count){
    }

    public boolean shouldFinish() {
        return count != 0;
    }

}

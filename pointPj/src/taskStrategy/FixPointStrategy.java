package taskStrategy;

public class FixPointStrategy implements TaskPointStrategy {

    private int point;

    public FixPointStrategy(int point) {
        this.point = point;
    }

    @Override
    public int calculatePoint() {
        return point;
    }

}

package transaction;

import java.util.ArrayList;
import java.util.List;

public abstract class Transaction {
    List<Flow> flow=new ArrayList<>();
    abstract void commit(TaskPerformer taskPerformer);

    public void setFlow(List<Flow> flow) {
        this.flow = flow;
    }

    public List<Flow> getFlow() {
        return flow;
    }
}

package transaction;

import task.TaskPerformer;
import java.util.List;

public class ConsumerTransaction extends Transaction {
    TaskPerformer taskPerformer;

    public ConsumerTransaction(List<Flow> flows, TaskPerformer taskPerformer) {
        this.flows = flows;
        this.taskPerformer = taskPerformer;
    }

    @Override
    public void commit() {
        this.taskPerformer.getAccount().addFlows(this.flows);
    }
}

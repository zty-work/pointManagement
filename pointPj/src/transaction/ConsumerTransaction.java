package transaction;

import task.TaskPerformer;
import task.UserTaskAction;

import java.util.ArrayList;
import java.util.List;

public class ConsumerTransaction extends Transaction {
    List<Flow> flows = new ArrayList<>();
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

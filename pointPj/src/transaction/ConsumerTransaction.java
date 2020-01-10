package transaction;

import task.TaskPerformer;
import task.UserTaskAction;

import java.util.ArrayList;
import java.util.List;

public class ConsumerTransaction extends Transaction{
    List<Flow> flows=new ArrayList<>();
    TaskPerformer taskPerformer;

    public ConsumerTransaction(List<Flow> flows){
        this.flows=flows;
    }
    @Override
    public void commit() {
      this.taskPerformer.getAccount().addFlows(this.flows);
      //  this.taskPerformer.setAccount(new Account(this.taskPerformer.getAccount().addFlows(this.flows)));
    }
}

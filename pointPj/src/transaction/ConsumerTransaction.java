package transaction;

public class ConsumerTransaction extends Transaction{
    @Override
    public void commit(TaskPerformer taskPerformer) {
        taskPerformer.setBalance(flow.amount);
    }
}

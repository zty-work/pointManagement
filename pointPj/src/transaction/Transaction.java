package transaction;

import java.util.ArrayList;
import java.util.List;


public abstract class Transaction {
    abstract void commit();
    List<Flow> flows = new ArrayList<>();

    public void setFlows(List<Flow> flows) {
        this.flows = flows;
    }
}

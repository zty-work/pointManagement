package transaction;

import java.util.List;

public class Account {
    int balance;
    List<Flow> flows;

    public Account(List<Flow> flows){
        this.flows=flows;
        int totalBalance=0;
        for(int i=0;i<flows.size();i++){
            totalBalance+=flows.get(i).amount;
        }
        this.balance=totalBalance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public List<Flow> getFlows() {
        return flows;
    }

    public void setFlows(List<Flow> flows) {
        this.flows = flows;
    }

    public List<Flow> addFlows(List<Flow> flows){
        this.flows.addAll(flows);
        return this.flows;
    }
}

package transaction;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private int balance;
    private List<Flow> flows;

    public Account() {
        balance = 0;
        flows = new ArrayList<>();
    }

    public Account(List<Flow> flows) {
        this.flows = flows;
        int totalBalance = 0;
        for (int i = 0; i < flows.size(); i++) {
            totalBalance += flows.get(i).amount;
        }
        this.balance = totalBalance;
    }
<<<<<<< HEAD
=======

    public void setBalance(int balance) {
        this.balance = balance;
    }
>>>>>>> 4c28241c4ba44a388c336bbe400ce40b5287bae8

    public int getBalance() {
        return balance;
    }


<<<<<<< HEAD
    public void addFlows(List<Flow> flows){
=======
    public void addFlows(List<Flow> flows) {
>>>>>>> 4c28241c4ba44a388c336bbe400ce40b5287bae8
        this.flows.addAll(flows);
    }
}

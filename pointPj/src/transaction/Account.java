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

    public int getBalance() {
        return balance;
    }

    public List<Flow> getFlows() {
        return flows;
    }

    public void addFlows(List<Flow> flows) {
        this.flows.addAll(flows);
        int totalBalance = this.balance;
        for (Flow flow : flows) {
            totalBalance += flow.amount;
        }
        this.balance = totalBalance;
    }

    public String printFlows() {
        String contents = "";
        for (Flow flow : flows) {
            contents += flow.description+"："+flow.amount+"分\n";
        }
        return contents;
    }
}

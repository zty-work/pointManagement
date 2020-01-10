package task;

import transaction.Account;
import transaction.Flow;

import java.util.List;

public class TaskPerformer {
    String name;
    Account account;
    public TaskPerformer(String name){
        this.name=name;
    }

    public Account getAccount() {
        return account;
    }

    public String getName() {
        return name;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}

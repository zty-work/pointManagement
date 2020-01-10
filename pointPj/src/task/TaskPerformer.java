package task;

import transaction.Account;

public class TaskPerformer {
    private String name;
    private Account account;

    public TaskPerformer(String name) {
        this.name = name;
        this.account = new Account();
    }

    public Account getAccount() {
        return account;
    }

    public String getName() {
        return name;
    }


}

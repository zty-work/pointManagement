package transaction;

import task.UserTask;
import task.UserTaskAction;
import task.UserTaskActionType;

import java.util.Date;

public class PerformTaskTransaction extends Transaction {
    UserTask userTask;

    public PerformTaskTransaction(UserTask userTask) {
        this.userTask = userTask;
    }

    public void setUserTask(UserTask userTask) {
        this.userTask = userTask;
    }

    public UserTask getUserTask() {
        return userTask;
    }

    @Override
    void commit() {
        int points = 0;
        points = this.userTask.addUserTaskAction(new UserTaskAction(new Date(), UserTaskActionType.GOAL));
    }
}

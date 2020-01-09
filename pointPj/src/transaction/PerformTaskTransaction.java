package transaction;

import task.TaskPerformer;
import task.UserTaskAction;

public class PerformTaskTransaction extends Transaction{
    UserTaskAction userTaskAction;

    public void commit(TaskPerformer taskPerformer) {

    }

    public void setUserTaskAction(UserTaskAction userTaskAction) {
        this.userTaskAction = userTaskAction;
    }

    public UserTaskAction getUserTaskAction() {
        return userTaskAction;
    }
}

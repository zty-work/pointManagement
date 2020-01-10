package task;

import java.util.List;

public class UserTask {
    UserTaskStatus status;
    List<UserTaskAction> userTaskActions;

    public List<UserTaskAction> getUserTaskActions() {
        return userTaskActions;
    }

    public void setUserTaskActions(List<UserTaskAction> userTaskActions) {
        this.userTaskActions = userTaskActions;
    }

    public void addUserTaskAction(UserTaskAction userTaskAction){
        this.userTaskActions.add(userTaskAction);
    }
}

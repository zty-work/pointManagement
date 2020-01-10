package task;

import java.util.Date;


public class UserTaskAction {
    Date actionTime;
    UserTaskActionType userTaskActionType;

    public UserTaskAction(Date actionTime, UserTaskActionType userTaskActionType) {
        this.actionTime = actionTime;
        this.userTaskActionType = userTaskActionType;
    }

    public UserTaskActionType getUserTaskActionType() {
        return userTaskActionType;
    }

    public Date getActionTime() {
        return actionTime;
    }
}

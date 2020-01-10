package task;

import java.util.Date;


public class UserTaskAction {
    Date actionTime;
    UserTaskActionType userTaskActionType;

    public UserTaskAction(Date actionTime, UserTaskActionType userTaskActionType) {
        this.actionTime = actionTime;
        this.userTaskActionType = userTaskActionType;
    }

}

package task;


import java.util.ArrayList;
import java.util.List;

import static task.UserTaskStatus.Active;
import static task.UserTaskStatus.Finished;

public class UserTask {
    private TaskDef taskDef;
    private UserTaskStatus status = Active;
    private TaskPerformer taskPerformer;
    private List<UserTaskAction> userTaskActions = new ArrayList<>();

    public UserTask(TaskDef taskDef, TaskPerformer taskPerformer) {
        this.taskDef = taskDef;
        this.taskPerformer = taskPerformer;
    }

    public List<UserTaskAction> getUserTaskActions() {
        return userTaskActions;
    }

    public void setUserTaskActions(List<UserTaskAction> userTaskActions) {
        this.userTaskActions = userTaskActions;
    }

    public int addUserTaskAction(UserTaskAction userTaskAction) {

        this.userTaskActions.add(userTaskAction);
        return performUserTaskAction(userTaskAction);
    }

    private int performUserTaskAction(UserTaskAction userTaskAction) {
        if (userTaskAction.userTaskActionType == UserTaskActionType.COMPLETE) {
            status = taskDef.taskCycleLifeStrategy.shouldFinish() ? Active : Finished;
        }
        return taskDef.taskPointStrategy.calculatePoint();
    }

    public TaskDef getTaskDef() {
        return taskDef;
    }

    public UserTaskStatus getStatus() {
        return status;
    }

    public TaskPerformer getTaskPerformer() {
        return taskPerformer;
    }

}

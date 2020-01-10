package task;


import taskStrategy.FixDurationCountDownLifeCycleStrategy;

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
        if (taskDef.taskCycleLifeStrategy instanceof FixDurationCountDownLifeCycleStrategy) {
            TaskTimerManager.getInstance().addTask(this, (FixDurationCountDownLifeCycleStrategy) taskDef.taskCycleLifeStrategy);
        }
    }

    public List<UserTaskAction> getUserTaskActions() {
        return userTaskActions;
    }

    public int addUserTaskAction(UserTaskAction userTaskAction) {
        this.userTaskActions.add(userTaskAction);
        return performUserTaskAction(userTaskAction);
    }

    private int performUserTaskAction(UserTaskAction userTaskAction) {
        if (userTaskAction.userTaskActionType == UserTaskActionType.GOAL) {
            status = taskDef.taskCycleLifeStrategy.shouldFinish() ? Finished : Active;
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

    public void setStatus(UserTaskStatus status) {
        this.status = status;
    }
}

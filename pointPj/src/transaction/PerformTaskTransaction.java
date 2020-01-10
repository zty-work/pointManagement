package transaction;

import task.TaskPerformer;
import task.UserTask;
import task.UserTaskAction;
import task.UserTaskActionType;

import java.util.Date;
import java.util.List;

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
        // if(this.userTask.TaskCycleLifeStrategy.shouldFinish()){
        int points = 0;
        if(true){
            points = this.userTask.addUserTaskAction(new UserTaskAction(new Date(), UserTaskActionType.COMPLETE));
        }else{
            points = this.userTask.addUserTaskAction(new UserTaskAction(new Date(), UserTaskActionType.NEXT));
        }

    }
}

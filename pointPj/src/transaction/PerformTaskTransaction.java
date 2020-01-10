package transaction;

import task.UserTask;
import task.UserTaskAction;
import task.UserTaskActionType;

import java.util.ArrayList;
import java.util.Date;

public class PerformTaskTransaction extends Transaction {
    UserTask userTask;

    public PerformTaskTransaction(UserTask userTask) {
        this.userTask = userTask;
    }

    @Override
    public void commit() {
        int points = this.userTask.addUserTaskAction(new UserTaskAction(new Date(), UserTaskActionType.COMPLETE));
        this.flows = new ArrayList<>();
        this.flows.add(new Flow(points, "完成任务，领取积分"));
        this.userTask.getTaskPerformer().getAccount().addFlows(this.flows);
    }
}
